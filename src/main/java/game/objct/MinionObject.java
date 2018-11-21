package game.objct;


import game.Gamer;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description : 随从 抽象
 * */
public class MinionObject extends GameObject {
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
    /**光环*/
    protected BuffHole buffHole = null;

    /**本回合增加的攻击力*/
    private long attackValueThisTurn = 0;
    /**光环所增加的攻击力*/
    private long buffHoleAttack = 0;
    /**光环所增加的生命值*/
    private long buffHoleHealth = 0;

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
     * @Description : 受伤害
     */
    public void beHurt(long number){
        System.out.println(minionName+"受到"+number+"点伤害");
        health -= number;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/19 18:16
     * @method : addAttack
     * @params : [addAttack]
     * @Description : 增加攻击力
     */
    public void addAttack(long addAttack){
        System.out.println(minionName+"增加"+addAttack+"点攻击");
        attackValue += addAttack;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/22 11:54
     * @method : addAttack
     * @params : [addAttack]
     * @Description : 本回合增加攻击力
     */
    public void addAttackThisTurn(long addAttack){
        System.out.println(minionName+"本回合增加"+addAttack+"点攻击");
        attackValue += addAttack;
        attackValueThisTurn += addAttack;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 8:49
     * @method : reduceAttack
     * @Description : 减少攻击力，攻击力可以为负数，但显示为0
     */
    public void reduceAttack(long reduceAttack){
        System.out.println(minionName+"减少"+reduceAttack+"点攻击");
        attackValue -= reduceAttack;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 8:51
     * @method : reduceAttack
     * @Description : 减少生命值上限
     */
    public void reduceHealthLimit(long reduceHealthLimit){
        System.out.println(minionName+"减少"+reduceHealthLimit+"点生命上限");
        healthLimit -= reduceHealthLimit;
        //如果当前生命值大于生命值上限，一并减少
        checkHealth();
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 8:55
     * @method : checkHealth
     * @Description : 生命值不得大于上限
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
     * @Description : 改变生命值
     */
    public void changeHealth(long hp){
        healthLimit = hp;
        health = hp;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 8:57
     * @method : addHealthLimit
     * @Description : 增加生命值上限
     */
    public void addHealthLimit(long addHealthLimit){
        System.out.println(minionName+"增加"+addHealthLimit+"点生命值");
        healthLimit += addHealthLimit;
        health += addHealthLimit;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/11/21 17:24
     * @Description : 获得嘲讽
     */
    public void addTaunt(){
        System.out.println(minionName+"获得嘲讽");
        isTaunt = true;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 恢复生命值
     * */
    public void recovery(long number){
        //防止上限溢出
        long newHealth = health + number >= healthLimit?healthLimit:health+number;
        System.out.println(minionName+"恢复"+number+"点生命值");
        health = newHealth;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/11/21 14:53
     * @method : addBuffHoleAttack
     * @Description : 增加光环所增加的攻击力
     */
    public void addBuffHoleAttack(long number){
        buffHoleAttack += number;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/11/21 14:53
     * @method : addBuffHoleHealth
     * @Description : 增加光环所增加的生命值
     */
    public void addBuffHoleHealth(long number){
        buffHoleHealth += number;
    }

    public void cleanBuffHoleAttribute(){
        buffHoleAttack = 0;
        buffHoleHealth = 0;
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
        this.spellDamage = 0;
    }

    public long getHealthLimit() {
        return healthLimit;
    }

    public void setHealthLimit(long healthLimit) {
        this.healthLimit = healthLimit;
    }

    public long getHealth() {
        return health + buffHoleHealth;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public long getAttackValue() {
        return attackValue + buffHoleAttack >= 0 ?attackValue+buffHoleAttack:0;
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

    public boolean isTaunt() {
        return isTaunt;
    }

    public void setTaunt(boolean taunt) {
        isTaunt = taunt;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public BuffHole getBuffHole() {
        return buffHole;
    }

    public void setBuffHole(BuffHole buffHole) {
        this.buffHole = buffHole;
    }

    public long getBuffHoleAttack() {
        return buffHoleAttack;
    }

    public void setBuffHoleAttack(long buffHoleAttack) {
        this.buffHoleAttack = buffHoleAttack;
    }

    public long getBuffHoleHealth() {
        return buffHoleHealth;
    }

    public void setBuffHoleHealth(long buffHoleHealth) {
        this.buffHoleHealth = buffHoleHealth;
    }
}
