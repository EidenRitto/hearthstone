package cn.eiden.hsm.game.minion.hero;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.game.card.AbstractHeroPowerCard;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Weapon;
import cn.eiden.hsm.listener.MinionListener;

/**
 * 英雄
 * @author Eiden J.P Zhou
 * @date 2020/5/20 17:43
 */
public interface Hero extends Minion {
    /**
     * 叠甲
     * @param armorNumber 护甲值
     */
    void addArmor(long armorNumber);
    /**
     * 装备武器
     * @param weapon 武器
     */
    void equipWeapons(Weapon weapon);

    /**
     * 卸下武器
     */
    void unloadWeapon();

    /**
     * 是否具有武器
     * @return 有武器返回true
     */
    boolean hasWeapon();

    /**
     * 回合开始时英雄获得武器的攻击力
     */
    void turnAtk();

    /**
     * 回合开始时充值攻击力为0
     */
    void resetAtk();

    /**
     * 获取英雄技能卡牌
     * @return 英雄技能卡牌
     */
    AbstractHeroPowerCard getHeroPower();

    /**
     * 设置英雄技能卡牌
     * @param card 英雄技能卡牌
     */
    void setHeroPower(AbstractHeroPowerCard card);

    /**
     * 获取武器
     * @return 武器
     */
    Weapon getWeapon();

    /**
     * 获取英雄信息
     * @return 信息
     */
    String getHeroInfo();

    /**
     * 伤害是否致命
     * @param dmg 伤害值
     * @return 致命返回true
     */
    boolean isLethalDose(long dmg);

    /**
     * 获取职业
     * @return 卡牌职业
     */
    CardClass getCardClass();

    /**
     * 克隆
     * @return 返回一个副本,需要手动修改随从引用
     */
    @Override
    HeroMinion clone();
}
