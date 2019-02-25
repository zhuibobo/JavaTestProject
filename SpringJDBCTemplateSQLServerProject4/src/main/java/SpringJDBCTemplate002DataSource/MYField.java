package SpringJDBCTemplate002DataSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/23
 * @Description:
 * @Version 1.0.0
 */
@Target({ElementType.METHOD})
public @interface MYField {
    String name();
}
