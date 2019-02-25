package spring010Async;


import org.springframework.scheduling.annotation.Async;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/7
 * @Description:
 * @Version 1.0.0
 */
public interface house {

    family getFamilyA();

    void setFamilyA(family familyA);

    @Async
    String getFamilyName(int i);
}
