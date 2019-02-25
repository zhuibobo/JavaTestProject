package spring003Aop;

/**
 * Created by zhuangrb on 2017/12/5.
 */

public class housebyAnnotation {

    family familyA;

    @Action(name="设定了拦截操作!")
    public String getFamilyName(){
        System.out.println("getFamilyName被调用");
        return familyA.getName();
    }
}
