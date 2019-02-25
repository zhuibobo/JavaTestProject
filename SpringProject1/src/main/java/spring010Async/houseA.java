package spring010Async;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by zhuangrb on 2017/12/5.
 */

public class houseA implements house {

    family familyA;

    public family getFamilyA() {
        return familyA;
    }

    public void setFamilyA(family familyA) {
        this.familyA = familyA;
    }

    @Override
    @Async
    public String getFamilyName(int i){
        System.out.println(familyA.getName()+i);
        return familyA.getName()+i;
    }
}
