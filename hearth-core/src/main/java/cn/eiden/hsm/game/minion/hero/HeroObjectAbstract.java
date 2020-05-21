package cn.eiden.hsm.game.minion.hero;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.card.AbstractHeroPowerCard;
import cn.eiden.hsm.game.minion.AbstractMinion;

import cn.eiden.hsm.game.minion.Weapon;
import cn.eiden.hsm.output.OutputInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/26
 */
@Getter
@Setter
public class HeroObjectAbstract extends AbstractMinion implements Hero {
    /**
     * 生命值上限
     */
    private static final Long HEALTH = 30L;
    /**
     * 职业
     */
    private CardClass cardClass;
    /**
     * 护甲
     */
    private long armor;
    /**
     * 英雄技能
     */
    private AbstractHeroPowerCard heroPower;

    /**
     * 武器
     */
    private Weapon weapon;

    public HeroObjectAbstract(CardClass cardClass) {
        super(cardClass.name(), HEALTH, 0L, Race.INVALID);
    }

    public HeroObjectAbstract(CardClass cardClass, AbstractHeroPowerCard heroPower) {
        super(cardClass.name(), HEALTH, 0L, Race.INVALID);
        this.heroPower = heroPower;
    }


    @Override
    public void addArmor(long armorNumber) {
        OutputInfo.info(getMinionName() + "获得" + armorNumber + "点护甲.");
        armor += armorNumber;
    }

    @Override
    public void equipWeapons(Weapon weapon) {
        if (hasWeapon()){
            weapon.destroy();
        }
        this.weapon = weapon;
    }

    @Override
    public boolean hasWeapon() {
        return weapon != null;
    }
}
