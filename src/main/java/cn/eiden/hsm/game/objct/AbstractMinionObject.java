package cn.eiden.hsm.game.objct;


import cn.eiden.hsm.game.Gamer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 随从 抽象
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public abstract class AbstractMinionObject extends GameObject {
    /**随从名称*/
    private String minionName;
    /**生命值上限*/
    private long healthLimit;
    /**当前生命值*/
    private long health;
    /**攻击力*/
    private long attackValue;
    /**是否可以攻击*/
    private boolean isAttack = false;
    /**本回合攻击次数*/
    private int attackTime = 1;
    /**是否具有战吼*/
    protected boolean isBattle = false;
    /**是否具有亡语*/
    protected boolean isDeathVoice = false;
    /**是否具有嘲讽*/
    protected boolean isTaunt = false;
    /**法术强度*/
    protected int spellDamage = 0;
    /**种族*/
    protected Ethnicity ethnicity;

    protected Gamer owner;

    /**本回合增加的攻击力*/
    private long attackValueThisTurn = 0;
    /**光环所增加的攻击力*/
    private long buffHoleAttack = 0;
    /**光环所增加的生命值*/
    private long buffHoleHealth = 0;

    /**
     * 随从攻击行为
     * @param beAttackMinion 被攻击的随从对象
     */
    public void attack(AbstractMinionObject beAttackMinion){
        //攻击次数减少1
        attackTime--;
        //敌人掉血
        beAttackMinion.setHealth(beAttackMinion.getHealth()-this.getAttackValue());
        //自己掉血
        this.setHealth(this.getHealth()-beAttackMinion.getAttackValue());
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/18 15:22
     * @method : beHurt
     *  受伤害
     */
    public void beHurt(long number){
        log.info(minionName+"受到"+number+"点伤害");
        health -= number;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/19 18:16
     * @method : addAttack
     * @params : [addAttack]
     *  增加攻击力
     */
    public void addAttack(long addAttack){
        log.info(minionName+"增加"+addAttack+"点攻击");
        attackValue += addAttack;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/22 11:54
     * @method : addAttack
     * @params : [addAttack]
     *  本回合增加攻击力
     */
    public void addAttackThisTurn(long addAttack){
        log.info(minionName+"本回合增加"+addAttack+"点攻击");
        attackValue += addAttack;
        attackValueThisTurn += addAttack;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 8:49
     * @method : reduceAttack
     *  减少攻击力，攻击力可以为负数，但显示为0
     */
    public void reduceAttack(long reduceAttack){
        log.info(minionName+"减少"+reduceAttack+"点攻击");
        attackValue -= reduceAttack;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 8:51
     * @method : reduceAttack
     *  减少生命值上限
     */
    public void reduceHealthLimit(long reduceHealthLimit){
        log.info(minionName+"减少"+reduceHealthLimit+"点生命上限");
        healthLimit -= reduceHealthLimit;
        //如果当前生命值大于生命值上限，一并减少
        checkHealth();
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 8:55
     * @method : checkHealth
     *  生命值不得大于上限
     */
    public void checkHealth(){
        if (health>healthLimit){
            health = healthLimit;
        }
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/22 14:57
     * @method : changeHealth
     *  改变生命值
     */
    public void changeHealth(long hp){
        healthLimit = hp;
        health = hp;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 8:57
     * @method : addHealthLimit
     *  增加生命值上限
     */
    public void addHealthLimit(long addHealthLimit){
        log.info(minionName+"增加"+addHealthLimit+"点生命值");
        healthLimit += addHealthLimit;
        health += addHealthLimit;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/11/21 17:24
     *  获得嘲讽
     */
    public void addTaunt(){
        log.info(minionName+"获得嘲讽");
        isTaunt = true;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  恢复生命值
     * */
    public void recovery(long number){
        //防止上限溢出
        long newHealth = health + number >= healthLimit?healthLimit:health+number;
        log.info(minionName+"恢复"+number+"点生命值");
        health = newHealth;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/11/21 14:53
     * @method : addBuffHoleAttack
     *  增加光环所增加的攻击力
     */
    public void addBuffHoleAttack(long number){
        buffHoleAttack += number;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/11/21 14:53
     * @method : addBuffHoleHealth
     *  增加光环所增加的生命值
     */
    public void addBuffHoleHealth(long number){
        buffHoleHealth += number;
    }

    public void cleanBuffHoleAttribute(){
        buffHoleAttack = 0;
        buffHoleHealth = 0;
    }

    public AbstractMinionObject() {
    }

    public AbstractMinionObject(String minionName, Long healthLimit, Long health, Long attackValue, boolean isAttack, int attackTime) {
        this.minionName = minionName;
        this.healthLimit = healthLimit;
        this.health = health;
        this.attackValue = attackValue;
        this.isAttack = isAttack;
        this.attackTime = attackTime;
        this.spellDamage = 0;
    }
}
