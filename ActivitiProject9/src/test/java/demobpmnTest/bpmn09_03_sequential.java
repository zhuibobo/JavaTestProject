package demobpmnTest;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class bpmn09_03_sequential {

    @Test
    public void testStartProcess() throws InterruptedException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        FormService formService=processEngine.getFormService();

        String bpmnFileName = "demobpmn/bpmn09-03-sequential.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("bpmn09-03-sequential.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        Deployment deployment=deploymentBuilder.deploy();

        //ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("bpmn06-23").singleResult();

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("bpmn09-03-sequential");

        TaskService taskService=processEngine.getTaskService();
        Task taskOfSelf=taskService.createTaskQuery().taskCandidateUser("self").singleResult();
        assertNotNull(taskOfSelf);
        System.out.println(taskOfSelf.getDescription());
        assertEquals("请假申请", taskOfSelf.getName());
        taskService.claim(taskOfSelf.getId(),"user-self");

        Map<String,Object> paras=new HashMap<>();
        List<String> userList=new ArrayList<>();
        userList.add("zhuibobo");
        userList.add("lv");
        userList.add("cz");
        paras.put("users",userList);
        taskService.complete(taskOfSelf.getId(),paras);

        Task taskOfczNull=taskService.createTaskQuery().taskCandidateUser("cz").singleResult();
        Assert.assertNull(taskOfczNull);

        Task taskOfzhuibobo=taskService.createTaskQuery().taskCandidateUser("zhuibobo").singleResult();

        assertNotNull(taskOfzhuibobo);
        assertEquals("串行审批", taskOfzhuibobo.getName());
        taskService.claim(taskOfzhuibobo.getId(),"user-self");
        taskService.complete(taskOfzhuibobo.getId());

        Task taskOflv=taskService.createTaskQuery().taskCandidateUser("lv").singleResult();
        assertNotNull(taskOflv);
        assertEquals("串行审批", taskOflv.getName());
        taskService.claim(taskOflv.getId(),"user-self");
        taskService.complete(taskOflv.getId());


        Task taskOfcz=taskService.createTaskQuery().taskCandidateUser("cz").singleResult();
        assertNotNull(taskOfcz);
        assertEquals("串行审批", taskOfcz.getName());
        taskService.claim(taskOfcz.getId(),"user-self");
        taskService.complete(taskOfcz.getId());

        HistoryService historyService = processEngine.getHistoryService();
        long count = historyService.createHistoricProcessInstanceQuery().finished()
                .count();
        assertEquals(1, count);
    }

}
