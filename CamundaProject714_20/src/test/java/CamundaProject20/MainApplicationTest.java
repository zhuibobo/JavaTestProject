package CamundaProject20;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2019/11/21
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MainApplication.class })
public class MainApplicationTest {

    @Autowired
    private MainApplication application;

    public void consolelog(Object object){
        System.out.println("CamundaProject20-Consolelog:"+object);
    }

}