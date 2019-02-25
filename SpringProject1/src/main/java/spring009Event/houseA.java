package spring009Event;

import org.springframework.context.ApplicationListener;

/**
 * Created by zhuangrb on 2017/12/5.
 */

public class houseA implements house,ApplicationListener<familyAddMemberEvent> {

    family familyA;


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
    public void onApplicationEvent(familyAddMemberEvent familyAddMemberEvent) {
        System.out.println("监听了familyAddMemberEvent事件");
    }
}
