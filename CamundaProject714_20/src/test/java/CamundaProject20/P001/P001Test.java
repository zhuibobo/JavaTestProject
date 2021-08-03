package CamundaProject20.P001;

import CamundaProject20.CamundaUtility;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2019/11/21
 * To change this template use File | Settings | File Templates.
 */
public class P001Test extends CamundaProject20.MainApplicationTest {
    @Test
    public void m1() throws InterruptedException {
        Assert.assertEquals(1,1);
        System.out.println("***************Test---m1***********************");
        System.out.println(CamundaUtility.getProcessEngine());
    }
}
