import {
  Stack, StackProps,
  aws_ecs as ecs,
  aws_ecr as ecr,
  RemovalPolicy,
  aws_ec2 as ec2,
  aws_iam as iam,
  aws_ecs_patterns as ecs_patterns,
  Duration
} from 'aws-cdk-lib';
import { Construct } from 'constructs';


export class CRMEcsStack extends Stack {
  public readonly fargateService: ecs_patterns.ApplicationLoadBalancedFargateService;
  public readonly crm_ecr_repository: ecr.Repository;
  public readonly crm_ecs_cluster: ecs.Cluster;

  constructor(scope: Construct, id: string, props?: StackProps) {
    super(scope, id, props);

    this.crm_ecr_repository = new ecr.Repository(this, "crm-ecr-repository", {
      repositoryName: "crm-backend-repository",
      removalPolicy: RemovalPolicy.DESTROY,
      autoDeleteImages: true,
    });

    const vpc = new ec2.Vpc(this, 'CRM-ecs-cdk-vpc', {
      ipAddresses: ec2.IpAddresses.cidr('10.0.0.0/16'),
      natGateways: 1,
      maxAzs: 2,
    });

    // ecs fargate cluster
    this.crm_ecs_cluster = new ecs.Cluster(this, "crm-ecs-cluster", {
      clusterName: "crm-ecs-cluster",
      enableFargateCapacityProviders: true,
      vpc: vpc
    });
    const logging = new ecs.AwsLogDriver({
      streamPrefix: "CRM-Backend-ecs-logs"
    });
    // const clusteradmin = new iam.Role(this, 'adminrole', {
    //   assumedBy: new iam.AccountRootPrincipal()
    // });

    const taskrole = new iam.Role(this, `ecs-taskrole-${this.stackName}`, {
      roleName: `CRM-Backend-ecs-taskrole-${this.stackName}`,
      assumedBy: new iam.ServicePrincipal('ecs-tasks.amazonaws.com')
    });


    const executionRolePolicy = new iam.PolicyStatement({
      effect: iam.Effect.ALLOW,
      resources: ['*'],
      actions: [
        "ecr:getauthorizationtoken",
        "ecr:batchchecklayeravailability",
        "ecr:getdownloadurlforlayer",
        "ecr:batchgetimage",
        "logs:createlogstream",
        "logs:putlogevents"
      ]
    });

    const taskDef = new ecs.FargateTaskDefinition(this, "CRM-ecs-taskdef", {
      taskRole: taskrole,
      cpu: 512,
      memoryLimitMiB: 1024,
      runtimePlatform: {
        operatingSystemFamily: ecs.OperatingSystemFamily.LINUX,
        cpuArchitecture: ecs.CpuArchitecture.X86_64
      }
    });

    taskDef.addToExecutionRolePolicy(executionRolePolicy);

    const baseImage = 'docker.io/softwareimprove/crm_backend:amd64'

    const container = taskDef.addContainer('CRM-Backend-app', {
      image: ecs.ContainerImage.fromRegistry(baseImage),
      logging: logging,
    });
    container.addPortMappings({
      containerPort: 8080,
      protocol: ecs.Protocol.TCP
    });

    this.fargateService = new ecs_patterns.ApplicationLoadBalancedFargateService(this, "ecs-service", {
      cluster: this.crm_ecs_cluster,
      taskDefinition: taskDef,
      publicLoadBalancer: true,
      desiredCount: 0,
      listenerPort: 80,
      healthCheckGracePeriod: Duration.seconds(600),
    });

    this.fargateService.service.autoScaleTaskCount({ maxCapacity: 2 }).scaleOnCpuUtilization('cpuscaling', {
      targetUtilizationPercent: 80,
      scaleInCooldown: Duration.seconds(600),
      scaleOutCooldown: Duration.seconds(600)
    });
  }
}
