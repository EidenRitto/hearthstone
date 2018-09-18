package game.objct;


import game.Gamer;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description :
 * */
public class ServantObject extends GameObject {
    /**随从名称*/
    private String servantName;
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
    public void doDeathVoice(Gamer self){

    }

    /**
     * @author : Eiden J.P Zhou
     * @Date : 2018/9/12 10:04
     * @Method : attack
     * @Params : [ServantCard]
     * @Return : void
     * @Description : 随从攻击行为
     */
    public ServantObject attack(ServantObject beAttackServant){
        /**敌人掉血*/
        beAttackServant.setHealth(beAttackServant.getHealth()-this.attackValue);
        /**自己掉血*/
        this.setHealth(this.getHealth()-beAttackServant.getAttackValue());
        return beAttackServant;
    }

    public void getState(){
        System.out.println(servantName+" 状态：");
        System.out.println("血量："+health.toString());
        System.out.println("攻击力："+attackValue.toString());
    }

    public ServantObject() {
    }

    public ServantObject(String servantName, Long healthLimit, Long health, Long attackValue, boolean isAttack, int attackTime) {
        this.servantName = servantName;
        this.healthLimit = healthLimit;
        this.health = health;
        this.attackValue = attackValue;
        this.isAttack = isAttack;
        this.attackTime = attackTime;
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

    public String getServantName() {

        return servantName;
    }

    public void setServantName(String servantName) {
        this.servantName = servantName;
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

    public void setDeathVoice(boolean deathVoice) {
        isDeathVoice = deathVoice;
    }
}
