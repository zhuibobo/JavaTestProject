package demobpmnTest;

import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class bpmn04_44 {

    @Test
    public void testStartProcess() throws InterruptedException {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String bpmnFileName = "demobpmn/bpmn04-44.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("bpmn04-44.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        deploymentBuilder.deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("bpmn04-44");

        TaskService taskService=processEngine.getTaskService();
        Task taskOfLeader=taskService.createTaskQuery().taskCandidateGroup("leader").singleResult();
        assertNotNull(taskOfLeader);
        assertEquals("部门领导审批", taskOfLeader.getName());
        taskService.claim(taskOfLeader.getId(),"user-leader");
        taskService.complete(taskOfLeader.getId());

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applyUser", "employee1");
        variables.put("days", 3);
        variables.put("approved", true);

        Task taskOfHr=taskService.createTaskQuery().taskCandidateGroup("hr").singleResult();
        assertNotNull(taskOfHr);
        assertEquals("人事审批", taskOfHr.getName());
        taskService.claim(taskOfHr.getId(),"user-hr");
        taskService.complete(taskOfHr.getId(),variables);

        /*Task taskOfSelf=taskService.createTaskQuery().taskCandidateGroup("self").singleResult();
        assertNotNull(taskOfSelf);
        assertEquals("销假", taskOfSelf.getName());
        taskService.claim(taskOfSelf.getId(),"user-self");*/

        Thread.sleep(20000);

    }
}
