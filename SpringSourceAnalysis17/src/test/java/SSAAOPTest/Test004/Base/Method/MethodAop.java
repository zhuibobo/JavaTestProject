package SSAAOPTest.Test004.Base.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.naming.Name;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/29
 * To change this template use File | Settings | File Templates.
 */
@Aspect
@Component
public class MethodAop {
    @Pointcut("execution(* SSAAOPTest.Test004.Base.Method.*.*(..)) && args(name)")
    public void pointcut(String name) {
    }

    @Before("pointcut(name)")
    public void beginTransaction(String name) {
        System.out.println("before beginTransaction");
    }

    @After("pointcut(name)")
    public void commit(String name) {
        System.out.println("after commit");
    }

    @AfterReturning(value="pointcut(name)",returning = "returnObject")
    public void afterReturning(JoinPoint joinPoint, Object returnObject,String name) {
        System.out.println("afterReturning "+returnObject);
        //returnObject="change para by afterReturning";
    }

    @AfterThrowing("pointcut(name)")
    public void afterThrowing(String name) {
        System.out.println("afterThrowing afterThrowing  rollback");
    }

    @Around("pointcut(name)")
    public Object around(ProceedingJoinPoint joinPoint,String name) throws Throwable {
        try {
            System.out.println("around begin ");
            System.out.println(joinPoint.getTarget());
            System.out.println(joinPoint.getArgs());
            return joinPoint.proceed(new Object[]{"change para"})+" around";
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("around finally");
        }
    }
}
