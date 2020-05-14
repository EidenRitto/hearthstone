package cn.eiden.hsm.game.minion.hero;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.minion.AbstractMinion;

import cn.eiden.hsm.output.OutputInfo;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/26
 */
public class HeroObjectAbstract extends AbstractMinion {
    /**生命值上限*/
    private static final Long HEALTH = 30L;
    /**职业*/
    private CardClass cardClass;
    /**护甲*/
    private long armor;
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

    public CardClass getCardClass() {
        return cardClass;
    }

    public void setCardClass(CardClass cardClass) {
        this.cardClass = cardClass;
    }

    public long getArmor() {
        return armor;
    }

    public void setArmor(long armor) {
        this.armor = armor;
    }
}
