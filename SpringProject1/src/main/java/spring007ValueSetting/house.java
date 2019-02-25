package spring007ValueSetting;


import org.springframework.core.io.Resource;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/7
 * @Description:
 * @Version 1.0.0
 */
public interface house {

    Resource getBaiduResource();

    void setBaiduResource(Resource baiduResource);

    family getFamilyA();

    void setFamilyA(family familyA);

    String getAddress();

    void setAddress(String address);

    String getAddress2();

    void setAddress2(String address2);

    String getAddress3();

    void setAddress3(String address3);

    String getAddress4();

    void setAddress4(String address4);

    String getFamilyName();
}
