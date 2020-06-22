package SSAAOPTest.Test004.Base.Annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/29
 * To change this template use File | Settings | File Templates.
 */
@Aspect
@Component
public class AnnotationAop {

    @Pointcut(value = "@annotation(log)", argNames = "log")
    public void pointcut(Log log) {
    }

    @Around(value = "pointcut(log)", argNames = "joinPoint,log")
    public Object around(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        try {
            //System.out.println();
            System.out.println("around begin "+log.value());
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            System.out.println("around end "+log.value());
        }
    }

    @Before("@annotation(SSAAOPTest.Test004.Base.Annotation.Log)")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        Log log1=joinPoint.getTarget().getClass().getMethods()[0].getAnnotation(Log.class);
        System.out.println("注解式拦截 " + log1.value());
    }
}
