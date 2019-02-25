package spring011AopImplInteface;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/10
 * @Description:
 * @Version 1.0.0
 */

@Aspect
public class AOPAspect {

    @DeclareParents(value = "spring011AopImplInteface.house+",defaultImpl = defaultnewhouse.class)
    public static inewhouse newhouse;
}
