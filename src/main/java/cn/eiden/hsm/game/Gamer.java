package cn.eiden.hsm.game;


import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.event.EventManager;
import cn.eiden.hsm.event.events.MinionDeathEvent;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.game.card.AbstractCard;
import cn.eiden.hsm.game.card.MagicCard;
import cn.eiden.hsm.game.card.MinionCard;
import cn.eiden.hsm.game.card.base.CoinCard;
import cn.eiden.hsm.game.hero.HeroObjectAbstract;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.BuffHole;
import cn.eiden.hsm.game.objct.Ethnicity;
import cn.eiden.hsm.game.objct.GameObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 玩家类
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class Gamer extends GameObject {
    /**手牌上限*/
    private static final int HANDS_LIMIT = 10;
    /**法力水晶上限*/
    private static final int MAGIC_LIMIT = 10;
    /**先手初始手牌数量*/
    private static final int FIRST_HANDS_INIT = 3;
    /**后手初始手牌数量*/
    private static final int LAST_HANDS_INIT = 4;


    /**英雄*/
    private HeroObjectAbstract hero;

    /**手牌*/
    private List<AbstractCard> handsCards;
    /**牌堆*/
    private List<AbstractCard> cards;
    /**场面上的随从*/
    private List<AbstractMinionObject> minions;
    /**墓地*/
    private List<AbstractMinionObject> tomb;
    /**空的法力水晶*/
    private int magicCrystal;
    /**当前可用法力水晶*/
    private int magicCrystalNow;
    /**疲劳计数器*/
    private int fatigueCounter;

    /**敌方英雄*/
    private Gamer enemy;

    /**随机种子*/
    private Random randomSeed;
    /**抉择选项*/
    private int chooseOne;

    private final EventManager eventManager = EventManager.getInstance();

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  开始一个新的回合
     * */
    public void newTurnStart(){
        //抽一张牌
        drawCard(1);
        log.info("--你抽到了"+handsCards.get(handsCards.size()-1).getCardName());
        //最大法力水晶加一
        addMagicCrystal(1);
        //恢复已使用的法力水晶
        reMagicCrystalNow();
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     *  开始一局新游戏
     * */
    public boolean newGameStart(){
        //分配先后手
        boolean isFirstTurn = randomSeed.nextBoolean();
        if (isFirstTurn){
            log.info("你随机分配到了先手");
        }else {
            log.info("你随机分配到了后手，你可以多一张手牌并获得幸运币");
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
     *  获得空的法力水晶
     * */
    public void addMagicCrystal(int number){
        //超过上限无效
        magicCrystal = magicCrystal + number >= MAGIC_LIMIT?MAGIC_LIMIT:magicCrystal + number;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  获得临时的法力水晶
     * */
    public void addMagicCrystalNow(int number){
        magicCrystalNow += number;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  恢复已使用的法力水晶
     * */
    public void reMagicCrystalNow(){
        magicCrystalNow = magicCrystal;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  使用法力水晶
     * */
    public void useMagicCrystalNow(int number){
        magicCrystalNow -= number;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  得到一张手牌
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
     *  失去一张手牌
     * */
    public void lossHandsCard(int cardIndex){
        handsCards.remove(cardIndex);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  移除牌堆顶部一张牌
     * */
    public void lossLastCards(){
        cards.remove(cards.size()-1);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  获取牌堆顶部一张牌
     * */
    public AbstractCard getLastCards(){
        return cards.get(cards.size()-1);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  从牌堆中抽牌
     * */
    public void drawCard(int number){
        for (int i = 0; i < number; i++) {
            //获取牌堆顶那一张牌
            AbstractCard lastCard = getLastCards();
            //移除牌堆顶的牌
            lossLastCards();
            //添加到手牌中
            addHandsCard(lastCard);
        }
    }

    /**
     * 从手牌中打出随从
     * @param number 编号
     * @param target 所选目标
     * */
    public void useThisMinionCard(int number,GameObject target){
        //获得随从卡
        MinionCard minionCard = (MinionCard)handsCards.get(number);
        //消耗对应的法力值
        useMagicCrystalNow(minionCard.getCost());
        //获得一张手牌指向的随从
        AbstractMinionObject abstractMinionObject = minionCard.getMinion();
        //发布事件[从手牌中打出随从卡牌事件]
        AbstractEvent abstractEvent = new UseMinionCardFromHandEvent(this,abstractMinionObject,target);
        eventManager.call(abstractEvent);

        //随从进入战场
        addMinion(abstractMinionObject);
        //从手牌中移除随从卡牌
        lossHandsCard(number);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     *  从手牌中打出法术
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
     *  添加一个随从
     * */
    public void addMinion(AbstractMinionObject minion){
        minions.add(minion);
        //刷新光环
        checkMinionBuffHole();
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  移除一个随从
     * */
    public void removeMinion(int index){
        minions.remove(index);
    }

    /**
     * 随从死亡
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * */
    public void deathMinion(int index){
        AbstractMinionObject abstractMinionObject = minions.get(index);
        log.info(abstractMinionObject.getMinionName()+"死亡");
        //执行亡语
        AbstractEvent minionDeathEvent = new MinionDeathEvent(this,abstractMinionObject);
        eventManager.call(minionDeathEvent);
        //随从进入墓地
        tomb.add(abstractMinionObject);
        //移除随从
        minions.remove(index);
        //刷新光环
        checkMinionBuffHole();
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  获取一个随从
     * */
    public AbstractMinionObject getMinion(int index){
        return minions.get(index);
    }


    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/18 16:15
     * @method : gameOver
     *  游戏结束
     */
    private void gameOver() {

    }



    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  洗牌
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
     *  计算总法强
     */
    public int getGamerSpellDamage(){
        int gamerSpellDamage = hero.getSpellDamage();
        for (AbstractMinionObject minion:minions) {
            gamerSpellDamage += minion.getSpellDamage();
        }
        return gamerSpellDamage;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     *  获取当前状态
     * */
    public void getState(){
        log.info("玩家当前手牌：");
        handsCards.forEach(card-> System.out.print(card.getCardName()+" "));
        log.info("当前生命值："+hero.getHealth()+"/"+hero.getHealthLimit());
        log.info("当前法力水晶："+magicCrystalNow+"/"+magicCrystal);
        log.info("场上随从:");
        minions.forEach(minionObject -> {
            System.out.print(minionObject.getMinionName()+" "+minionObject.getAttackValue()+"/"
                    +minionObject.getHealth()+"  ");
        });
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  初始化游戏
     * */
    public void init(HeroObjectAbstract heroObject, List<AbstractCard> cards){
        this.hero = heroObject;
        this.handsCards = new ArrayList<>(10);
        this.cards = initRandomCards(cards);
        this.tomb = new ArrayList<>();
        this.magicCrystal = 0;
        this.minions = new ArrayList<>(7);
        this.enemy = null;
        this.randomSeed = new Random();
        this.fatigueCounter = 0;
        this.chooseOne=-1;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     *  手牌是否已满
     * */
    public boolean isHandsFull(){
        return handsCards.size() >= HANDS_LIMIT;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     *  是否是敌人
     * */
    public boolean isEnemy(){
        return this.getEnemy()==null;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/11/21 16:30
     *  检查场上是否拥有指定种族的随从
     */
    public boolean checkHaveEthnicity(Ethnicity ethnicity){
        for (AbstractMinionObject minion : minions) {
            if (minion.getEthnicity() == ethnicity) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     *  检查手牌能否使用
     * */
    public boolean checkUse(int cardId){
        boolean result = true;
        if (!checkCardMagic(cardId)){
            log.info("你没有足够的法力值!");
            result = false;
        }
        return result;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     *  检查随从血量，小于1则死亡
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
     * @date : 2018/11/21 15:02
     * @method : checkMinionBuffHole
     *  刷新随从光环所添加的属性【默认为对自身无效】
     */
    public void checkMinionBuffHole(){
        BuffHole buffHole;
        //清空属性
        minions.forEach(AbstractMinionObject::cleanBuffHoleAttribute);
        for (int i = 0;i < minions.size();i++){
            buffHole = minions.get(i).getBuffHole();
            if (buffHole != null){
                if (buffHole.getPrerequisite() != null){
                    for (int j = 0; j < minions.size(); j++){
                        if (i != j && minions.get(j).getEthnicity() == buffHole.getPrerequisite()){
                            minions.get(j).addBuffHoleAttack(buffHole.getAddAttack());
                            minions.get(j).addBuffHoleHealth(buffHole.getAddHealth());
                        }
                    }
                }else {
                    for (int j=0; j < minions.size(); j++){
                        if (i != j){
                            minions.get(j).addBuffHoleAttack(buffHole.getAddAttack());
                            minions.get(j).addBuffHoleHealth(buffHole.getAddHealth());
                        }
                    }
                }
            }
        }
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     *  检查英雄血量，小于1则游戏结束
     * */
    public void checkHero(){
        if (hero.getHealth()<=0){
            gameOver();
        }
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/19 18:11
     * @method : addMinionsAttack
     *  友方全部随从增加攻击力
     */
    public void addMinionsAttack(long addAttack){
        minions.forEach(minionObject -> minionObject.addAttack(addAttack));
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 9:00
     * @method : addMinionsHealth
     *  友方全部随从增加生命值
     */
    public void addMinionsHealth(long addHealth){
        minions.forEach(minionObject -> minionObject.addHealthLimit(addHealth));
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/20 9:03
     * @method : buffYourAllMinions
     * @params : [addAttack, addHealth]
     *  友方随从获得 +[addAttack]/+[addHealth]
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
     *  查找全部能够攻击的随从
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
     *  查找全部能够被攻击的随从(敌方的)
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
     *  检查手牌费用是否足够打出
     * */
    public boolean checkCardMagic(int cardId){
        return handsCards.get(cardId).getCost() <= magicCrystalNow;
    }

    public Gamer(HeroObjectAbstract heroObject, List<AbstractCard> cards) {
        init(heroObject, cards);
    }
}
