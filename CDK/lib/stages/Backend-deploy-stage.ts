import {
    Stage, StageProps,
    aws_codepipeline_actions as actions,
    aws_codepipeline as codepipeline,
} from 'aws-cdk-lib';

import { Construct } from 'constructs';

import { Artifact, IAction } from 'aws-cdk-lib/aws-codepipeline';
import { ApplicationLoadBalancedFargateService } from 'aws-cdk-lib/aws-ecs-patterns';

interface DeployStageProps extends StageProps {
    fargateService: ApplicationLoadBalancedFargateService;
    buildOutput: Artifact
}
export class DeployStage extends Stage {
    public readonly action: IAction
    constructor(scope: Construct, id: string, props: DeployStageProps) {
        super(scope, id, props);
        this.action = new actions.EcsDeployAction({
            actionName: "deployEcs",
            service: props.fargateService.service,
            imageFile: new codepipeline.ArtifactPath(props.buildOutput, `imagedefinitions.json`)
        });
    }


}