package SSAAOPTest.Test004.Base;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/29
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Aspect
@EnableAspectJAutoProxy
public class BeanConfig {

    @DeclareParents(value = "SSAAOPTest.Test004.Base..*", defaultImpl = DoSthServiceImpl.class)
    public DoSthService doSthService;

}
