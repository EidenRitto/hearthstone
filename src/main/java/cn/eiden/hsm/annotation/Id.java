package cn.eiden.hsm.annotation;

import java.lang.annotation.*;

/**
 * 类id
 * @author 周晋平
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
