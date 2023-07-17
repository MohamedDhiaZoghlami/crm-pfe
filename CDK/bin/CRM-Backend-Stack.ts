#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from 'aws-cdk-lib';
import { CRMEcsStack } from '../lib/CRM-ECS-Stack';
import { CRMBackendPipelineStack } from '../lib/CRM-Backend-pipeline-stack';

const app = new cdk.App();
const ecs_cluster = new CRMEcsStack(app, 'CRM-Backend-Stack');

new CRMBackendPipelineStack(app, 'CRM-Backend-Pipeline-Stack', {
    fargateService: ecs_cluster.fargateService,
    crm_ecr_repository: ecs_cluster.crm_ecr_repository,
    crm_ecs_cluster: ecs_cluster.crm_ecs_cluster
})
