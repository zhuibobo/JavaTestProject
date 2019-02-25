package spring003Aop;

import java.lang.annotation.*;

/**
 * Created by zhuangrb on 2017/12/5.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
