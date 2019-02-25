package demobpmnTest;

import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.bpmn.model.UserTask;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/7/19
 * To change this template use File | Settings | File Templates.
 */
public class BPMNModelUtility {

    static void PrintStartEventToOutgoing(Collection<FlowElement> flowElements) {
        for (FlowElement flowElement : flowElements) {
            //如果是任务节点
            if (flowElement instanceof StartEvent) {
                StartEvent startEvent = (StartEvent) flowElement;
                //获取出线信息
                List<SequenceFlow> incomingFlows = startEvent.getOutgoingFlows();
                for (SequenceFlow sequenceFlow : incomingFlows) {
                    System.out.println("射出线===>ID:["+sequenceFlow.getId() + "],ConditionExpression:[" + sequenceFlow.getConditionExpression() + "],Documentation:[" + sequenceFlow.getDocumentation() + "],SourceRef:["
                            + sequenceFlow.getSourceRef() + "],TargetRef:[" + sequenceFlow.getTargetRef() + "]");
                }
            }
        }
    }

    static void PrintUserTaskToOutgoing(FlowElement flowElement) {
        if (flowElement instanceof UserTask) {
            UserTask userTask = (UserTask) flowElement;
            //获取出线信息
            List<SequenceFlow> incomingFlows = userTask.getOutgoingFlows();
            for (SequenceFlow sequenceFlow : incomingFlows) {
                System.out.println("射出线===>ID:[" + sequenceFlow.getId() + "],ConditionExpression:[" + sequenceFlow.getConditionExpression() + "],Documentation:[" + sequenceFlow.getDocumentation() + "],SourceRef:["
                        + sequenceFlow.getSourceRef() + "],TargetRef:[" + sequenceFlow.getTargetRef() + "]");
            }
        }
    }

    static void PrintUserTaskInfo(FlowElement flowElement){
        if(flowElement instanceof UserTask){
            System.out.println("用户任务===>ID:["+flowElement.getId() + "],Name:[" + flowElement.getName() + "],Documentation:[" + flowElement.getDocumentation() + "]");
        }
    }
}
