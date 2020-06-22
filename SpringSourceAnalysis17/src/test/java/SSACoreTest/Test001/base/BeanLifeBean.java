package SSACoreTest.Test001.base;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/21
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BeanLifeBean {
    @PostConstruct
    public void start(){
        System.out.println("AnnotationBean start");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("AnnotationBean start");
    }

    public void send() {
        System.out.println("I am send method from BeanLifeBean!");
    }
}
