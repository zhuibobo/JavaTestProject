package demobpmnTest;

import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class bpmn04_32 {

    @Test
    public void testStartProcess1() {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String bpmnFileName = "demobpmn/bpmn04-32.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("bpmn04-32.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        deploymentBuilder.deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("bpmn04-32");

        System.out.println("pid=" + processInstance.getId() + ", pdid="
                + processInstance.getProcessDefinitionId());

        TaskService taskService=processEngine.getTaskService();

        Task taskOfSelf=taskService.createTaskQuery().taskCandidateGroup("self").singleResult();
        Map<String,Object> paras= new HashMap<>();
        paras.put("day",2);
        assertEquals("请假申请", taskOfSelf.getName());
        taskService.claim(taskOfSelf.getId(),"user-self");
        taskService.complete(taskOfSelf.getId(),paras);

        Task taskOfLeader=taskService.createTaskQuery().taskCandidateGroup("leader").singleResult();
        assertEquals("部门领导审批", taskOfLeader.getName());
        taskService.claim(taskOfLeader.getId(),"user-leader");
        taskService.complete(taskOfLeader.getId());


        Task taskOfHr=taskService.createTaskQuery().taskCandidateGroup("hr").singleResult();
        Assert.assertEquals("人事归档",taskOfHr.getName());
        taskService.claim(taskOfHr.getId(),"user-hr");
        taskService.complete(taskOfHr.getId());

        HistoryService historyService = processEngine.getHistoryService();
        long count = historyService.createHistoricProcessInstanceQuery().finished()
                .count();
        assertEquals(1, count);
    }

}
