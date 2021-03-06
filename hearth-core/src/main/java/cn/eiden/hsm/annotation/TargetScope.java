package cn.eiden.hsm.annotation;

import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.hero.HeroMinion;
import cn.eiden.hsm.game.tags.Stand;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 卡牌选择目标时，能够选择的目标范围<br/>
 * 打出卡牌时需要选择目标的战吼和法术，在被invoke的类上添加本注解<br/>
 * classScope用来确定指定的目标类型
 * 如果目标可以选择为双方随从和双方英雄 则注解不需要添加参数<br/>
 * 如果目标只能为随从 则参数填写{@link MinionObject} 随从的抽象子类<br/>
 * 如果目标只能为英雄 则填写{@link HeroMinion} <br/>
 * stand属性用来判断指定的目标为敌或友或全部<br/>
 * example:<br/>
 * 友方随从 ：@TargetScope(MinionObject.class,Stand.FRIEND)
 * 敌方英雄和随从 ：@TargetScope(Minion.class,Stand.FOE)
 * 全部英雄和随从 ： @TargetScope
 *
 * @author Eiden J.P Zhou
 * @date 2020/4/4 15:12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetScope {
    Class<? extends Minion> classScope() default Minion.class;
    Stand stand() default Stand.ALL;
}
