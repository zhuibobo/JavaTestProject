package demobpmnTest;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/7/19
 * To change this template use File | Settings | File Templates.
 */
public class dev_bpmn004_model_resolve {

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
        //开始事件的所有射出线
        BpmnModel bpmnModel=repositoryService.getBpmnModel(processDefinition.getId());
        Process process=bpmnModel.getMainProcess();
        System.out.println(process.getDocumentation());
        BPMNModelUtility.PrintStartEventToOutgoing(process.getFlowElements());
    }

    @Test
    public void Resolve_V2(){
        ProcessDefinition processDefinition = 部署解析用的流程(repositoryService, runtimeService);
        //启动流程与环节处理之后,获取当前的环节
        Map<String,Object> paras=new HashMap<>();
        paras.put("actionName","U3");
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dev-bpmn004","Resolve_V2",paras);
        List<String> activityIds=runtimeService.getActiveActivityIds(processInstance.getId());
        BpmnModel bpmnModel=repositoryService.getBpmnModel(processDefinition.getId());
        //执行后的下一个节点
        FlowElement u3=bpmnModel.getMainProcess().getFlowElement(activityIds.get(0));
        BPMNModelUtility.PrintUserTaskInfo(u3);

        //接受UT3任务并执行
        Task task_u3=taskService.createTaskQuery().taskCandidateUser("ut3").singleResult();
        taskService.claim(task_u3.getId(),"ut3");
        taskService.complete(task_u3.getId());

        activityIds=runtimeService.getActiveActivityIds(processInstance.getId());
        FlowElement u5=bpmnModel.getMainProcess().getFlowElement(activityIds.get(0));
        BPMNModelUtility.PrintUserTaskInfo(u5);

        //接受UT5任务并执行
        Task task_u5=taskService.createTaskQuery().taskCandidateUser("ut5").singleResult();
        taskService.claim(task_u5.getId(),"ut5");
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applyUser", "employee1");
        variables.put("days", 3);
        variables.put("approved","approved");
        taskService.complete(task_u5.getId(),variables);

        activityIds=runtimeService.getActiveActivityIds(processInstance.getId());
        FlowElement u6=bpmnModel.getMainProcess().getFlowElement(activityIds.get(0));
        BPMNModelUtility.PrintUserTaskInfo(u6);

        //接收UT6任务,并执行
        Task task_u6=taskService.createTaskQuery().taskCandidateUser("ut6").singleResult();
        taskService.claim(task_u6.getId(),"ut6");
        taskService.complete(task_u6.getId());
        activityIds=runtimeService.getActiveActivityIds(processInstance.getId());
        FlowElement u7=bpmnModel.getMainProcess().getFlowElement(activityIds.get(0));
        BPMNModelUtility.PrintUserTaskInfo(u7);

        //接收UT7任务,并执行
        Task task_u7=taskService.createTaskQuery().taskCandidateUser("ut7").singleResult();
        taskService.claim(task_u7.getId(),"ut7");
        taskService.complete(task_u7.getId());

        /*activityIds=runtimeService.getActiveActivityIds(processInstance.getId());
        FlowElement toendbefore=bpmnModel.getMainProcess().getFlowElement(activityIds.get(0));
        BPMNModelUtility.PrintUserTaskToOutgoing(toendbefore);
        taskService.complete(task_u5.getId());*/
        //FlowElement u5=bpmnModel.getMainProcess().getFlowElement(activityIds.get(0));
    }

    private ProcessDefinition 部署解析用的流程(RepositoryService repositoryService, RuntimeService runtimeService) {
        String bpmnFileName = "demobpmn/dev-bpmn004-model-resolve.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("dev-bpmn004-model-resolve.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        Deployment deployment=deploymentBuilder.deploy();

        //runtimeService.startProcessInstanceByKey("dev-bpmn004","Resolve_V1");
        return repositoryService.createProcessDefinitionQuery().processDefinitionKey("dev-bpmn004").latestVersion().singleResult();
    }
}
