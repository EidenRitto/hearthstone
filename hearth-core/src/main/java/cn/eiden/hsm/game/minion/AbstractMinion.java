package cn.eiden.hsm.game.minion;


import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.event.events.AfterHeroBeAttackEvent;
import cn.eiden.hsm.event.events.HeroBeAttackEvent;
import cn.eiden.hsm.event.events.MinionAttackEvent;
import cn.eiden.hsm.event.events.MinionBeHurtEvent;
import cn.eiden.hsm.game.AbstractGeneralItem;
import cn.eiden.hsm.game.keyword.Aura;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.keyword.Combo;
import cn.eiden.hsm.game.minion.hero.HeroMinion;
import cn.eiden.hsm.listener.MinionListener;
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
public abstract class AbstractMinion extends AbstractGeneralItem implements Minion, Cloneable {
    /**
     * 随从对应的卡牌id
     */
    private String cardId;
    /**
     * 随从名称
     */
    private String minionName;
    /**
     * 生命值上限
     */
    private long originHealthLimit;

    /**
     * 被buff的生命值上限
     */
    private long buffedHealthLimit;
    /**
     * 当前生命值
     */
    private long health;
    /**
     * 攻击力
     */
    private long attackValue;

    /**
     * 被buff的攻击力
     */
    private long buffedAttackValue;

    /**
     * 第一回的准备时间
     */
    private boolean ready = false;

    /**
     * 冲锋
     */
    private boolean charge = false;

    /**
     * 被冻结
     */
    private boolean isFrozen = false;

    /**
     * 本回合攻击次数
     */
    private int attackTime = 1;

    /**
     * 本回合是否攻击过
     */
    private boolean isAtkThisTurn = false;

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
     * 是否具有剧毒
     */
    private boolean poisonous = false;

    /**
     * 是否具有风怒
     */
    private boolean windFury = false;

    /**
     * 吸血
     */
    private boolean lifeSteal = false;

    /**
     * 突袭
     */
    private boolean rush = false;

    /**
     * 复生
     */
    private boolean reborn = false;

    /**
     * 是否具有免疫
     */
    private boolean immune = false;
    /**
     * 法术强度
     */
    private int spellPower = 0;
    /**
     * 种族
     */
    private Race race;

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
     * 光环
     */
    private Aura aura;

    /**
     * 连击
     */
    private Combo combo;

    /**
     * 自带监听
     */
    private MinionListener minionListener;

    private boolean deadFlag = false;

    @Override
    public void attack(Minion beAttackMinion) {
        MinionAttackEvent minionAttackEvent = new MinionAttackEvent(this, beAttackMinion);
        getOwner().getEventManager().call(minionAttackEvent);
        if (beAttackMinion instanceof HeroMinion) {
            HeroBeAttackEvent heroBeAttackEvent = new HeroBeAttackEvent(getOwner(), this);
            getOwner().getEventManager().call(heroBeAttackEvent);
            if (!getOwner().getMinions().contains(this) && !(this instanceof HeroMinion)) {
                return;
            }
        }
        if (!this.isAttack()) {
            getOwner().printPrivateQueue("这个随从无法进行攻击");
            return;
        }
        OutputInfo.info(String.format("%s攻击%s", this.getMinionName(), beAttackMinion.getMinionName()));
        //攻击次数减少1
        attackTime--;
        isAtkThisTurn = true;
        //敌人掉血
        beAttackMinion.beHurt(this, this.getAttackValue());
        //自己掉血
        this.beHurt(beAttackMinion, beAttackMinion.getAttackValue());
        if (beAttackMinion instanceof HeroMinion) {
            AfterHeroBeAttackEvent attackEvent = new AfterHeroBeAttackEvent(getOwner(), this);
            getOwner().getEventManager().call(attackEvent);
        }
        //移除隐藏
        this.removeStealth();
    }

    @Override
    public void beHurt(Minion source, long number) {
        if (number > 0) {
            if (this.isDivineShield()) {
                OutputInfo.info(minionName + "圣盾抵消伤害");
                removeDivineShield();
            } else {
                //减少伤害
                reduceHealth(source, number);
            }
        }
    }

    @Override
    public void addAttack(long addAttack) {
        OutputInfo.info(minionName + "增加" + addAttack + "点攻击");
        attackValue += addAttack;
    }

    @Override
    public void reduceAttack(long reduceAttack) {
        OutputInfo.info(minionName + "减少" + reduceAttack + "点攻击");
        attackValue -= reduceAttack;
    }

    @Override
    public void reduceHealth(Minion source, long reduceHealth) {
        if (immune) {
            OutputInfo.info(minionName + "免疫了本次伤害！");
            return;
        }
        MinionBeHurtEvent minionBeHurtEvent = new MinionBeHurtEvent(this, reduceHealth);
        getOwner().getEventManager().call(minionBeHurtEvent);
        OutputInfo.info(source.getMinionName() + "对" + this.getMinionName() + "造成" + reduceHealth + "点伤害");
        if (source.hasLifeSteal()){
            source.getOwner().getHero().recoveryHp(reduceHealth);
        }
        health -= reduceHealth;
    }

    @Override
    public void reduceHealthLimit(long reduceHealthLimit) {
        OutputInfo.info(minionName + "减少" + reduceHealthLimit + "点生命上限");
        originHealthLimit -= reduceHealthLimit;
        //如果当前生命值大于生命值上限，一并减少
        checkHealth();
    }

    @Override
    public void checkHealth() {
        if (health > this.getHealthLimit()) {
            health = this.getHealthLimit();
        }
    }

    @Override
    public void changeHealth(long hp) {
        originHealthLimit = hp;
        health = hp;
    }

    @Override
    public void addHealthLimit(long addHealthLimit) {
        OutputInfo.info(minionName + "增加" + addHealthLimit + "点生命值");
        originHealthLimit += addHealthLimit;
        health += addHealthLimit;
    }

    @Override
    public void addTaunt() {
        isTaunt = true;
    }

    @Override
    public void addCharge() {
        charge = true;
    }

    @Override
    public void removeCharge() {
        charge = false;
    }

    @Override
    public boolean hasCharge() {
        return charge;
    }

    @Override
    public void recoveryHp(long number) {
        //防止上限溢出
        long newHealth = Math.min(health + number, this.getHealthLimit());
        OutputInfo.info(minionName + "恢复" + (newHealth - health) + "点生命值");
        health = newHealth;
    }

    @Override
    public void newTurnStart() {
        recoverAttack();
    }

    @Override
    public boolean isAtkThisTurn() {
        return this.isAtkThisTurn;
    }

    @Override
    public void recoverAttack() {
        attackTime = attackTimeLimit;
        isAtkThisTurn = false;
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
    public void setHealth(long health) {
        this.health = health;
    }

    @Override
    public long getAttackValue() {
        return attackValue + buffedAttackValue;
    }

    @Override
    public void setAttackValue(long attackValue) {
        this.attackValue = attackValue;
    }

    @Override
    public boolean isAttack() {
        return getAttackValue() > 0 && (ready || charge || rush) && !isFrozen && attackTime > 0;
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
        return originHealthLimit + buffedHealthLimit;
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
    public void endTurn() {
        if (this.isFreeze() && attackTime > 0){
            this.unfreeze();
        }
    }

    @Override
    public void freeze() {
        isFrozen = true;
    }

    @Override
    public boolean isFreeze() {
        return isFrozen;
    }

    public int getAttackTime() {
        return attackTime;
    }

    @Override
    public void addAttackTime(int times){
        this.attackTime += times;
    }

    @Override
    public void unfreeze() {
        isFrozen = false;
    }

    @Override
    public void silence() {
        health = Math.min(health, originalHealth);
        originHealthLimit = originalHealth;
        attackValue = originalAttack;
        isSilence = true;
        charge = false;
        attackTimeLimit = 1;
        isTaunt = false;
        isFrozen = false;
        stealth = false;
        divineShield = false;
        poisonous = false;
        windFury = false;
        spellPower = 0;
        aura = null;
        minionListener = null;
        lifeSteal = false;
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
    public void doBattle(Minion target) {
        this.battle.doBattle(this, target);
    }

    @Override
    public boolean hasBattle() {
        return this.battle != null;
    }

    @Override
    public void setAura(Aura aura) {
        this.aura = aura;
    }

    @Override
    public Aura getAura() {
        return aura;
    }

    @Override
    public boolean hasAura() {
        return this.aura != null;
    }

    @Override
    public MinionListener getMinionListener() {
        return minionListener;
    }

    @Override
    public void setMinionListener(MinionListener minionListener) {
        this.minionListener = minionListener;
        if (minionListener != null){
            minionListener.setMinion(this);
        }
    }

    @Override
    public void setReady() {
        ready = true;
    }

    @Override
    public void resetBuff() {
        buffedAttackValue = 0;
        buffedHealthLimit = 0;
        checkHealth();
    }

    @Override
    public void addBuffAtk(long val) {
        buffedAttackValue += val;
    }

    @Override
    public void addBuffHp(long val) {
        buffedHealthLimit += val;
        health += val;
    }

    public AbstractMinion() {
    }

    public AbstractMinion(String minionName, Long originHealthLimit, Long attackValue, Race race, String cardId) {
        this.minionName = minionName;
        this.originHealthLimit = originHealthLimit;
        this.health = originHealthLimit;
        this.attackValue = attackValue;
        this.originalHealth = originHealthLimit;
        this.originalAttack = attackValue;
        this.race = race;
        this.cardId = cardId;
    }

    @Override
    public String getCardId() {
        return cardId;
    }

    @Override
    public boolean isImmune() {
        return immune;
    }

    @Override
    public void setImmune(boolean immune) {
        this.immune = immune;
    }

    @Override
    public boolean isDeadFlag() {
        return deadFlag;
    }

    @Override
    public void setDead() {
        this.setDeadFlag(true);
    }

    public void setDeadFlag(boolean deadFlag) {
        this.deadFlag = deadFlag;
    }

    @Override
    public boolean hasPoisonous() {
        return poisonous;
    }

    public void setPoisonous(boolean poisonous) {
        this.poisonous = poisonous;
    }

    @Override
    public void addPoisonous() {
        this.setPoisonous(true);
    }

    @Override
    public void removePoisonous() {
        this.setPoisonous(false);
    }

    @Override
    public boolean hasWindFury() {
        return this.windFury;
    }

    @Override
    public void addWindFury() {
        this.setWindFury(true);
        this.attackTimeLimit = 2;
    }

    @Override
    public void removeWindFury() {
        this.setWindFury(false);
    }

    public void setWindFury(boolean windFury) {
        this.windFury = windFury;
    }

    @Override
    public boolean hasLifeSteal() {
        return this.lifeSteal;
    }

    @Override
    public void addLifeSteal() {
        this.lifeSteal = true;
    }

    @Override
    public void removeLifeSteal() {
        this.lifeSteal = false;
    }

    @Override
    public boolean hasRush() {
        return this.rush;
    }

    @Override
    public void addRush() {
        this.rush = true;
    }

    @Override
    public boolean hasReborn() {
        return this.reborn;
    }

    @Override
    public void addReborn() {
        this.reborn = true;
    }

    @Override
    public void removeReborn() {
        this.reborn = false;
    }

    @Override
    public boolean hasReady() {
        return this.ready;
    }

    @Override
    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    @Override
    public Combo getCombo() {
        return this.combo;
    }

    @Override
    public Minion clone() {
        try {
            Minion minionClone = (Minion) super.clone();
            MinionListener minionListenerClone = null;
            if (this.minionListener != null) {
                minionListenerClone = this.minionListener.clone();
            }
            minionClone.setMinionListener(minionListenerClone);
            return minionClone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
