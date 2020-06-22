package SSAAOPTest.Test004.Base;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/29
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DoSthServiceImpl implements DoSthService {

    @Override
    public void doSth() {
        System.out.println("do sth ....");
    }
}