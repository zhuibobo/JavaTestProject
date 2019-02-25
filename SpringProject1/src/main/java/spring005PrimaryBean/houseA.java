package spring005PrimaryBean;

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
    public String getFamilyName(){
        return familyA.getName();
    }
}
