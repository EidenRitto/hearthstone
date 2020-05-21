package cn.eiden.hsm.game.minion;


import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.event.events.MinionBeHurtEvent;
import cn.eiden.hsm.game.AbstractGeneralItem;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.listener.HearthListener;
import cn.eiden.hsm.output.OutputInfo;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;


/**
 * 随从 抽象
 *
 * @author : Eiden J.P Zhou
 * @version : 1.0
 * @date : 2018/9/12
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
public abstract class AbstractMinion extends AbstractGeneralItem implements Minion {
    /**
     * 随从名称
     */
    private String minionName;
    /**
     * 生命值上限
     */
    private long healthLimit;
    /**
     * 当前生命值
     */
    private long health;
    /**
     * 攻击力
     */
    private long attackValue;

    /**
     * 第一回的准备时间
     */
    private boolean ready = false;

    /**
     * 被冻结
     */
    private boolean isFrozen = false;

    /**
     * 本回合攻击次数
     */
    private int attackTime = 1;

    /**
     * 攻击次数上限
     */
    private int attackTimeLimit = 1;
    /**
     * 是否具有嘲讽
     */
    private boolean isTaunt = false;

    /**
     * 是否具有隐藏
     */
    private boolean stealth = false;

    /**
     * 是否具有圣盾
     */
    private boolean divineShield = false;
    /**
     * 法术强度
     */
    private int spellPower = 0;
    /**
     * 种族
     */
    private Race race;

    /**
     * 本回合增加的攻击力
     */
    private long attackValueThisTurn = 0;

    /**
     * 是否被沉默
     */
    private boolean isSilence = false;

    /**
     * 原始生命值
     */
    private long originalHealth;
    /**
     * 原始攻击力
     */
    private long originalAttack;


    /**
     * 战吼
     */
    private Battle battle;

    /**
     * 自带监听
     */
    private HearthListener hearthListener;

    @Override
    public void attack(Minion beAttackMinion) {
        if (!this.isAttack()) {
            OutputInfo.info("这个随从无法进行攻击");
            return;
        }
        //攻击次数减少1
        attackTime--;
        //敌人掉血
        beAttackMinion.beHurt(this.getAttackValue());
        //自己掉血
        this.beHurt(beAttackMinion.getAttackValue());
    }

    @Override
    public void beHurt(long number) {
        if (divineShield) {
            OutputInfo.info(minionName + "圣盾抵消伤害");
            removeDivineShield();
        } else {
            MinionBeHurtEvent minionBeHurtEvent = new MinionBeHurtEvent(this);
            OutputInfo.info(minionName + "受到" + number + "点伤害");
            health -= number;
            getOwner().getEventManager().call(minionBeHurtEvent);
        }
    }

    @Override
    public void addAttack(long addAttack) {
        OutputInfo.info(minionName + "增加" + addAttack + "点攻击");
        attackValue += addAttack;
    }

    @Override
    public void addAttackThisTurn(long addAttack) {
        OutputInfo.info(minionName + "本回合增加" + addAttack + "点攻击");
        attackValue += addAttack;
        attackValueThisTurn += addAttack;
    }

    @Override
    public void reduceAttack(long reduceAttack) {
        OutputInfo.info(minionName + "减少" + reduceAttack + "点攻击");
        attackValue -= reduceAttack;
    }

    @Override
    public void reduceHealthLimit(long reduceHealthLimit) {
        OutputInfo.info(minionName + "减少" + reduceHealthLimit + "点生命上限");
        healthLimit -= reduceHealthLimit;
        //如果当前生命值大于生命值上限，一并减少
        checkHealth();
    }

    @Override
    public void checkHealth() {
        if (health > healthLimit) {
            health = healthLimit;
        }
    }

    @Override
    public void changeHealth(long hp) {
        healthLimit = hp;
        health = hp;
    }

    @Override
    public void addHealthLimit(long addHealthLimit) {
        OutputInfo.info(minionName + "增加" + addHealthLimit + "点生命值");
        healthLimit += addHealthLimit;
        health += addHealthLimit;
    }

    @Override
    public void addTaunt() {
        isTaunt = true;
    }

    @Override
    public void addCharge() {
        ready = true;
    }

    @Override
    public void recoveryHp(long number) {
        //防止上限溢出
        long newHealth = Math.min(health + number, healthLimit);
        OutputInfo.info(minionName + "恢复" + number + "点生命值");
        health = newHealth;
    }

    @Override
    public void newTurnStart() {
        recoverAttack();
    }

    @Override
    public void recoverAttack() {
        attackTime = attackTimeLimit;
    }

    @Override
    public String getMinionName() {
        return minionName;
    }

    @Override
    public long getHealth() {
        return health;
    }

    @Override
    public long getAttackValue() {
        return attackValue;
    }

    @Override
    public void setAttackValue(long attackValue) {
        this.attackValue = attackValue;
    }

    @Override
    public boolean isAttack() {
        return ready && !isFrozen && attackTime > 0;
    }

    @Override
    public void setTaunt(boolean taunt) {
        isTaunt = taunt;
    }

    @Override
    public int getSpellPower() {
        return spellPower;
    }

    @Override
    public void setSpellPower(int spellPower) {
        this.spellPower = spellPower;
    }

    @Override
    public long getHealthLimit() {
        return healthLimit;
    }

    @Override
    public Race getRace() {
        return race;
    }

    @Override
    public boolean isTaunt() {
        return isTaunt;
    }

    @Override
    public void freeze() {
        isFrozen = true;
    }

    @Override
    public void unfreeze() {
        isFrozen = false;
    }

    @Override
    public void silence() {
        health = Math.min(health, originalHealth);
        healthLimit = originalHealth;
        attackValue = originalAttack;
        isSilence = true;
    }

    @Override
    public boolean isSilence() {
        return isSilence;
    }

    @Override
    public void addStealth() {
        stealth = true;
    }

    @Override
    public void removeStealth() {
        stealth = false;
    }

    @Override
    public boolean isStealth() {
        return stealth;
    }

    @Override
    public void addDivineShield() {
        divineShield = true;
    }

    @Override
    public void removeDivineShield() {
        divineShield = false;
    }

    @Override
    public boolean isDivineShield() {
        return divineShield;
    }

    @Override
    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    @Override
    public void doBattle(Gamer gamer, Minion target) {
        this.battle.doBattle(gamer, this, target);
    }

    @Override
    public boolean hasBattle() {
        return this.battle != null;
    }

    @Override
    public HearthListener getHearthListener() {
        return hearthListener;
    }

    @Override
    public void setHearthListener(HearthListener hearthListener) {
        this.hearthListener = hearthListener;
    }

    public AbstractMinion() {
    }

    public AbstractMinion(String minionName, Long healthLimit, Long attackValue, Race race) {
        this.minionName = minionName;
        this.healthLimit = healthLimit;
        this.health = healthLimit;
        this.attackValue = attackValue;
        this.originalHealth = healthLimit;
        this.originalAttack = attackValue;
        this.race = race;
    }
}
