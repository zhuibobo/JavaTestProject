package demobpmnTest;

import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class bpmn03_35 {
    @Test
    public void testStartProcess() {
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService=processEngine.getRepositoryService();
        String bpmnFileName="demobpmn/bpmn03-35.bpmn";
        DeploymentBuilder deploymentBuilder=repositoryService.createDeployment().addInputStream("bpmn03-35.bpmn",this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        deploymentBuilder.deploy();
        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();

        RuntimeService runtimeService=processEngine.getRuntimeService();

        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("bpmn03-35");

        System.out.println("pid=" + processInstance.getId() + ", pdid="
                + processInstance.getProcessDefinitionId());

        TaskService taskService=processEngine.getTaskService();
        Task taskOfLeader=taskService.createTaskQuery().taskCandidateGroup("leader").singleResult();
        assertEquals("领导审批", taskOfLeader.getName());
        taskService.claim(taskOfLeader.getId(),"user-leader");
        taskService.complete(taskOfLeader.getId());

        Task taskOfHr=taskService.createTaskQuery().taskCandidateGroup("hr").singleResult();
        Assert.assertEquals("人事审批",taskOfHr.getName());
        taskService.claim(taskOfHr.getId(),"user-hr");
        taskService.complete(taskOfHr.getId());

        Task taskOfSelf=taskService.createTaskQuery().taskCandidateGroup("self").singleResult();
        Assert.assertEquals("销假",taskOfSelf.getName());
        taskService.claim(taskOfSelf.getId(),"user-self");
        taskService.complete(taskOfSelf.getId());

        HistoryService historyService = processEngine.getHistoryService();
        long count = historyService.createHistoricProcessInstanceQuery().finished()
                .count();
        assertEquals(1, count);
    }
}
