package demobpmnTest;

import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/7/20
 * To change this template use File | Settings | File Templates.
 */
public class ServiceTaskDemo1 implements JavaDelegate {

    private Expression text1;

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("serviceTask已经执行已经执行！");
        String value1 = (String) text1.getValue(execution);
        System.out.println(value1);
        String applyUser= (String) execution.getVariable("applyUser");
        System.out.println("serviceTask中获取变量！"+applyUser);
    }
}
