package demobpmnTest;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class bpmn03_44 {

    @Test
    public void testStartProcess1() {
        //发起流程->部门领导审批->不同意->调整->取消申请->结束
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService=processEngine.getRepositoryService();
        String bpmnFileName="demobpmn/bpmn03-44.bpmn";
        repositoryService.createDeployment().addInputStream("bpmn03-44.bpmn",this.getClass().getClassLoader()
                .getResourceAsStream(bpmnFileName)).deploy();
        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
        RuntimeService runtimeService=processEngine.getRuntimeService();
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("bpmn03-44");

        TaskService taskService=processEngine.getTaskService();
        Task taskOfLeader=taskService.createTaskQuery().taskCandidateGroup("leader").singleResult();
        assertNotNull(taskOfLeader);
        assertEquals("部门领导审批", taskOfLeader.getName());

        taskService.claim(taskOfLeader.getId(),"user-leader");
        Map<String,Object> paras=new HashMap<>();
        paras.put("leader_comm","refuse");
        taskService.complete(taskOfLeader.getId(),paras);

        Task taskOfSelf=taskService.createTaskQuery().taskCandidateGroup("self").singleResult();
        assertNotNull(taskOfSelf);
        assertEquals("调整", taskOfSelf.getName());

        taskService.claim(taskOfSelf.getId(),"self-Adjustment");
        paras=new HashMap<>();
        paras.put("self_comm","cancel");
        taskService.complete(taskOfSelf.getId(),paras);

        HistoryService historyService = processEngine.getHistoryService();
        long count = historyService.createHistoricProcessInstanceQuery().finished()
                .count();
        assertEquals(1, count);
    }

    @Test
    public void testStartProcess2() {
        //发起流程->部门领导审批->不同意->调整->部门领导审批->同意
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService=processEngine.getRepositoryService();
        String bpmnFileName="demobpmn/bpmn03-44.bpmn";
        repositoryService.createDeployment().addInputStream("bpmn03-44.bpmn",this.getClass().getClassLoader()
                .getResourceAsStream(bpmnFileName)).deploy();
        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
        RuntimeService runtimeService=processEngine.getRuntimeService();
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("bpmn03-44");

        TaskService taskService=processEngine.getTaskService();
        Task taskOfLeader=taskService.createTaskQuery().taskCandidateGroup("leader").singleResult();
        assertNotNull(taskOfLeader);
        assertEquals("部门领导审批", taskOfLeader.getName());
        taskService.claim(taskOfLeader.getId(),"user-leader");
        Map<String,Object> paras=new HashMap<>();
        paras.put("leader_comm","refuse");
        taskService.complete(taskOfLeader.getId(),paras);

        Task taskOfSelf=taskService.createTaskQuery().taskCandidateGroup("self").singleResult();
        assertNotNull(taskOfSelf);
        assertEquals("调整", taskOfSelf.getName());
        taskService.claim(taskOfSelf.getId(),"self-Adjustment");
        paras=new HashMap<>();
        paras.put("self_comm","keepon");
        taskService.complete(taskOfSelf.getId(),paras);

        taskOfLeader=taskService.createTaskQuery().taskCandidateGroup("leader").singleResult();
        assertNotNull(taskOfLeader);
        assertEquals("部门领导审批", taskOfLeader.getName());
        taskService.claim(taskOfLeader.getId(),"user-leader");
        paras=new HashMap<>();
        paras.put("leader_comm","pass");
        taskService.complete(taskOfLeader.getId(),paras);

        Task hrOfSelf=taskService.createTaskQuery().taskCandidateGroup("hr").singleResult();
        assertNotNull(hrOfSelf);
        assertEquals("人事审批", hrOfSelf.getName());
        taskService.claim(hrOfSelf.getId(),"user-hr");
        paras=new HashMap<>();
        paras.put("hr_comm","pass");
        taskService.complete(hrOfSelf.getId(),paras);

        taskOfSelf=taskService.createTaskQuery().taskCandidateGroup("self").singleResult();
        assertNotNull(taskOfSelf);
        assertEquals("销假", taskOfSelf.getName());
        taskService.claim(taskOfSelf.getId(),"self-end");
        taskService.complete(taskOfSelf.getId());

        HistoryService historyService = processEngine.getHistoryService();
        long count = historyService.createHistoricProcessInstanceQuery().finished()
                .count();
        assertEquals(1, count);

    }
}
