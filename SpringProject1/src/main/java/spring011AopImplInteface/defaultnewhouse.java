package spring011AopImplInteface;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/10
 * @Description:
 * @Version 1.0.0
 */
public class defaultnewhouse implements inewhouse {

    @Override
    public String getNewFamilyName(house sourceHouse) {

        return sourceHouse.getFamilyName(0)+" and I'm defaultnewhouse getNewFamilyName";
    }
}
