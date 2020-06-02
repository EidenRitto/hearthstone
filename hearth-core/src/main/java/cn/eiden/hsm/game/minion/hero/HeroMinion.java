package cn.eiden.hsm.game.minion.hero;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.event.events.BattlefieldChangeEvent;
import cn.eiden.hsm.game.card.AbstractHeroPowerCard;
import cn.eiden.hsm.game.minion.AbstractMinion;

import cn.eiden.hsm.game.minion.Minion;
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
public class HeroMinion extends AbstractMinion implements Hero {
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

    public HeroMinion(CardClass cardClass) {
        super(cardClass.name(), HEALTH, 0L, Race.INVALID);
    }

    public HeroMinion(CardClass cardClass, AbstractHeroPowerCard heroPower) {
        super(cardClass.getCnName(), HEALTH, 0L, Race.INVALID);
        this.heroPower = heroPower;
    }


    @Override
    public void addArmor(long armorNumber) {
        OutputInfo.info(getMinionName() + "获得" + armorNumber + "点护甲.");
        armor += armorNumber;
    }

    @Override
    public void equipWeapons(Weapon weapon) {
        OutputInfo.info("装备%s", weapon.getName());
        if (hasWeapon()) {
            this.weapon.destroy();
            this.reduceAttack(this.weapon.getWeaponAttack());
        }
        this.addAttack(weapon.getWeaponAttack());
        this.weapon = weapon;
        this.weapon.setOwner(getOwner());
        getOwner().getEventManager().call(new BattlefieldChangeEvent(getOwner()));
    }

    @Override
    public boolean hasWeapon() {
        return weapon != null;
    }

    @Override
    public void attack(Minion beAttackMinion) {
        super.attack(beAttackMinion);
        //减去武器耐久
        if (hasWeapon()) {
            weapon.durableConsumed();
        }
        //如果耐久度没了就摧毁武器
        if (!weapon.hasDurable()) {
            weapon.destroy();
            weapon = null;
        }
    }

    @Override
    public void beHurt(Minion source, long number) {
        number -= armor;
        armor = Math.max(armor - number, 0L);
        super.beHurt(source, number);
        getOwner().checkHero();
    }

    @Override
    public void turnAtk() {
        if (hasWeapon()) {
            this.setAttackValue(weapon.getWeaponAttack());
        }
    }

    @Override
    public void resetAtk() {
        this.setAttackValue(0L);
    }

    @Override
    public AbstractHeroPowerCard getHeroPower() {
        return heroPower;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public String getHeroInfo() {
        StringBuilder builder = new StringBuilder(getMinionName());
        builder.append(" 当前生命值:").append(getHealth());
        if (hasWeapon()) {
            builder.append(" 当前武器:").append(weapon.getName());
        }
        if (getAttackValue() > 0) {
            builder.append(" 当前攻击力:").append(getAttackValue());
        }
        if (isAttack()) {
            builder.append(" (可以攻击)");
        }
        return builder.toString();
    }

    @Override
    public boolean isAttack() {
        return getAttackValue() > 0 && !isFreeze() && getAttackTime() > 0;
    }
}
