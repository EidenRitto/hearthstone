package game;


import game.card.AbstractCard;
import game.card.MagicCard;
import game.card.MinionCard;
import game.card.base.CoinCard;
import game.hero.Profession;
import game.objct.GameObject;
import game.objct.MinionObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @version : 1.0
 * @description : 玩家类
 * */
public class Gamer extends GameObject{
    /**手牌上限*/
    private static final int HANDS_LIMIT = 10;
    /**法力水晶上限*/
    private static final int MAGIC_LIMIT = 10;
    /**先手初始手牌数量*/
    private static final int FIRST_HANDS_INIT = 3;
    /**后手初始手牌数量*/
    private static final int LAST_HANDS_INIT = 4;


    /**职业*/
    private Profession myProfession;
    /**生命值上限*/
    private long defaultHealth = 30L;
    /**当前生命值*/
    private long health;
    /**攻击力*/
    private long attackValue;
    /**是否可以攻击*/
    private boolean isAttack;
    /**本回合攻击次数*/
    private int attackTime;
    /**手牌*/
    private List<AbstractCard> handsCards;
    /**牌堆*/
    private List<AbstractCard> cards;
    /**场面上的随从*/
    private List<MinionObject> minions;
    /**墓地*/
    private List<MinionObject> tomb;
    /**空的法力水晶*/
    private int magicCrystal;
    /**当前可用法力水晶*/
    private int magicCrystalNow;
    /**疲劳计数器*/
    private int fatigueCounter;
    /**法术强度*/
    private int spellDamage;
    /**护甲*/
    private int armor;

    /**敌方英雄*/
    private Gamer enemy;

    /**随机种子*/
    private Random randomSeed;
    /**抉择选项*/
    private int chooseOne;

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 开始一个新的回合
     * */
    public void newTurnStart(){
        //抽一张牌
        drawCard(1);
        System.out.println("--你抽到了"+handsCards.get(handsCards.size()-1).getCardName());
        //最大法力水晶加一
        addMagicCrystal(1);
        //恢复已使用的法力水晶
        reMagicCrystalNow();
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * @description : 开始一局新游戏
     * */
    public boolean newGameStart(){
        //分配先后手
        boolean isFirstTurn = randomSeed.nextBoolean();
        if (isFirstTurn){
            System.out.println("你随机分配到了先手");
        }else {
            System.out.println("你随机分配到了后手，你可以多一张手牌并获得幸运币");
        }
        //抽初始手牌阶段
        if (isFirstTurn){
            drawCard(FIRST_HANDS_INIT);
            getEnemy().drawCard(LAST_HANDS_INIT);
            //后手加入幸运币
            getEnemy().addHandsCard(new CoinCard());
        }else {
            drawCard(LAST_HANDS_INIT);
            addHandsCard(new CoinCard());
            getEnemy().drawCard(FIRST_HANDS_INIT);
        }
        //换牌阶段(==暂无==)
        //分配手牌
        return isFirstTurn;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 获得空的法力水晶
     * */
    public void addMagicCrystal(int number){
        //超过上限无效
        magicCrystal = magicCrystal + number >= MAGIC_LIMIT?MAGIC_LIMIT:magicCrystal + number;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 获得临时的法力水晶
     * */
    public void addMagicCrystalNow(int number){
        magicCrystalNow += number;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 恢复已使用的法力水晶
     * */
    public void reMagicCrystalNow(){
        magicCrystalNow = magicCrystal;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 使用法力水晶
     * */
    public void useMagicCrystalNow(int number){
        magicCrystalNow -= number;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 得到一张手牌
     * */
    public void addHandsCard(AbstractCard card){
        if (!isHandsFull()){
            //如果手牌没满，添加到手牌中
            handsCards.add(card);
        }else {
            //手排满了，爆牌
        }
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @param cardIndex 手牌编号
     * @description : 失去一张手牌
     * */
    public void lossHandsCard(int cardIndex){
        handsCards.remove(cardIndex);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 移除牌堆顶部一张牌
     * */
    public void lossLastCards(){
        cards.remove(cards.size()-1);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 获取牌堆顶部一张牌
     * */
    public AbstractCard getLastCards(){
        return cards.get(cards.size()-1);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 从牌堆中抽牌
     * */
    public void drawCard(int number){
        for (int i = 0; i < number; i++) {
            //获取排队顶那一张牌
            AbstractCard lastCard = getLastCards();
            //移除排队顶的牌
            lossLastCards();
            //添加到手牌中
            addHandsCard(lastCard);
        }
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 从手牌中打出随从
     * */
    public void useThisMinionCard(int number,GameObject target){
        //获得随从卡
        MinionCard minionCard = (MinionCard)handsCards.get(number);
        //消耗对应的法力值
        useMagicCrystalNow(minionCard.getCost());
        //获得一张手牌指向的随从
        MinionObject minionObject = minionCard.getMinion();
        //战吼
        if (minionObject.isBattle()){
            minionObject.doBattle(target);
        }
        //随从进入战场
        addMinion(minionObject);
        //从手牌中移除随从卡牌
        lossHandsCard(number);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * @description : 从手牌中打出法术
     * */
    public void useThisMagicCard(int number,GameObject target){
        //获得法术卡
        MagicCard magicCard = (MagicCard)handsCards.get(number);
        //消耗对应的法力值
        useMagicCrystalNow(magicCard.getCost());
        //魔法效果
        magicCard.magicEffect(this,target);
        //从手牌中移除随从卡牌
        lossHandsCard(number);
    }
    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 添加一个随从
     * */
    public void addMinion(MinionObject minion){
        minions.add(minion);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 移除一个随从
     * */
    public void removeMinion(int index){
        minions.remove(index);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * @description : 随从死亡
     * */
    public void deathMinion(int index){
        System.out.println(minions.get(index).getMinionName()+"死亡");
        //执行亡语
        minions.get(index).doDeathRattle(this);
        //随从进入墓地
        tomb.add(minions.get(index));
        //移除随从
        minions.remove(index);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 获取一个随从
     * */
    public MinionObject getMinion(int index){
        return minions.get(index);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 受伤害
     * */
    public void beHurt(long number){
        System.out.println("英雄受到"+number+"点伤害");
        health -= number;
        if (health<=0){
            gameOver();
        }
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/19 15:59
     * @method : addArmor
     * @params : [armorNumber]
     * @Description : 叠甲
     */
    public void addArmor(int armorNumber){
        armor += armorNumber;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/18 16:15
     * @method : gameOver
     * @Description : 游戏结束
     */
    private void gameOver() {

    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 恢复生命值
     * */
    public void recovery(long number){
        //防止上限溢出
        long newHealth = health + number >= defaultHealth?defaultHealth:health+number;
        System.out.println("英雄恢复"+number+"点生命值");
        health = newHealth;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 洗牌
     * */
    public List<AbstractCard> initRandomCards(List<AbstractCard> oldCards){
        Collections.shuffle(oldCards);
        return oldCards;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/19 9:39
     * @method : getGamerSpellDamage
     * @return : int
     * @Description : 计算总法强
     */
    public int getGamerSpellDamage(){

        int gamerSpellDamage = spellDamage;
        for (MinionObject minion:minions) {
            gamerSpellDamage += minion.getSpellDamage();
        }
        return gamerSpellDamage;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * @description : 获取当前状态
     * */
    public void getState(){
        System.out.println("玩家当前手牌：");
        handsCards.forEach(card->{
            System.out.print(card.getCardName()+" ");
        });
        System.out.println();
        System.out.println("当前生命值："+health+"/"+defaultHealth);
        System.out.println("当前法力水晶："+magicCrystalNow+"/"+magicCrystal);
        System.out.println("场上随从:");
        minions.forEach(minionObject -> {
            System.out.print(minionObject.getMinionName()+" "+minionObject.getAttackValue()+"/"
                    +minionObject.getHealth()+"  ");
        });
        System.out.println();
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 初始化游戏
     * */
    public void init(Profession myProfession, List<AbstractCard> cards){
        this.myProfession = myProfession;
        this.health = defaultHealth;
        this.handsCards = new ArrayList<>(10);
        this.cards = initRandomCards(cards);
        this.tomb = new ArrayList<>();
        this.magicCrystal = 0;
        this.minions = new ArrayList<>(7);
        this.enemy = null;
        this.randomSeed = new Random();
        this.fatigueCounter = 0;
        this.attackTime=1;
        this.attackValue=0;
        this.isAttack=false;
        this.spellDamage=0;
        this.armor=0;
        this.chooseOne=-1;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * @description : 手牌是否已满
     * */
    public boolean isHandsFull(){
        return handsCards.size() >= HANDS_LIMIT;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * @description : 是否是敌人
     * */
    public boolean isEnemy(){
        return this.getEnemy()==null;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * @description : 检查手牌能否使用
     * */
    public boolean checkUse(int cardId){
        boolean result = true;
        if (!checkCardMagic(cardId)){
            System.out.println("你没有足够的法力值!");
            result = false;
        }
        return result;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * @description : 检查随从血量，小于1则死亡
     * */
    public void checkMinion(){
        List<Integer> deadIds = new ArrayList<>(14);
        for (int i=0;i<minions.size();i++){
            if (minions.get(i).getHealth()<=0){
                deadIds.add(i);
            }
        }
        Collections.reverse(deadIds);
        deadIds.forEach(this::deathMinion);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/19 18:11
     * @method : addMinionsAttack
     * @Description : 友方全部随从增加攻击力
     */
    public void addMinionsAttack(long addAttack){
        minions.forEach(minionObject -> minionObject.addAttack(addAttack));
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 9:00
     * @method : addMinionsHealth
     * @Description : 友方全部随从增加生命值
     */
    public void addMinionsHealth(long addHealth){
        minions.forEach(minionObject -> minionObject.addHealthLimit(addHealth));
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 9:03
     * @method : buffYourAllMinions
     * @params : [addAttack, addHealth]
     * @Description : 友方随从获得 +[addAttack]/+[addHealth]
     */
    public void buffYourAllMinions(long addAttack,long addHealth){
        addMinionsAttack(addAttack);
        addMinionsHealth(addHealth);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/21 16:29
     * @method : findAllCanAttackMinionsId
     * @return : java.util.List<java.lang.Integer>
     * @Description : 查找全部能够攻击的随从
     */
    public List<Integer> findAllCanAttackMinionsId(){
        List<Integer> resultList = new ArrayList<>();
        for (Integer i = 0;i<minions.size();i++) {
            if (minions.get(i).getAttackTime()>0){
                resultList.add(i);
            }
        }
        return resultList;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/21 17:12
     * @method : findAllCanBeAttackMinionsId
     * @return : java.util.List<java.lang.Integer>
     * @Description : 查找全部能够被攻击的随从(敌方的)
     */
    public List<Integer> findAllCanBeAttackMinionsId(){
        List<Integer> resultList = new ArrayList<>();
        //判断敌方是否拥有嘲讽随从，有则添加嘲讽随从，
        for (Integer i = 0;i<enemy.getMinions().size();i++){
            if (enemy.getMinion(i).isTaunt()){
                resultList.add(i);
            }
        }
        //无则添加全部
        if (resultList.size()==0){
            for (Integer i = 0;i<enemy.getMinions().size();i++){
                resultList.add(i);
            }
        }
        return resultList;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * @description : 检查手牌费用是否足够打出
     * */
    public boolean checkCardMagic(int cardId){
        return handsCards.get(cardId).getCost() <= magicCrystalNow;
    }

    public Gamer(Profession myProfession, List<AbstractCard> cards) {
        init(myProfession, cards);
    }

    public Profession getMyProfession() {
        return myProfession;
    }

    public void setMyProfession(Profession myProfession) {
        this.myProfession = myProfession;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public List<AbstractCard> getHandsCards() {
        return handsCards;
    }

    public void setHandsCards(List<AbstractCard> handsCards) {
        this.handsCards = handsCards;
    }

    public List<AbstractCard> getCards() {
        return cards;
    }

    public void setCards(List<AbstractCard> cards) {
        this.cards = cards;
    }

    public List<MinionObject> getTomb() {
        return tomb;
    }

    public void setTomb(List<MinionObject> tomb) {
        this.tomb = tomb;
    }

    public int getMagicCrystal() {
        return magicCrystal;
    }

    public void setMagicCrystal(int magicCrystal) {
        this.magicCrystal = magicCrystal;
    }

    public long getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }

    public int getMagicCrystalNow() {
        return magicCrystalNow;
    }

    public void setMagicCrystalNow(int magicCrystalNow) {
        this.magicCrystalNow = magicCrystalNow;
    }

    public List<MinionObject> getMinions() {
        return minions;
    }

    public void setMinions(List<MinionObject> minions) {
        this.minions = minions;
    }

    public Gamer getEnemy() {
        return enemy;
    }

    public void setEnemy(Gamer enemy) {
        this.enemy = enemy;
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

    public int getFatigueCounter() {
        return fatigueCounter;
    }

    public void setFatigueCounter(int fatigueCounter) {
        this.fatigueCounter = fatigueCounter;
    }

    public int getSpellDamage() {
        return spellDamage;
    }

    public void setSpellDamage(int spellDamage) {
        this.spellDamage = spellDamage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getChooseOne() {
        return chooseOne;
    }

    public void setChooseOne(int chooseOne) {
        this.chooseOne = chooseOne;
    }
}
