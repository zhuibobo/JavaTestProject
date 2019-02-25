package spring007ValueSetting;

import org.springframework.core.io.Resource;

/**
 * Created by zhuangrb on 2017/12/5.
 */

public class houseA implements house {

    String address;

    String address2;

    String address3;

    String address4;

    family familyA;

    @Override
    public Resource getBaiduResource() {
        return baiduResource;
    }

    @Override
    public void setBaiduResource(Resource baiduResource) {
        this.baiduResource = baiduResource;
    }

    Resource baiduResource;

    public family getFamilyA() {
        return familyA;
    }

    public void setFamilyA(family familyA) {
        this.familyA = familyA;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress2() {
        return address2;
    }

    @Override
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Override
    public String getAddress3() {
        return address3;
    }

    @Override
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Override
    public String getAddress4() {
        return address4;
    }

    @Override
    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    @Override
    public String getFamilyName(){
        return familyA.getName();
    }


}
