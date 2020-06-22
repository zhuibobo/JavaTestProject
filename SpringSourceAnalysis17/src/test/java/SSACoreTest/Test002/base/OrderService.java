package SSACoreTest.Test002.base;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/21
 * To change this template use File | Settings | File Templates.
 */
@Component
public class OrderService {
    public void init() {
        System.out.println("OrderService init...");
    }

    public void hello(){
        System.out.println("OrderService.hello");
    }
}
