package demobpmnTest;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/7/18
 * To change this template use File | Settings | File Templates.
 */
public class dev_bpmn002_redeploy {

    @Test
    public void testReDeployOnly() throws InterruptedException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //部署模型1
        部署模型1(repositoryService);

        //重新部署模型2
        重新部署模型2(repositoryService);
    }

    @Test
    public void testReDeployRun() throws InterruptedException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService=processEngine.getTaskService();
        //部署模型1
        部署模型1(repositoryService);

        //使用模型1启动流程
        System.out.println("------------------------------------使用模型1启动流程------------------------------------");
        ProcessInstance processInstanceM1=runtimeService.startProcessInstanceByKey("dev-bpmn002","sss");
        //TaskService taskService=processEngine.getTaskService();
        Task taskOfHr=taskService.createTaskQuery().taskCandidateUser("hr").singleResult();
        Assert.assertEquals("人力资源",taskOfHr.getName());
        System.out.println(taskOfHr.getName());


        //重新部署模型2
        重新部署模型2(repositoryService);

        //使用模型2启动流程
        System.out.println("------------------------------------使用模型2启动流程------------------------------------");
        Map<String,Object> paras=new HashMap<>();
        paras.put("actionName","送财务部");
        ProcessInstance processInstanceM2=runtimeService.startProcessInstanceByKey("dev-bpmn002","sss",paras);
        Task taskOfcw=taskService.createTaskQuery().taskCandidateUser("cw").singleResult();
        Assert.assertEquals("财务部",taskOfcw.getName());
        System.out.println(taskOfcw.getName());

        //继续模型1的流程处理
        System.out.println("------------------------------------继续模型1的流程处理------------------------------------");
        taskService.claim(taskOfHr.getId(),"hr");
        taskService.complete(taskOfHr.getId());
        Task taskOfdept=taskService.createTaskQuery().taskCandidateUser("dept").singleResult();
        Assert.assertEquals("部门",taskOfdept.getName());
        System.out.println(taskOfdept.getName());

        //继续模型2的流程处理
        System.out.println("------------------------------------继续模型2的流程处理------------------------------------");
        taskService.claim(taskOfcw.getId(),"cw");
        taskService.complete(taskOfcw.getId());
        Task taskOfGC=taskService.createTaskQuery().taskCandidateUser("GC").singleResult();
        Assert.assertEquals("工厂2",taskOfGC.getName());
        System.out.println(taskOfGC.getName());

        //保存模型1和模型2的流程图
        /*InputStream is=repositoryService.getProcessDiagram(processInstanceM1.get());
        File targetFile = new File("D://M1.png");
        OutputStream outStream = null;
        byte[] buffer = null;
        try {
            buffer = new byte[is.available()];
            is.read(buffer);
            outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void 重新部署模型2(RepositoryService repositoryService) {
        System.out.println("------------------------------------模型2------------------------------------");
        String bpmnFileName = "demobpmn/dev-bpmn002-redeploy-002.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("dev-bpmn002-redeploy-002.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        Deployment deployment = deploymentBuilder.deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("dev-bpmn002").latestVersion().singleResult();
        //获取流程xml模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        Process process = bpmnModel.getProcesses().get(0);
        //获取所有的FlowElement信息
        Collection<FlowElement> flowElements = process.getFlowElements();
        BPMNModelUtility.PrintStartEventToOutgoing(flowElements);
    }

    private void 部署模型1(RepositoryService repositoryService) {
        System.out.println("------------------------------------模型1------------------------------------");
        String bpmnFileName = "demobpmn/dev-bpmn002-redeploy-001.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("dev-bpmn002-redeploy-001.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        Deployment deployment=deploymentBuilder.deploy();

        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("dev-bpmn002").latestVersion().singleResult();
        //获取流程xml模型
        BpmnModel bpmnModel=repositoryService.getBpmnModel(processDefinition.getId());
        //bpmnModel.getFlowElement()
        Process process = bpmnModel.getProcesses().get(0);
        //获取所有的FlowElement信息
        Collection<FlowElement> flowElements = process.getFlowElements();
        BPMNModelUtility.PrintStartEventToOutgoing(flowElements);
    }
}
