package spring003Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by zhuangrb on 2017/12/5.
 */
@Aspect
@Component
public class logAspect {
    //https://www.mkyong.com/spring3/spring-aop-aspectj-annotation-example/

    @Pointcut("@annotation(spring003Aop.Action)")
    public void annotationPointCut(){}

    /*try{
        try{
            //@Before
            method.invoke(..);
        }finally{
            //@After
        }
        //@AfterReturning
    }catch(){
        //@AfterThrowing
    }*/

    @AfterReturning(
            pointcut = "annotationPointCut()",
            returning= "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("******");
        System.out.println("logAfterReturning() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result);
        System.out.println("******");

    }

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint){
        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
        Method method=signature.getMethod();
        Action action=method.getAnnotation(Action.class);
        System.out.println("之后注解拦截"+action.name());
    }

    @Before("execution(* spring003Aop.housebyMethodName.*(..))()")
    public void before(JoinPoint joinPoint){
        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
        Method method=signature.getMethod();
        //Action action=method.getAnnotation(Action.class);
        System.out.println("之前方法拦截");
    }

}
