package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.GeneralItem;
import cn.eiden.hsm.game.keyword.Aura;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.listener.HearthListener;

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
     * @param number 数量
     */
    void beHurt(long number);

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
     * @param attackValue 数值
     * */
    void setAttackValue(long attackValue);

    /**
     * 本回合增加攻击力
     *
     * @param addAttack 数量
     */
    void addAttackThisTurn(long addAttack);

    /**
     * 减少攻击力，攻击力可以为负数，但显示为0
     *
     * @param reduceAttack 数量
     */
    void reduceAttack(long reduceAttack);

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

    /**移除冲锋*/
    void removeCharge();

    /**
     * 是否具有冲锋
     * @return 有返回true
     * */
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
     * 冻结
     */
    void freeze();

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
     * @return 沉默true
     */
    boolean isSilence();

    /**
     * 添加隐藏
     * */
    void addStealth();

    /**
     * 移除隐藏
     * */
    void removeStealth();

    /**
     * 是否隐藏
     * @return 隐藏返回true
     * */
    boolean isStealth();

    /**
     * 添加圣盾
     * */
    void addDivineShield();

    /**
     * 移除圣盾
     * */
    void removeDivineShield();

    /**
     * 是否有圣盾
     * @return 有圣盾返回true
     * */
    boolean isDivineShield();

    /**
     * 设置战吼
     * @param battle 战吼
     * */
    void setBattle(Battle battle);

    /**
     * 战吼
     * @param target 目标角色
     */
    void doBattle(Minion target);

    /**
     * 是否具有战吼
     * @return 有返回true
     */
    boolean hasBattle();


    /**
     * 设置光环
     * @param aura 光环
     */
    void setAura(Aura aura);

    /**
     * 获取光环
     * @return 光环
     * */
    Aura getAura();

    /**
     * 是否具有光环
     * @return 有返回true
     */
    boolean hasAura();

    /**
     * 获取自带特效监听
     * @return 监听
     */
    HearthListener getHearthListener();

    /**
     * 设置自带特效监听
     * @param hearthListener 监听
     */
    void setHearthListener(HearthListener hearthListener);

    /**
     * 调用该方法，接触随从第一回合不能攻击的标志
     */
    void setReady();
}
