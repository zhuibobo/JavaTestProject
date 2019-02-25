package demobpmnTest;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //开始事件的所有射出线
        BpmnModel bpmnModel=repositoryService.getBpmnModel(processDefinition.getId());
        Process process=bpmnModel.getMainProcess();
        System.out.println(process.getDocumentation());
        BPMNModelUtility.PrintStartEventToOutgoing(process.getFlowElements());
    }

    private ProcessDefinition 部署解析用的流程(RepositoryService repositoryService, RuntimeService runtimeService) {
        String bpmnFileName = "demobpmn/dev-bpmn004-model-resolve.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("dev-bpmn004-model-resolve.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        Deployment deployment=deploymentBuilder.deploy();

        //runtimeService.startProcessInstanceByKey("dev-bpmn004","Resolve_V1");
        return repositoryService.createProcessDefinitionQuery().processDefinitionKey("dev-bpmn004").latestVersion().singleResult();
    }

}
