package CamundaProject13.P004;

import CamundaProject13.CamundaUtility;
import CamundaProject13.MainApplicationTest;
import com.jb4dc.base.tools.XMLUtility;
import com.jb4dc.workflow.integrate.WorkflowIntegrate;
import com.jb4dc.workflow.po.bpmn.BpmnDefinitions;
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
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2019/11/25
 * To change this template use File | Settings | File Templates.
 */
public class P004Test extends MainApplicationTest {
    @Test
    public void P004_001_bpmn() throws InterruptedException, IOException, JAXBException, XMLStreamException {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();

        InputStream is=this.getClass().getClassLoader().getResourceAsStream("bpmn/P004_001_发文流程_解析XML模型用.bpmn");

        //RepositoryService repositoryService = processEngine.getRepositoryService();
        //repositoryService.createDeployment().name("P004Test-NAME").source("P004Test-Source").tenantId("P004Test-TenantId").addClasspathResource("bpmn/P004_001_发文流程.bpmn").deploy();

        WorkflowIntegrate workflowIntegrate=new WorkflowIntegrate();
        BpmnDefinitions bpmnDefinitions=workflowIntegrate.parseToPO(is);

        Assert.assertEquals("发文流程",bpmnDefinitions.getBpmnProcess().getName());
        Assert.assertEquals("发文流程V1",bpmnDefinitions.getBpmnProcess().getVersionTag());

        consolelog(XMLUtility.toXMLString(bpmnDefinitions));

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
