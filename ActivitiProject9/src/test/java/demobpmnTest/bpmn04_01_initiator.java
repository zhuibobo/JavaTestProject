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

public class bpmn04_01_initiator {

    @Test
    public void testStartProcess() {
        //http://www.kafeitu.me/activiti/2012/09/14/activiti-initiator.html
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String bpmnFileName = "demobpmn/bpmn04-01-initiator.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("bpmn04-01-initiator.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        deploymentBuilder.deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String,Object> paras=new HashMap<>();
        paras.put("userId","zhuibobo1");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("bpmn04-01-initiator",paras);

        System.out.println("pid=" + processInstance.getId() + ", pdid="
                + processInstance.getProcessDefinitionId());

        TaskService taskService=processEngine.getTaskService();
        Task taskOfLeader=taskService.createTaskQuery().taskCandidateGroup("leader").singleResult();
        assertEquals("部门领导审核", taskOfLeader.getName());
        taskService.claim(taskOfLeader.getId(),"user-leader");
        taskService.complete(taskOfLeader.getId());

        Task taskOfSelf=taskService.createTaskQuery().taskCandidateOrAssigned("zhuibobo1").singleResult();
        Assert.assertEquals("调整",taskOfSelf.getName());
        taskService.claim(taskOfSelf.getId(),"zhuibobo1");
        taskService.complete(taskOfSelf.getId());
    }
}
