package game.objct;


import game.Gamer;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description :
 * */
public class MinionObject extends GameObject {
    /**随从名称*/
    private String minionName;
    /**生命值上限*/
    private Long healthLimit;
    /**当前生命值*/
    private Long health;
    /**攻击力*/
    private Long attackValue;
    /**是否可以攻击*/
    private boolean isAttack;
    /**本回合攻击次数*/
    private int attackTime;
    /**是否具有战吼*/
    protected boolean isBattle = false;
    /**是否具有亡语*/
    protected boolean isDeathVoice = false;
    /**法术强度*/
    protected int spellDamage;

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 战吼
     * */
    public void doBattle(GameObject target){

    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 亡语
     * */
    public void doDeathRattle(Gamer self){

    }

    /**
     * @author : Eiden J.P Zhou
     * @Date : 2018/9/12 10:04
     * @Method : attack
     * @Params : [MinionCard]
     * @Return : void
     * @Description : 随从攻击行为
     */
    public void attack(MinionObject beAttackMinion){
        //敌人掉血
        beAttackMinion.setHealth(beAttackMinion.getHealth()-this.attackValue);
        //自己掉血
        this.setHealth(this.getHealth()-beAttackMinion.getAttackValue());
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/18 16:09
     * @method : attack
     * @params : [beAttackGamer]
     * @return : void
     * @Description : 重载打脸
     */
    public void attack(Gamer beAttackGamer){
        //敌人掉血
        beAttackGamer.beHurt(this.attackValue);
        //自己掉血
        this.beHurt(beAttackGamer.getAttackValue());
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/18 15:22
     * @method : beHurt
     * @Description : 受伤害
     */
    public void beHurt(long number){
        System.out.println(minionName+"受到"+number+"点伤害");
        health -= number;
    }


    public MinionObject() {
    }

    public MinionObject(String minionName, Long healthLimit, Long health, Long attackValue, boolean isAttack, int attackTime) {
        this.minionName = minionName;
        this.healthLimit = healthLimit;
        this.health = health;
        this.attackValue = attackValue;
        this.isAttack = isAttack;
        this.attackTime = attackTime;
        this.setSpellDamage(0);
    }

    public Long getHealthLimit() {
        return healthLimit;
    }

    public void setHealthLimit(Long healthLimit) {
        this.healthLimit = healthLimit;
    }

    public Long getHealth() {
        return health;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    public Long getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(Long attackValue) {
        this.attackValue = attackValue;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }

    public int getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(int attackTime) {
        this.attackTime = attackTime;
    }

    public String getMinionName() {

        return minionName;
    }

    public void setMinionName(String minionName) {
        this.minionName = minionName;
    }

    public boolean isBattle() {
        return isBattle;
    }

    public void setBattle(boolean battle) {
        isBattle = battle;
    }

    public boolean isDeathVoice() {
        return isDeathVoice;
    }

    public void setDeathVoice(boolean deathRattle) {
        isDeathVoice = deathRattle;
    }

    public int getSpellDamage() {
        return spellDamage;
    }

    public void setSpellDamage(int spellDamage) {
        this.spellDamage = spellDamage;
    }
}
