package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.GeneralItem;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.keyword.Aura;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.keyword.Combo;
import cn.eiden.hsm.game.keyword.SpellBurst;
import cn.eiden.hsm.listener.HearthListener;
import cn.eiden.hsm.listener.MinionListener;
import cn.eiden.hsm.output.OutputInfo;

/**
 * @author Eiden J.P Zhou
 * @date 2020/3/19 14:53
 */
public interface Minion extends GeneralItem {
    /**
     * 攻击
     *
     * @param beAttackMinion 被攻击随从
     */
    void attack(Minion beAttackMinion);

    /**
     * 受伤害
     *
     * @param source 伤害来源
     * @param number 数量
     */
    void beHurt(Minion source, long number);

    /**
     * 受英雄伤害
     *
     * @param number 数量
     */
    default void beHurt(long number) {
        this.beHurt(this.getOwner().getEnemy().getHero(), number);
    }

    /**
     * 增加攻击力
     *
     * @param addAttack 数量
     */
    void addAttack(long addAttack);

    /**
     * 获取当前攻击力
     *
     * @return 攻击力
     */
    long getAttackValue();

    /**
     * 设置攻击力
     *
     * @param attackValue 数值
     */
    void setAttackValue(long attackValue);


    /**
     * 减少攻击力，攻击力可以为负数，但显示为0
     *
     * @param reduceAttack 数量
     */
    void reduceAttack(long reduceAttack);

    /**
     * 减少生命值
     * @param source 伤害来源
     * @param reduceHealth 数量
     */
    void reduceHealth(Minion source, long reduceHealth);

    /**
     * 减少生命值上限
     *
     * @param reduceHealthLimit 数量
     */
    void reduceHealthLimit(long reduceHealthLimit);

    /**
     * 生命值不得大于上限
     */
    void checkHealth();

    /**
     * 改变生命值
     *
     * @param hp 数量
     */
    void changeHealth(long hp);

    /**
     * 增加生命值上限
     *
     * @param addHealthLimit 数量
     */
    void addHealthLimit(long addHealthLimit);


    /**
     * 获得嘲讽
     */
    void addTaunt();

    /**
     * 获得冲锋
     */
    void addCharge();

    /**
     * 移除冲锋
     */
    void removeCharge();

    /**
     * 是否具有冲锋
     *
     * @return 有返回true
     */
    boolean hasCharge();

    /**
     * 恢复生命值
     *
     * @param number 数量
     */
    void recoveryHp(long number);

    /**
     * 开始新回合
     */
    void newTurnStart();

    /**
     * 本回合是否攻击过
     * @return 攻击过返回true
     */
    boolean isAtkThisTurn();

    /**
     * 恢复攻击状态
     */
    void recoverAttack();

    /**
     * 获取随从名称
     *
     * @return 随从名称
     */
    String getMinionName();

    /**
     * 获取当前生命值
     *
     * @return 当前生命值
     */
    long getHealth();

    /**
     * 改变生命值
     * @param health 生命值
     */
    void setHealth(long health);

    /**
     * 是否可以攻击
     *
     * @return 可以返回true
     */
    boolean isAttack();

    /**
     * 设置嘲讽
     *
     * @param taunt 是否嘲讽
     */
    void setTaunt(boolean taunt);

    /**
     * 获取法强
     *
     * @return 法强
     */
    int getSpellPower();

    /**
     * 设置法强
     *
     * @param spellPower 法强
     */
    void setSpellPower(int spellPower);

    /**
     * 获取生命值上限
     *
     * @return 生命值上限
     */
    long getHealthLimit();

    /**
     * 获取种族
     *
     * @return 种族
     */
    Race getRace();

    /**
     * 是否嘲讽
     *
     * @return 是否嘲讽
     */
    boolean isTaunt();

    /**
     * 回合结束
     */
    void endTurn();

    /**
     * 冻结
     */
    void freeze();

    boolean isFreeze();

    void addAttackTime(int times);

    /**
     * 解冻
     */
    void unfreeze();

    /**
     * 沉默
     */
    void silence();

    /**
     * 是否沉默
     *
     * @return 沉默true
     */
    boolean isSilence();

    /**
     * 添加隐藏
     */
    void addStealth();

    /**
     * 移除隐藏
     */
    void removeStealth();

    /**
     * 是否隐藏
     *
     * @return 隐藏返回true
     */
    boolean isStealth();

    /**
     * 添加圣盾
     */
    void addDivineShield();

    /**
     * 移除圣盾
     */
    void removeDivineShield();

    /**
     * 是否有圣盾
     *
     * @return 有圣盾返回true
     */
    boolean isDivineShield();

    /**
     * 设置战吼
     *
     * @param battle 战吼
     */
    void setBattle(Battle battle);

    /**
     * 战吼
     *
     * @param target 目标角色
     */
    void doBattle(Minion target);

    /**
     * 是否具有战吼
     *
     * @return 有返回true
     */
    boolean hasBattle();


    /**
     * 设置光环
     *
     * @param aura 光环
     */
    void setAura(Aura aura);

    /**
     * 获取光环
     *
     * @return 光环
     */
    Aura getAura();

    /**
     * 是否具有光环
     *
     * @return 有返回true
     */
    boolean hasAura();

    /**
     * 获取自带特效监听
     *
     * @return 监听
     */
    HearthListener getMinionListener();

    /**
     * 设置自带特效监听
     *
     * @param minionListener 监听
     */
    void setMinionListener(MinionListener minionListener);

    /**
     * 调用该方法，接触随从第一回合不能攻击的标志
     */
    void setReady();

    /**
     * 重置光环属性
     */
    void resetBuff();

    /**
     * 增加被buff的攻击力
     *
     * @param val 攻击力的值
     */
    void addBuffAtk(long val);

    /**
     * 增加被buff的生命值
     *
     * @param val 生命值
     */
    void addBuffHp(long val);

    /**
     * 获取随从对应的卡牌id
     *
     * @return 卡牌id
     */
    String getCardId();

    /**
     * 是否具有免疫
     *
     * @return 有免疫返回true
     */
    boolean isImmune();

    /**
     * 设置免疫
     *
     * @param immune 是否免疫
     */
    void setImmune(boolean immune);

    /**
     * 获得免疫
     */
    default void addImmune() {
        setImmune(true);
    }

    /**
     * 移除免疫
     */
    default void removeImmune() {
        setImmune(false);
    }

    /**
     * 复制随从
     *
     * @return 随从副本
     */
    Minion clone();

    /**
     * 随从死亡标识
     *
     * @return 随从死亡标识
     */
    boolean isDeadFlag();

    /**
     * 设置随从死亡
     */
    void setDead();

    /**
     * 是否具有剧毒
     *
     * @return 有剧毒返回true
     */
    boolean hasPoisonous();

    /**
     * 添加剧毒
     */
    void addPoisonous();

    /**
     * 移除剧毒
     */
    void removePoisonous();

    /**
     * 是否具有风怒
     *
     * @return 有风怒返回true
     */
    boolean hasWindFury();

    /**
     * 添加风怒
     */
    void addWindFury();

    /**
     * 移除风怒
     */
    void removeWindFury();


    /**
     * 是否具有吸血
     *
     * @return 有吸血返回true
     */
    boolean hasLifeSteal();

    /**
     * 添加吸血
     */
    void addLifeSteal();

    /**
     * 移除吸血
     */
    void removeLifeSteal();

    /**
     * 是否具有突袭
     *
     * @return 有突袭返回true
     */
    boolean hasRush();

    /**
     * 添加突袭
     */
    void addRush();

    /**
     * 是否具有复生
     *
     * @return 有复生返回true
     */
    boolean hasReborn();

    /**
     * 添加复生
     */
    void addReborn();

    /**
     * 移除复生
     */
    void removeReborn();

    /**
     * 随从是否准备好了（随从进场时当前回合不能攻击）
     * @return 准备好返回true
     */
    boolean hasReady();

    /**
     * 设置连击
     *
     * @param combo 连击
     */
    void setCombo(Combo combo);

    /**
     * 获取连击
     *
     * @return 连击
     */
    Combo getCombo();

    /**
     * 执行连击
     *
     * @param target 指定的目标
     */
    default void doCombo(Minion target) {
        this.getCombo().doCombo(this, target);
    }

    /**
     * 是否有连击
     *
     * @return 有连击返回true
     */
    default boolean hasCombo() {
        return this.getCombo() != null;
    }

    /**
     * 设置法术迸发
     * @param spellBurst 法术迸发
     */
    void setSpellBurst(SpellBurst spellBurst);

    /**
     * 执行法术迸发，执行完毕移出法术迸发
     * @param magicCard 触发的法术
     */
    void doSpellBurst(AbstractMagicCard magicCard);

    /**
     * 是否具有法术迸发
     * @return 有返回true
     */
    boolean hasSpellBurst();
}
