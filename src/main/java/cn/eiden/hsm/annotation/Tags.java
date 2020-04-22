package cn.eiden.hsm.annotation;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标签属性
 * 包含种族，职业
 *
 * @author Eiden J.P Zhou
 * @date 2020/4/4 11:59
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tags {
    CardClass cardClass();
    CardSet cardSet();
}
