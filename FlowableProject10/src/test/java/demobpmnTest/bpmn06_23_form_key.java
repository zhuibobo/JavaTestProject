package demobpmnTest;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class bpmn06_23_form_key {
    @Test
    public void testStartProcess() throws InterruptedException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        FormService formService=processEngine.getFormService();

        String bpmnFileName = "demobpmn/bpmn06-23-form-key.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("bpmn06-23-form-key.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        Deployment deployment=deploymentBuilder.deploy();

        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("bpmn06-23").singleResult();
        String formKey=formService.getStartFormKey(processDefinition.getId());
        System.out.println(formKey);

        RuntimeService runtimeService = processEngine.getRuntimeService();
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
        taskService.complete(taskOfLeader.getId());
    }
}
