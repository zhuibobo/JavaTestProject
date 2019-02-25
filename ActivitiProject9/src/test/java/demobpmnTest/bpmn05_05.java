package demobpmnTest;

import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class bpmn05_05 {
    @Test
    public void testStartProcess() throws InterruptedException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        String bpmnFileName = "demobpmn/bpmn05-05.bpmn";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream("bpmn05-05.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName));
        Deployment deployment=deploymentBuilder.deploy();

        /*IdentityService identityService=processEngine.getIdentityService();
        Group group=identityService.newGroup("deptleader");
        group.setName("部门领导");
        group.setType("assignment");
        identityService.saveGroup(group);
        User user=identityService.newUser("zhuibobo");
        user.setFirstName("zhui");
        user.setLastName("bobo");
        user.setEmail("zhuibobo@qq.com");
        identityService.saveUser(user);
        identityService.createMembership("zhuibobo","deptleader");

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("bpmn05-05");

        TaskService taskService=processEngine.getTaskService();
        Task taskOfLeader=taskService.createTaskQuery().taskCandidateUser("zhuibobo").singleResult();
        assertNotNull(taskOfLeader);
        assertEquals("部门领导", taskOfLeader.getName());
        taskService.claim(taskOfLeader.getId(),"user-leader");
        taskService.complete(taskOfLeader.getId());*/

    }
}
