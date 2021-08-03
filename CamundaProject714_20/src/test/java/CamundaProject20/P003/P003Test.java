package CamundaProject20.P003;

import CamundaProject20.CamundaUtility;
import CamundaProject20.MainApplicationTest;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2019/11/22
 * To change this template use File | Settings | File Templates.
 */
public class P003Test extends MainApplicationTest {



    @Test
    public void P003_001_bpmn() throws InterruptedException, IOException {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().name("P003Test-NAME").source("P003Test-Source").tenantId("P003Test-TenantId").addClasspathResource("bpmn/P003_001.bpmn").addClasspathResource("bpmn/P003_001.png").deploy();

        RuntimeService runtimeService=processEngine.getRuntimeService();

        ProcessInstanceQuery processInstanceQuery = deleteAllProcessInstance(runtimeService);

        Map<String,Object> vars=new HashMap<>();
        vars.put("UserId","User001");
        vars.put("UserName","zhuibobo");
        String thisVar= UUID.randomUUID().toString();
        vars.put("thisVar",thisVar);
        ProcessInstance process_p003Test = runtimeService.startProcessInstanceByKey("Process_P003Test",vars);

        TaskService taskService=processEngine.getTaskService();
        TaskQuery taskQueryUser001 = taskService.createTaskQuery().taskAssignee("User001");

        for (Task task : taskQueryUser001.list()) {
            consolelog(task.getName());
            Assert.assertEquals(task.getName(),"请假申请");
            Object thisVar1 = runtimeService.getVariableLocal(task.getExecutionId(), "thisVar");
            if(thisVar.equals(String.valueOf(thisVar1))){
                consolelog("------------------当前单元测试创建的方法-----------------");
                consolelog(thisVar);
                vars=new HashMap<>();
                vars.put("UserId","User002");
                vars.put("UserName","alex");

                taskService.complete(task.getId(),vars);

                TaskQuery taskQueryUser002 = taskService.createTaskQuery().taskAssignee("User002");
                Task taskUser002 = taskQueryUser002.singleResult();
                consolelog(taskUser002.getName());
                Assert.assertEquals(taskUser002.getName(),"经理审批");
                taskService.complete(taskUser002.getId());

                ProcessInstance processInstance = processInstanceQuery.processInstanceId(taskUser002.getProcessInstanceId()).singleResult();
                Assert.assertEquals(null,processInstance);

                HistoryService historyService = processEngine.getHistoryService();
                HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
                HistoricProcessInstance historicProcessInstance = historicProcessInstanceQuery.processInstanceId(taskUser002.getProcessInstanceId()).singleResult();

                Assert.assertNotEquals(null,historicProcessInstance.getEndTime());

                consolelog("------------------当前单元测试创建的方法-----------------");
            }
        }


    }


    @Test
    public void P003_002_bpmn() throws InterruptedException, IOException {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().name("P003_002_bpmn").source("P003Test-Source").tenantId("P003Test-TenantId").addClasspathResource("bpmn/P003_002.bpmn").deploy();

        RuntimeService runtimeService=processEngine.getRuntimeService();

        ProcessInstanceQuery processInstanceQuery = deleteAllProcessInstance(runtimeService);

        Map<String,Object> vars=new HashMap<>();
        vars.put("UserId","User001");
        vars.put("UserName","zhuibobo");
        String thisVar= UUID.randomUUID().toString();
        vars.put("thisVar",thisVar);
        ProcessInstance process_p003Test = runtimeService.startProcessInstanceByKey("P003_002_bpmn",vars);

        TaskService taskService=processEngine.getTaskService();
        TaskQuery taskQueryUser001 = taskService.createTaskQuery().taskAssignee("User001");

        Task task = taskQueryUser001.singleResult();
        Assert.assertEquals(task.getName(),"请假申请");
        vars=new HashMap<>();
        vars.put("UserId","User002");
        vars.put("UserName","alex");
        taskService.complete(task.getId(),vars);

        TaskQuery taskQuery = taskService.createTaskQuery().processInstanceId(process_p003Test.getId());
        Task 组长审核任务 = taskQuery.singleResult();
        Assert.assertEquals(组长审核任务.getName(),"组长审核");
        taskService.setAssignee(组长审核任务.getId(),"User002");

        //后选人
        组长审核任务 = taskService.createTaskQuery().taskAssignee("User002").singleResult();
        Assert.assertEquals(组长审核任务.getName(),"组长审核");
        vars=new HashMap<>();
        vars.put("UserGroup","User003,User004,User005");
        vars.put("UserName","alex3");
        taskService.complete(组长审核任务.getId(),vars);

        //签收
        Task taskUser003 = taskService.createTaskQuery().taskCandidateUser("User003").singleResult();
        Assert.assertEquals(taskUser003.getName(),"经理审批");
        Task taskUser004 = taskService.createTaskQuery().taskCandidateUser("User004").singleResult();
        Assert.assertEquals(taskUser004.getName(),"经理审批");
        Task taskUser005 = taskService.createTaskQuery().taskCandidateUser("User005").singleResult();
        Assert.assertEquals(taskUser005.getName(),"经理审批");

        taskService.claim(taskUser003.getId(),"User003");
        try {
            taskService.claim(taskUser004.getId(), "User004");
            taskService.claim(taskUser005.getId(), "User005");
        }
        catch (TaskAlreadyClaimedException taskAlreadyClaimedException){
            if (taskAlreadyClaimedException.getMessage().indexOf("already claimed by someone")>0){
                Assert.assertSame("1","1");
            }
            else {
                Assert.assertSame("1","2");
            }
        }

        //取消签收
        taskService.setAssignee(taskUser003.getId(),null);
        //添加后选人
        taskService.addCandidateUser(taskUser003.getId(),"User006");
        //重新签收
        taskService.claim(taskUser004.getId(), "User006");
        Task 经理审批任务 = taskService.createTaskQuery().taskAssignee("User006").singleResult();
        Assert.assertEquals(经理审批任务.getName(),"经理审批");

    }

    @Test
    public void P003_003_bpmn() throws InterruptedException, IOException {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().name("P003_003_bpmn").source("P003Test-Source").tenantId("P003Test-TenantId").addClasspathResource("bpmn/P003_003.bpmn").deploy();

        RuntimeService runtimeService=processEngine.getRuntimeService();

        ProcessInstanceQuery processInstanceQuery = deleteAllProcessInstance(runtimeService);

        Map<String,Object> vars=new HashMap<>();
        vars.put("UserId","User001");
        vars.put("UserName","zhuibobo");
        String thisVar= UUID.randomUUID().toString();
        vars.put("thisVar",thisVar);
        ProcessInstance process_p003Test = runtimeService.startProcessInstanceByKey("P003_003_bpmn",vars);

        TaskService taskService=processEngine.getTaskService();
        TaskQuery taskQueryUser001 = taskService.createTaskQuery().taskAssignee("User001");

        Task task = taskQueryUser001.singleResult();
        Assert.assertEquals(task.getName(),"请假申请");
        vars=new HashMap<>();
        vars.put("UserId","User002");
        vars.put("UserName","alex");
        taskService.complete(task.getId(),vars);


    }

    @Test
    public void P003_004_bpmn() throws InterruptedException, IOException {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().name("P003_004_bpmn").source("P003Test-Source").tenantId("P003Test-TenantId").addClasspathResource("bpmn/P003_004.bpmn").deploy();

        RuntimeService runtimeService=processEngine.getRuntimeService();

        ProcessInstanceQuery processInstanceQuery = deleteAllProcessInstance(runtimeService);

        Map<String,Object> vars=new HashMap<>();
        vars.put("UserId","User001");
        vars.put("UserName","zhuibobo");
        String thisVar= UUID.randomUUID().toString();
        vars.put("thisVar",thisVar);
        ProcessInstance process_p003Test = runtimeService.startProcessInstanceByKey("P003_004_bpmn",vars);

        TaskService taskService=processEngine.getTaskService();
        TaskQuery taskQueryUser001 = taskService.createTaskQuery().taskAssignee("User001");

        Task task = taskQueryUser001.singleResult();
        Assert.assertEquals(task.getName(),"请假申请");
        vars=new HashMap<>();
        vars.put("UserId","User002");
        vars.put("UserName","alex");
        vars.put("level",3);
        List<String> assigneeList=new ArrayList<>();
        assigneeList.add("User003");
        assigneeList.add("User004");
        assigneeList.add("User005");
        assigneeList.add("User006");
        assigneeList.add("User007");
        vars.put("assigneeList",assigneeList);
        taskService.complete(task.getId(),vars);

        //串行状态,需要按照User003,User004,User005,User006,User007进行处理,3处理前,4无法获取到任务
        TaskQuery taskQueryUser004 = taskService.createTaskQuery().taskAssignee("User004");
        task=taskQueryUser004.singleResult();
        Assert.assertEquals(null,task);

        //按照User003,User004,User005,User006,User007进行处理
        TaskQuery taskQueryUser003 = taskService.createTaskQuery().taskAssignee("User003");
        task=taskQueryUser003.singleResult();

        //多人实例数量
        Object nrOfInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfInstances");
        //已经完成的实例数量
        Object nrOfCompletedInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfCompletedInstances");
        //当前激活的实例数量
        Object nrOfActiveInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfActiveInstances");
        Assert.assertEquals(5,Integer.parseInt(String.valueOf(nrOfInstances)));
        Assert.assertEquals(0,Integer.parseInt(String.valueOf(nrOfCompletedInstances)));
        Assert.assertEquals(1,Integer.parseInt(String.valueOf(nrOfActiveInstances)));

        Assert.assertEquals("组长审核1",task.getName());
        taskService.complete(task.getId());

        taskQueryUser004 = taskService.createTaskQuery().taskAssignee("User004");
        task=taskQueryUser004.singleResult();

        //多人实例数量
         nrOfInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfInstances");
        //已经完成的实例数量
         nrOfCompletedInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfCompletedInstances");
        //当前激活的实例数量
         nrOfActiveInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfActiveInstances");
        Assert.assertEquals(5,Integer.parseInt(String.valueOf(nrOfInstances)));
        Assert.assertEquals(1,Integer.parseInt(String.valueOf(nrOfCompletedInstances)));
        Assert.assertEquals(1,Integer.parseInt(String.valueOf(nrOfActiveInstances)));

        Assert.assertEquals("组长审核1",task.getName());
        taskService.complete(task.getId());

        TaskQuery taskQueryUser005 = taskService.createTaskQuery().taskAssignee("User005");
        task=taskQueryUser005.singleResult();
        taskService.complete(task.getId());

        TaskQuery taskQueryUser006 = taskService.createTaskQuery().taskAssignee("User006");
        task=taskQueryUser006.singleResult();
        taskService.complete(task.getId());

        TaskQuery taskQueryUser007 = taskService.createTaskQuery().taskAssignee("User007");
        task=taskQueryUser007.singleResult();
        //多人实例数量
        nrOfInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfInstances");
        //已经完成的实例数量
        nrOfCompletedInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfCompletedInstances");
        //当前激活的实例数量
        nrOfActiveInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfActiveInstances");
        Assert.assertEquals(5,Integer.parseInt(String.valueOf(nrOfInstances)));
        Assert.assertEquals(4,Integer.parseInt(String.valueOf(nrOfCompletedInstances)));
        Assert.assertEquals(1,Integer.parseInt(String.valueOf(nrOfActiveInstances)));

        vars=new HashMap<>();
        vars.put("UserId","User002");
        vars.put("UserName","alex");
        vars.put("level",3);
        assigneeList=new ArrayList<>();
        assigneeList.add("User010");
        assigneeList.add("User011");
        assigneeList.add("User012");
        assigneeList.add("User013");
        assigneeList.add("User014");
        vars.put("assigneeList",assigneeList);
        taskService.complete(task.getId(),vars);

        TaskQuery taskQueryUser014 = taskService.createTaskQuery().taskAssignee("User014");
        task=taskQueryUser014.singleResult();
        Assert.assertEquals("经理审批",task.getName());
        taskService.complete(task.getId());

        TaskQuery taskQueryUser010 = taskService.createTaskQuery().taskAssignee("User010");
        task=taskQueryUser010.singleResult();
        Assert.assertEquals("经理审批",task.getName());
        taskService.complete(task.getId());

        TaskQuery taskQueryUser011 = taskService.createTaskQuery().taskAssignee("User011");
        task=taskQueryUser011.singleResult();
        Assert.assertEquals("经理审批",task.getName());
        //多人实例数量
        nrOfInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfInstances");
        //已经完成的实例数量
        nrOfCompletedInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfCompletedInstances");
        //当前激活的实例数量
        nrOfActiveInstances = runtimeService.getVariable(task.getExecutionId(), "nrOfActiveInstances");
        Assert.assertEquals(5,Integer.parseInt(String.valueOf(nrOfInstances)));
        Assert.assertEquals(2,Integer.parseInt(String.valueOf(nrOfCompletedInstances)));
        Assert.assertEquals(3,Integer.parseInt(String.valueOf(nrOfActiveInstances)));
        taskService.complete(task.getId());

        TaskQuery taskQueryUser012 = taskService.createTaskQuery().taskAssignee("User012");
        task=taskQueryUser012.singleResult();
        Assert.assertEquals("经理审批",task.getName());
        taskService.complete(task.getId());

        TaskQuery taskQueryUser013 = taskService.createTaskQuery().taskAssignee("User013");
        task=taskQueryUser013.singleResult();
        Assert.assertEquals("经理审批",task.getName());
        taskService.complete(task.getId());

        ProcessInstance processInstance = processInstanceQuery.processInstanceId(task.getProcessInstanceId()).singleResult();
        Assert.assertEquals(null,processInstance);
    }

    @Test
    public void P003_005_bpmn() throws InterruptedException, IOException {
        ProcessEngine processEngine= CamundaUtility.getProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().name("P003_005_bpmn").source("P003Test-Source").tenantId("P003Test-TenantId").addClasspathResource("bpmn/P003_005.bpmn").deploy();

        RuntimeService runtimeService=processEngine.getRuntimeService();

        ProcessInstanceQuery processInstanceQuery = deleteAllProcessInstance(runtimeService);

        Map<String,Object> vars=new HashMap<>();
        vars.put("UserId","User001");
        vars.put("UserName","zhuibobo");
        String thisVar= UUID.randomUUID().toString();
        vars.put("thisVar",thisVar);

        List<ProcessDefinition> processDefinitionList1=repositoryService.createProcessDefinitionQuery().startableByUser("csu").latestVersion().list();
        List<ProcessDefinition> processDefinitionList2=repositoryService.createProcessDefinitionQuery().startableByUser("aaa").latestVersion().list();
        List<ProcessDefinition> processDefinitionList3=repositoryService.createProcessDefinitionQuery().startableByUser("bbb").latestVersion().list();
        //repositoryService.createProcessDefinitionQuery().s
        ProcessInstance process_p003Test = runtimeService.startProcessInstanceByKey("P003_003_bpmn",vars);

        TaskService taskService=processEngine.getTaskService();
        TaskQuery taskQueryUser001 = taskService.createTaskQuery().taskAssignee("User001");

        Task task = taskQueryUser001.singleResult();
        Assert.assertEquals(task.getName(),"请假申请");
        vars=new HashMap<>();
        vars.put("UserId","User002");
        vars.put("UserName","alex");
        taskService.complete(task.getId(),vars);
    }

    public static ProcessInstanceQuery deleteAllProcessInstance(RuntimeService runtimeService) {
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        for (ProcessInstance processInstance : processInstanceQuery.list()) {
            runtimeService.deleteProcessInstance(processInstance.getId(),"unit-test");
        }
        return processInstanceQuery;
    }
}
