package CamundaProject20.P004;

import CamundaProject20.CamundaUtility;
import CamundaProject20.MainApplicationTest;
import CamundaProject20.P003.P003Test;
/*import com.jb4dc.base.tools.XMLUtility;
import com.jb4dc.builder.workflow.integrate.IWorkFlowIntegratedService;
import com.jb4dc.builder.workflow.integrate.impl.WorkflowIntegrateServiceImpl;
import com.jb4dc.builder.workflow.po.bpmn.BpmnDefinitions;*/
import com.jb4dc.base.tools.XMLUtility;
import com.jb4dc.builder.workflow.integrate.impl.WFModelIntegrateServiceImpl;
import com.jb4dc.builder.workflow.integrate.impl.WFTaskIntegratedServiceImpl;
import com.jb4dc.builder.workflow.po.bpmn.BpmnDefinitions;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.impl.javax.el.ExpressionFactory;
import org.camunda.bpm.engine.impl.javax.el.ValueExpression;
import org.camunda.bpm.engine.impl.juel.ExpressionFactoryImpl;
import org.camunda.bpm.engine.impl.juel.SimpleContext;
import org.camunda.bpm.engine.impl.juel.SimpleResolver;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2019/11/25
 * To change this template use File | Settings | File Templates.
 */
public class P004Test extends MainApplicationTest {

    private Logger LOGGER= LoggerFactory.getLogger(P004Test.class);

    @Test
    public void P004_001_bpmn() throws InterruptedException, IOException, JAXBException, XMLStreamException {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();

        InputStream is=this.getClass().getClassLoader().getResourceAsStream("bpmn/P004_001_发文流程_解析XML模型用.bpmn");

        WFModelIntegrateServiceImpl workflowIntegrate=new WFModelIntegrateServiceImpl(null);
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

    @Test
    public void P004_002_1_bpmn() throws InterruptedException, IOException, JAXBException, XMLStreamException {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();

        //InputStream is=this.getClass().getClassLoader().getResourceAsStream("bpmn/P004_002_发文流程.bpmn");

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().name("P004Test-NAME").source("P004Test-Source").tenantId("P004Test-TenantId").addClasspathResource("bpmn/P004_002_发文流程.bpmn").deploy();

        P003Test.deleteAllProcessInstance(processEngine.getRuntimeService());

        RuntimeService runtimeService=processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByKey("P004_002","P004_002_bpmn");

        TaskService taskService=processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee("起草人");
        Task task=taskQuery.singleResult();
        Assert.assertEquals("起草",task.getName());

        Map<String,Object> vars=new HashMap<>();
        vars.put("act","送分管领导");
        List<UserTask> possibleTasks=getNextTasks(task.getProcessDefinitionId(),"ExclusiveGateway_0xr70ty",vars);
        Assert.assertEquals(1,possibleTasks.size());
        Assert.assertEquals("分管领导",possibleTasks.get(0).getName());

        vars=new HashMap<>();
        vars.put("act","送分管领导");
        possibleTasks=getNextTasks(task.getProcessDefinitionId(),"Task_1uhc294",vars);
        Assert.assertEquals(1,possibleTasks.size());
        Assert.assertEquals("分管领导",possibleTasks.get(0).getName());
    }

    //@Autowired
    //WFTaskIntegratedServiceImpl wfTaskIntegratedService;

    @Test
    public void P004_002_2_bpmn() {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();

        //InputStream is=this.getClass().getClassLoader().getResourceAsStream("bpmn/P004_002_发文流程.bpmn");

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().name("P004Test-NAME").source("P004Test-Source").tenantId("P004Test-TenantId").addClasspathResource("bpmn/P004_002_发文流程.bpmn").deploy();

        P003Test.deleteAllProcessInstance(processEngine.getRuntimeService());

        RuntimeService runtimeService=processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByKey("P004_002","P004_002_bpmn");

        TaskService taskService=processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee("起草人");
        Task task=taskQuery.singleResult();
        Assert.assertEquals("起草",task.getName());

        WFTaskIntegratedServiceImpl wfTaskIntegratedService=new WFTaskIntegratedServiceImpl();
        wfTaskIntegratedService.setProcessEngine(processEngine);
        List<SequenceFlow> outgoingSequenceFlow = wfTaskIntegratedService.getOutgoingSequenceFlow(task.getProcessDefinitionId(),"Task_1uhc294");
        System.out.println(outgoingSequenceFlow);
        //wfTaskIntegratedService.getSequenceFlow(task.getProcessDefinitionId(),"Task_1uhc294");
    }

    public List<UserTask> getNextTasks(String processDefinitionId, String taskDefinitionKey, Map<String, Object> vars) {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();
        RepositoryService repositoryService=processEngine.getRepositoryService();
        BpmnModelInstance modelInstance = repositoryService.getBpmnModelInstance(processDefinitionId);
        ModelElementInstance instance = modelInstance.getModelElementById(taskDefinitionKey);
        FlowNode flowNode = (FlowNode)instance;
        /*if(flowNode.getElementType().getTypeName().equals("exclusiveGateway")){
            return getNextTasks(processDefinitionId,flowNode.getId(),vars);
        }*/
        return getOutgoingTask(flowNode, vars);
    }
    private List<UserTask> getOutgoingTask(FlowNode node, Map<String, Object> vars) {
        List<UserTask> possibleTasks = new ArrayList<>();
        for(SequenceFlow sf: node.getOutgoing()) {
            if (sf.getName() != null) {
                LOGGER.info("Entering flow node {}", sf.getName());
            }
            boolean next = true;
            if (sf.getConditionExpression() != null) {
                ExpressionFactory factory = new ExpressionFactoryImpl();
                SimpleContext context = new SimpleContext(new SimpleResolver());

                LOGGER.info("Generating map vars {}", vars.toString());
                for (Map.Entry<String, Object> v : vars.entrySet()) {
                    if(v.getValue() instanceof Boolean) {
                        factory.createValueExpression(context, "${"+ v.getKey() +"}", Boolean.class).setValue(context, v.getValue());
                    }else if(v.getValue() instanceof java.util.Date) {
                        factory.createValueExpression(context, "${"+ v.getKey() +"}", java.util.Date.class).setValue(context, v.getValue());
                    }else {
                        factory.createValueExpression(context, "${"+ v.getKey() +"}", String.class).setValue(context, v.getValue());
                    }
                }
                ValueExpression expr1 = factory.createValueExpression(context, sf.getConditionExpression().getTextContent(), boolean.class);

                next = (Boolean)expr1.getValue(context);
                LOGGER.info("Evaluating expression {} to result {}", sf.getConditionExpression().getTextContent(), expr1.getValue(context));

            }

            if (next && sf.getTarget() != null) {
                if (sf.getTarget() instanceof UserTask) {
                    LOGGER.info("Found next user task {}", sf.getTarget().getName());
                    possibleTasks.add((UserTask)sf.getTarget());
                    break;
                }

                //possibleTasks.addAll(getOutgoing(sf.getTarget(), vars));
            }

        }
        return possibleTasks;
    }

    @Test
    public void P004_003_bpmn() throws InterruptedException, IOException, JAXBException, XMLStreamException {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().name("P004Test-NAME").source("P004Test-Source").tenantId("P004Test-TenantId").addClasspathResource("bpmn/P004_003_发文流程.bpmn").deploy();

        P003Test.deleteAllProcessInstance(processEngine.getRuntimeService());

        RuntimeService runtimeService=processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByKey("P004_003","P004_003_bpmn");

        TaskService taskService=processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee("起草人");
        Task task=taskQuery.singleResult();
        Assert.assertEquals("起草",task.getName());

        try {
            Map<String, Object> vars = new HashMap<>();
            //vars.put("act","送分管领导");
            taskService.complete(task.getId());
        }
        catch (ProcessEngineException ex){
            Assert.assertEquals("Unknown property used in expression: ${act==\"送负责人\"}. Cause: Cannot resolve identifier 'act'",ex.getMessage());
        }

        Map<String, Object> vars = new HashMap<>();
        vars.put("act","送分管领导");

        List<String> assigneeList=new ArrayList<>();
        assigneeList.add("User003");
        assigneeList.add("User004");
        assigneeList.add("User005");
        assigneeList.add("User006");
        assigneeList.add("User007");
        vars.put("assigneeList",assigneeList);
        taskService.complete(task.getId(),vars);


    }
}
