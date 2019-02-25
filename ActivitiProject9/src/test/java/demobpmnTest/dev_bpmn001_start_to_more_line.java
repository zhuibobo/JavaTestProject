package demobpmnTest;

import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/7/18
 * To change this template use File | Settings | File Templates.
 */
public class dev_bpmn001_start_to_more_line {

    @Test
    public void testStartProcess() throws InterruptedException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        FormService formService=processEngine.getFormService();

        String bpmnFileName = "demobpmn/dev-bpmn001-start-to-more-line.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("dev-bpmn001-start-to-more-line.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        Deployment deployment=deploymentBuilder.deploy();

        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("dev-bpmn001").latestVersion().singleResult();
        String formKey=formService.getStartFormKey(processDefinition.getId());
        System.out.println(formKey);
        //获取流程xml模型
        BpmnModel bpmnModel=repositoryService.getBpmnModel(processDefinition.getId());
        //bpmnModel.getFlowElement()
        Process process = bpmnModel.getProcesses().get(0);
        //获取所有的FlowElement信息
        Collection<FlowElement> flowElements = process.getFlowElements();
        BPMNModelUtility.PrintStartEventToOutgoing(flowElements);

        //repositoryService.getModel()

        //启动流程
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String,Object> startParas=new HashMap<>();
        startParas.put("actionName","送人力资源");
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dev-bpmn001","sss",startParas);

        /*TaskService taskService=processEngine.getTaskService();
        Task taskOfHr=taskService.createTaskQuery().taskCandidateUser("hr").singleResult();
        formKey = taskOfHr.getFormKey();
        System.out.println(formKey);*/

        /*RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("bpmn06-23");

        TaskService taskService=processEngine.getTaskService();
        Task taskOfLeader=taskService.createTaskQuery().taskCandidateGroup("notformkey").singleResult();
        formKey = taskOfLeader.getFormKey();
        //formKey=formService.getTaskFormKey(processDefinition.getId(),taskOfLeader.getId());
        System.out.println(formKey);
        assertNotNull(taskOfLeader);
        assertEquals("没有设置formkey", taskOfLeader.getName());
        taskService.claim(taskOfLeader.getId(),"user-leader");
        taskService.complete(taskOfLeader.getId());


        taskOfLeader=taskService.createTaskQuery().taskCandidateGroup("formkey").singleResult();
        formKey = taskOfLeader.getFormKey();
        //formKey=formService.getTaskFormKey(processDefinition.getId(),taskOfLeader.getId());
        System.out.println(formKey);
        assertNotNull(taskOfLeader);
        assertEquals("设置了formkey", taskOfLeader.getName());
        taskService.claim(taskOfLeader.getId(),"user-leader");
        taskService.complete(taskOfLeader.getId());*/
    }

}
