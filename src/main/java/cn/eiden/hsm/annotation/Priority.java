package cn.eiden.hsm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 关键字优先度注解，标注在关键字类上<br/>
 * 数值越小，优先度越高
 * @author Eiden J.P Zhou
 * @date 2019/10/29 10:18
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Priority {
    int value() default 99;
}
