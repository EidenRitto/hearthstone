package cn.eiden.hsm.game.minion.hero;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.card.AbstractHeroPowerCard;
import cn.eiden.hsm.game.minion.AbstractMinion;

import cn.eiden.hsm.output.OutputInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/26
 */
public class HeroObjectAbstract extends AbstractMinion {
    /**生命值上限*/
    private static final Long HEALTH = 30L;
    /**职业*/
    @Getter
    @Setter
    private CardClass cardClass;
    /**护甲*/
    @Getter
    @Setter
    private long armor;
    /**英雄技能*/
    @Getter
    @Setter
    private AbstractHeroPowerCard heroPower;

    public HeroObjectAbstract(CardClass cardClass) {
        super(cardClass.name(),HEALTH,0L, Race.INVALID);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/19 15:59
     *  叠甲
     */
    public void addArmor(long armorNumber){
        OutputInfo.info(getMinionName()+"获得"+armorNumber+"点护甲.");
        armor += armorNumber;
    }
}
