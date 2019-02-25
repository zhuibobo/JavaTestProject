package spring005PrimaryBean;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/7
 * @Description:
 * @Version 1.0.0
 */
public class houseB implements house {
    family familyA;

    @Override
    public family getFamilyA() {
        return familyA;
    }

    @Override
    public void setFamilyA(family familyA) {
        this.familyA = familyA;
    }

    @Override
    public String getFamilyName(){
        return familyA.getName()+"Demo5_";
    }
}
