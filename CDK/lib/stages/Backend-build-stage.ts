import {
    Stage, StageProps,
    aws_codepipeline_actions as actions,
    aws_codepipeline as codepipeline,

} from 'aws-cdk-lib';

import { Construct } from 'constructs';
import { PipelineProject } from 'aws-cdk-lib/aws-codebuild';
import { IAction } from 'aws-cdk-lib/aws-codepipeline';
interface BuildStageProps extends StageProps {

    codeBuildProject: PipelineProject
}
export class BuildStage extends Stage {
    public readonly action: IAction
    public readonly buildArtifact: codepipeline.Artifact
    constructor(scope: Construct, id: string, props: BuildStageProps) {
        super(scope, id, props);
        this.buildArtifact = new codepipeline.Artifact("Build")
        this.action = new actions.CodeBuildAction({
            actionName: "BuildSpringBootDockerImage",
            project: props.codeBuildProject,
            input: new codepipeline.Artifact("Source"),
            outputs: [this.buildArtifact],
        });
    }


}