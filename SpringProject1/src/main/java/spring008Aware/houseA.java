package spring008Aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

/**
 * Created by zhuangrb on 2017/12/5.
 */

public class houseA implements house,BeanNameAware {

    family familyA;

    Resource baiduResource;

    public family getFamilyA() {
        return familyA;
    }

    public void setFamilyA(family familyA) {
        this.familyA = familyA;
    }

    @Override
    public String getFamilyName(){
        return familyA.getName();
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware:"+s);
    }
}
