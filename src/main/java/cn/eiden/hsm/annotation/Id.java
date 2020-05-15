package cn.eiden.hsm.annotation;

import java.lang.annotation.*;

/**
 * 类id
 * @author Eiden J.P Zhou
 * @date 2020/4/17 14:42
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Id {
    /**id*/
    int value();
    /**牌名*/
    String name();
}
