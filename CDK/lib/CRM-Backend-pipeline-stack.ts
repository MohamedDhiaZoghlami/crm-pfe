import {
  Stack, StackProps,
  aws_codepipeline as codepipeline,
  aws_codebuild as codebuild,
  aws_ssm as ssm,
  aws_ecr as ecr,
  aws_ecs as ecs,
  aws_iam as iam,
  CfnOutput
} from 'aws-cdk-lib';

import { Construct } from 'constructs';


import { CodeSourceStage } from './stages/Backend-codeSource-stage';
import { BuildStage } from './stages/Backend-build-stage';
import { DeployStage } from './stages/Backend-deploy-stage';
import { BuildEnvironmentVariableType, Cache, LocalCacheMode } from 'aws-cdk-lib/aws-codebuild';
import { ApplicationLoadBalancedFargateService } from 'aws-cdk-lib/aws-ecs-patterns';


interface CRMBackendPipelineStackProps extends StackProps {
  fargateService: ApplicationLoadBalancedFargateService;
  crm_ecr_repository: ecr.Repository;
  crm_ecs_cluster: ecs.Cluster;
}
export class CRMBackendPipelineStack extends Stack {
  constructor(scope: Construct, id: string, props: CRMBackendPipelineStackProps) {
    super(scope, id, props);
    // const CRM_ASSETS_BUCKET_NAME = ssm.StringParameter.fromStringParameterAttributes(
    //   this,
    //   `CRM_ASSETS_BUCKET_NAME`,
    //   { parameterName: "/CRM/buckets/assets_bucket_name" }
    // ).stringValue;

    // const CRM_ASSETS_DISTRIBUTION_DOMAIN = ssm.StringParameter.fromStringParameterAttributes(
    //   this,
    //   `CRM_ASSETS_DISTRIBUTION_DOMAIN`,
    //   { parameterName: "/CRM/distributions/assets_distribution_domain_name" }
    // ).stringValue;


    const codeBuildProject = new codebuild.PipelineProject(this, "CodeBuildProject", {
      projectName: `CRM_Backend_BUILD_PROJECT`,
      environment: {
        buildImage: codebuild.LinuxBuildImage.STANDARD_7_0,
        computeType: codebuild.ComputeType.SMALL,
        privileged: true,
      },

      cache: Cache.local(LocalCacheMode.DOCKER_LAYER),
      // badge: true,
      buildSpec: codebuild.BuildSpec.fromObject({
        version: 0.2,
        phases: {
          pre_build: {
            commands: [
              'env',
              'export tag=latest',
              'echo "pass envirement variables on `date`"',
              'echo Logging in to Amazon ECR...',
              'aws ecr get-login-password --profile dhia | docker login --username AWS --password-stdin 888425790821.dkr.ecr.us-east-1.amazonaws.com'
            ]
          },
          build: {
            commands: [
              'echo "Build started on `date`"',
              'cd BACKEND',
              `docker build -t $ecr_repo_uri:$tag .`,
              'docker push $ecr_repo_uri:$tag',
              'echo "Build finished on `date`"',
            ],
          },
          post_build: {
            commands: [
              'echo "in post-build stage"',
              'cd ..',
              "printf '[{\"name\":\"CRM-Backend-APP\",\"imageUri\":\"%s\"}]' $ecr_repo_uri:$tag > imagedefinitions.json",
              "cat imagedefinitions.json"
            ]
          }
        },
        artifacts: {
          name: "BuildOutput",
          files: ['imagedefinitions.json']
        },
      }),
      environmentVariables: {
        REACT_APP_REGION: { type: BuildEnvironmentVariableType.PLAINTEXT, value: this.region },
        cluster_name: { type: BuildEnvironmentVariableType.PLAINTEXT, value: `${props.crm_ecs_cluster.clusterName}` },
        ecr_repo_uri: { type: BuildEnvironmentVariableType.PLAINTEXT, value: `${props.crm_ecr_repository.repositoryUri}` }
      },
    });
    const githubToken = ssm.StringParameter.fromStringParameterAttributes(
      this,
      `CRM_Github_Token`,
      { parameterName: "/CRM/github/accessToken" }
    ).stringValue;
    const buildStage = new BuildStage(this, "buildStage", { codeBuildProject: codeBuildProject })

    new codepipeline.Pipeline(this, "CRM-Backend-pipeline", {
      crossAccountKeys: false,
      pipelineName: `CRM_BACKEND_PIPELINE`,
      stages: [
        {
          stageName: "source",
          actions: [new CodeSourceStage(this, "sourceStage", { githubToken }).action]
        },
        {
          stageName: "build",
          actions: [buildStage.action]
        },
        {
          stageName: "deploy",
          actions: [
            new DeployStage(this, "deployStage", {
              fargateService: props.fargateService,
              buildOutput: buildStage.buildArtifact
            }).action
          ]
        }

      ]
    });
    props.crm_ecr_repository.grantPullPush(codeBuildProject.role!)
    codeBuildProject.addToRolePolicy(new iam.PolicyStatement({
      actions: [
        "ecs:describecluster",
        "ecr:getauthorizationtoken",
        "ecr:batchchecklayeravailability",
        "ecr:batchgetimage",
        "ecr:getdownloadurlforlayer"
      ],
      resources: [`${props.crm_ecs_cluster.clusterArn}`],
    }));


    new CfnOutput(this, "image", { value: props.crm_ecr_repository.repositoryUri + ":latest" })
    new CfnOutput(this, 'loadbalancerdns', { value: props.fargateService.loadBalancer.loadBalancerDnsName });

    new ssm.StringParameter(this, "CRM_LB_Backend-EXPORT", {
      stringValue: props.fargateService.loadBalancer.loadBalancerDnsName,
      parameterName: '/CRM/Backend/Backend_URL'
    });
  }
}
