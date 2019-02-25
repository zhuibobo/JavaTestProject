package spring004ConditionBean;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/7
 * @Description:
 * @Version 1.0.0
 */
public class ConditionalDemo4 implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return false;
    }
}
