package cn.eiden.hsm.annotation;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;

import java.lang.annotation.*;

/**
 * 标签属性
 * 包含种族，职业
 *
 * @author Eiden J.P Zhou
 * @date 2020/4/4 11:59
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Tags {
    CardClass cardClass();
    CardSet cardSet();
    int[] value() default {};
}
