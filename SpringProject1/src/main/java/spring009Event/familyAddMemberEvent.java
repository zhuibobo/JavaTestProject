package spring009Event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/9
 * @Description:
 * @Version 1.0.0
 */
public class familyAddMemberEvent extends ApplicationEvent {

    public familyAddMemberEvent(Object source) {
        super(source);
    }
}
