package spring014AppendBeanToContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhuangrb on 2017/12/5.
 */
public class house {

    @Autowired
    family familyA;

    public String getFamilyName(){
        return familyA.getName();
    }
}
