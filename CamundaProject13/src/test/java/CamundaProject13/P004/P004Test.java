package CamundaProject13.P004;

import CamundaProject13.CamundaUtility;
import CamundaProject13.MainApplicationTest;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.impl.RepositoryServiceImpl;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParse;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParser;
import org.camunda.bpm.engine.impl.cfg.DefaultBpmnParseFactory;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.repository.DeploymentBuilderImpl;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2019/11/25
 * To change this template use File | Settings | File Templates.
 */
public class P004Test extends MainApplicationTest {
    @Test
    public void P004_001_bpmn() throws InterruptedException, IOException {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().name("P004Test-NAME").source("P004Test-Source").tenantId("P004Test-TenantId").addClasspathResource("bpmn/P004_001_发文流程.bpmn").deploy();

        //repositoryService.createDeployment().
        //Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration());
        //DefaultBpmnParseFactory bpmnParseFactory = new DefaultBpmnParseFactory();
        //BpmnParser bpmnParser=new BpmnParser(((ProcessEngineConfigurationImpl)processEngine.getProcessEngineConfiguration()).getExpressionManager(),bpmnParseFactory);
        //BpmnParse bpmnParse=bpmnParser.createParse();
        //bpmnParse.sourceResource("bpmn/P004_001_发文流程.bpmn").execute();
        //DeploymentBuilder deploymentBuilder=new DeploymentBuilderImpl((RepositoryServiceImpl) processEngine.getRepositoryService());
        //deploymentBuilder.addClasspathResource("").getDeployment()
        //repositoryService.
    }
}
