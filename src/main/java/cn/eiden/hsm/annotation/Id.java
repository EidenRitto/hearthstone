package cn.eiden.hsm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类id
 * @author 周晋平
 * @date 2020/4/17 14:42
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    /**id*/
    int value();
    /**牌名*/
    String name();
}
