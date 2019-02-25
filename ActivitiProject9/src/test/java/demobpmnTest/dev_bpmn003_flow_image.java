package demobpmnTest;

import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/7/19
 * To change this template use File | Settings | File Templates.
 */
public class dev_bpmn003_flow_image {

    @Test
    public void testFlowImage1() throws InterruptedException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        HistoryService historyService=processEngine.getHistoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //部署模型1
        部署模型1(repositoryService);

        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("dev-bpmn003").latestVersion().singleResult();
        //获取流程xml模型
        BpmnModel bpmnModel=repositoryService.getBpmnModel(processDefinition.getId());
        ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinition.getId());

        ProcessInstance processInstanceM2=runtimeService.startProcessInstanceByKey("dev-bpmn003","sss");

        List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceM2.getProcessInstanceId()).list();
        //高亮环节id集合
        List<String> highLightedActivitis = new ArrayList<String>();

        //高亮线路id集合
        List<String> highLightedFlows = new ArrayList<>();
        highLightedFlows.add("flow1");

        for(HistoricActivityInstance tempActivity : highLightedActivitList){
            String activityId = tempActivity.getActivityId();
            highLightedActivitis.add(activityId);
        }

        //InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis,highLightedFlows,"宋体","宋体",null,1.0);
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel,"png",highLightedActivitis,highLightedFlows,"宋体","宋体","宋体",null,1.0);

        File targetFile = new File("D://dev-bpmn003-test1.png");
        OutputStream outStream = null;
        byte[] buffer = null;
        try {
            buffer = new byte[imageStream.available()];
            imageStream.read(buffer);
            outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void 部署模型1(RepositoryService repositoryService) {
        System.out.println("------------------------------------模型1------------------------------------");
        String bpmnFileName = "demobpmn/dev-bpmn003-flow-image.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("dev-bpmn003-flow-image.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        Deployment deployment=deploymentBuilder.deploy();

        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("dev-bpmn003").latestVersion().singleResult();
        //获取流程xml模型
        BpmnModel bpmnModel=repositoryService.getBpmnModel(processDefinition.getId());
        //bpmnModel.getFlowElement()
        Process process = bpmnModel.getProcesses().get(0);
        //获取所有的FlowElement信息
        Collection<FlowElement> flowElements = process.getFlowElements();
        BPMNModelUtility.PrintStartEventToOutgoing(flowElements);
    }
}
