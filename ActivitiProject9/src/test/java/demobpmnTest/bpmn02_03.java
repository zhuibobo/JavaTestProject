package demobpmnTest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Assert;
import org.junit.Test;

public class bpmn02_03 {
    @Test
    public void testStartProcess() throws Exception {
        //创建流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //ProcessEngines.get
        //部署流程定义文件
        RepositoryService repositoryService=processEngine.getRepositoryService();
        String bpmnFileName = "demobpmn/bpmn02-03.bpmn";
        Deployment deployment=repositoryService
                .createDeployment()
                .addInputStream(
                        "bpmn02-03.bpmn",
                        this.getClass().getClassLoader()
                                .getResourceAsStream(bpmnFileName)).deploy();
        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
        Assert.assertEquals("leavesayhellow",processDefinition.getKey());
        RuntimeService runtimeService=processEngine.getRuntimeService();
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("leavesayhellow");
        Assert.assertNotNull(processInstance);
        System.out.println("pid="+processInstance.getId()+",pdid="+processInstance.getProcessDefinitionId());
    }
}
