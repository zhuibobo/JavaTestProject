package SSACoreTest.Test003.Base;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/28
 * To change this template use File | Settings | File Templates.
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Qualifier1 {
}
