package CamundaProject13;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2019/11/21
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CamundaUtility {

    public static ProcessEngine processEngine;

    @Autowired
    public CamundaUtility(ProcessEngine _processEngine) {
        processEngine=_processEngine;
    }

    public static ProcessEngine getProcessEngine() {
        return processEngine;
    }

    public static void setProcessEngine(ProcessEngine processEngine) {
        CamundaUtility.processEngine = processEngine;
    }
}
