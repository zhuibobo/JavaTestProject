package demobpmnTest;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ChangeActivityStateBuilder;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/7/20
 * To change this template use File | Settings | File Templates.
 */
public class dev_bpmn005_send_recycling {

    ProcessEngine processEngine;
    RepositoryService repositoryService;
    RuntimeService runtimeService;
    TaskService taskService;

    @Before
    public void Init(){
        processEngine = ProcessEngines.getDefaultProcessEngine();
        repositoryService = processEngine.getRepositoryService();
        runtimeService=processEngine.getRuntimeService();
        taskService=processEngine.getTaskService();
    }

    @Test
    public void Resolve_V1(){
        ProcessDefinition processDefinition = 部署解析用的流程(repositoryService, runtimeService);

        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dev-bpmn005");

        Task task_ut1=taskService.createTaskQuery().taskCandidateUser("ut1").singleResult();
        taskService.claim(task_ut1.getId(),"ut1");
        taskService.complete(task_ut1.getId());

        Task task_ut2=taskService.createTaskQuery().taskCandidateUser("ut2").singleResult();
        System.out.println(task_ut2.getName());

        BpmnModel bpmnModel=repositoryService.getBpmnModel(processDefinition.getId());
        String activityId=runtimeService.getActiveActivityIds(processInstance.getId()).get(0);
        ChangeActivityStateBuilder changeActivityStateBuilder=runtimeService.createChangeActivityStateBuilder().processInstanceId(processInstance.getProcessInstanceId());
        changeActivityStateBuilder.moveActivityIdTo(activityId,"usertask1").changeState();

        activityId=runtimeService.getActiveActivityIds(processInstance.getId()).get(0);
        System.out.println(activityId);

        task_ut1=taskService.createTaskQuery().taskCandidateUser("ut1").singleResult();
        System.out.println(task_ut1.getName());
    }

    private ProcessDefinition 部署解析用的流程(RepositoryService repositoryService, RuntimeService runtimeService) {
        String bpmnFileName = "demobpmn/dev-bpmn005-send-recycling.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("dev-bpmn005-send-recycling.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        Deployment deployment=deploymentBuilder.deploy();
        //runtimeService.startProcessInstanceByKey("dev-bpmn004","Resolve_V1");
        return repositoryService.createProcessDefinitionQuery().processDefinitionKey("dev-bpmn005").latestVersion().singleResult();
    }

}
