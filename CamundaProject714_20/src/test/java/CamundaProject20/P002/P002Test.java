package CamundaProject20.P002;

import CamundaProject20.CamundaUtility;
import CamundaProject20.MainApplicationTest;
import org.apache.commons.io.FileUtils;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2019/11/21
 * To change this template use File | Settings | File Templates.
 */
public class P002Test extends MainApplicationTest {


    /*
    * Result 0:	insert into ACT_RE_DEPLOYMENT(ID_, NAME_, DEPLOY_TIME_, SOURCE_, TENANT_ID_) values(?, ?, ?, ?, ?)	Update counts: [1]
    * Result 1:	insert into ACT_GE_BYTEARRAY( ID_, NAME_, BYTES_, DEPLOYMENT_ID_, GENERATED_, TENANT_ID_, TYPE_, CREATE_TIME_, REV_) values ( ?, ?, ?, ?, ?, ?, ?, ?, 1)	Update counts: [1]
    * Result 2:	insert into ACT_RE_PROCDEF(ID_, CATEGORY_, NAME_, KEY_, VERSION_, DEPLOYMENT_ID_, RESOURCE_NAME_, DGRM_RESOURCE_NAME_, HAS_START_FORM_KEY_, SUSPENSION_STATE_, TENANT_ID_, VERSION_TAG_, HISTORY_TTL_, STARTABLE_, REV_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1 )	Update counts: [1]
    * */
    @Test
    public void createDeployment() throws InterruptedException, IOException {
        ProcessEngine processEngine=CamundaUtility.getProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().name("P002Test-NAME").source("P002Test-Source").tenantId("P002Test-TenantId").addClasspathResource("bpmn/P002_001.bpmn").addClasspathResource("bpmn/P002_001.png").deploy();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        //select * from ACT_RE_PROCDEF where ID_='Process_0rdfusd:1:07709a62-1b47-11eb-9d5b-005056c00001';
        InputStream processModel = repositoryService.getProcessModel("Process_0rdfusd:1:07709a62-1b47-11eb-9d5b-005056c00001");
        FileUtils.copyInputStreamToFile(processModel,new File("D:\\JavaProject\\JavaTestProject\\CamundaProject714_20\\src\\main\\resources\\bpmn\\P002_001_Copy.bpmn"));
        InputStream processDiagram = repositoryService.getProcessDiagram("Process_0rdfusd:1:07709a62-1b47-11eb-9d5b-005056c00001");
        System.out.println(processDiagram.available());
        ProcessDefinition process_0rdfusd = repositoryService.createProcessDefinitionQuery().processDefinitionKey("Process_0rdfusd").latestVersion().singleResult();
        BpmnModelInstance bpmnModelInstance = repositoryService.getBpmnModelInstance(process_0rdfusd.getId());
        System.out.println(bpmnModelInstance.getDefinitions().getExtensions());
        UserTask task_1tbacor = bpmnModelInstance.getModelElementById("Task_1tbacor");
        ExtensionElements extensionElements = task_1tbacor.getExtensionElements();
        Collection<CamundaProperty> camundaProperties = task_1tbacor.getExtensionElements().getElementsQuery().filterByType(CamundaProperties.class).singleResult().getCamundaProperties();
        for (CamundaProperty camundaProperty : camundaProperties) {
            System.out.println(camundaProperty.getCamundaValue());
        }

        //task_1tbacor.getChildElementsByType(UserTask.class)
    }
}
