package cn.eiden.hsm.game;


import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.event.EventManager;
import cn.eiden.hsm.event.events.AddMinionEvent;
import cn.eiden.hsm.event.events.BattlefieldChangeEvent;
import cn.eiden.hsm.event.events.MinionDeathEvent;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.base.CoinCard;
import cn.eiden.hsm.game.objct.hero.HeroObjectAbstract;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.output.OutputInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 玩家类
 *
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class Gamer extends GameObject {
    /**
     * 手牌上限
     */
    private int handsLimit = 10;

    /**
     * 先手初始手牌数量
     */
    private static final int FIRST_HANDS_INIT = 3;
    /**
     * 后手初始手牌数量
     */
    private static final int LAST_HANDS_INIT = 4;


    /**
     * 英雄
     */
    private HeroObjectAbstract hero;

    /**
     * 手牌
     */
    private Hand hand;
    /**
     * 牌堆
     */
    private List<Card> cards;
    /**
     * 场面上的随从
     */
    private List<Minion> minions;
    /**
     * 墓地
     */
    private List<Minion> tomb;
    /**
     * 法力水晶
     */
    private ManaCrystal manaCrystal;

    /**
     * 疲劳计数器
     */
    private int fatigueCounter;

    /**
     * 敌方英雄
     */
    private Gamer enemy;

    /**
     * 随机种子
     */
    private Random randomSeed;
    /**
     * 抉择选项
     */
    private int chooseOne;

    /**
     * 事件管理器
     */
    private final EventManager eventManager = EventManager.getInstance();

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 开始一个新的回合
     */
    public void newTurnStart() {
        //抽一张牌
        drawCard(1);
        getManaCrystal().newTurnStart();
        getMinions().forEach(Minion::newTurnStart);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * 开始一局新游戏
     */
    public boolean newGameStart() {
        //分配先后手
        boolean isFirstTurn = randomSeed.nextBoolean();
        if (isFirstTurn) {
            OutputInfo.info("你随机分配到了先手");
        } else {
            OutputInfo.info("你随机分配到了后手，你可以多一张手牌并获得幸运币");
        }
        //抽初始手牌阶段
        if (isFirstTurn) {
            drawCard(FIRST_HANDS_INIT);
            getEnemy().drawCard(LAST_HANDS_INIT);
            //后手加入幸运币
            getEnemy().getHand().addHandsCard(new CoinCard());
        } else {
            drawCard(LAST_HANDS_INIT);
            getHand().addHandsCard(new CoinCard());
            getEnemy().drawCard(FIRST_HANDS_INIT);
        }
        //换牌阶段(==暂无==)
        //分配手牌
        return isFirstTurn;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 移除牌堆顶部一张牌
     */
    public void lossLastCards() {
        cards.remove(cards.size() - 1);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 获取牌堆顶部一张牌
     */
    public Card getLastCards() {
        return cards.get(cards.size() - 1);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 从牌堆中抽牌
     */
    public void drawCard(int number) {
        for (int i = 0; i < number; i++) {
            //获取牌堆顶那一张牌
            Card lastCard = getLastCards();
            //移除牌堆顶的牌
            lossLastCards();
            //添加到手牌中
            getHand().addHandsCard(lastCard);
            OutputInfo.info("--你抽到了" + lastCard.getCardName());
        }
    }

    /**
     * 从手牌中打出随从
     *
     * @param number 编号
     * @param target 所选目标
     */
    public void useThisMinionCard(int number, Minion target) {
        //获得随从卡
        Card card = getHand().getCard(number);
        this.useThisMinionCard(card,target);
    }

    public void useThisMinionCard(Card card, Minion target) {
        //获得随从卡
        AbstractMinionCard minionCard = (AbstractMinionCard) card;
        //消耗对应的法力值
        getManaCrystal().applyAvailable(minionCard.getCost());
        //获得一张手牌指向的随从
        Minion minion = minionCard.createMinion();
        minion.setOwner(this);
        //发布事件[从手牌中打出随从卡牌事件]
        AbstractEvent abstractEvent = new UseMinionCardFromHandEvent(this, minion, target);
        eventManager.call(abstractEvent);

        //随从进入战场
        addMinion(minion);
        //从手牌中移除随从卡牌
        getHand().loss(card);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * 从手牌中打出法术
     */
    public void useThisMagicCard(int number, Minion target) {
        Card card = getHand().getCard(number);
        this.useThisMagicCard(card,target);
    }

    public void useThisMagicCard(Card card, Minion target) {
        if (!isRightTarget(target)){
            OutputInfo.info("这不是一个有效的目标");
            return;
        }
        //获得法术卡
        AbstractMagicCard magicCard = (AbstractMagicCard) card;
        //消耗对应的法力值
        getManaCrystal().applyAvailable(magicCard.getCost());
        //魔法效果
        magicCard.magicEffect(this, target);
        //从手牌中移除随从卡牌
        getHand().loss(card);
    }

    /**
     * 是否是正确的目标
     * @param target 目标，可能为随从或英雄
     * @return 正确的目标返回true
     */
    private boolean isRightTarget(Minion target){
        Class<? extends Minion> clazz = target.getClass();
        boolean friend = isFriend(target);
        TargetScope annotation = clazz.getAnnotation(TargetScope.class);
        if (!clazz.isAssignableFrom(annotation.classScope())){
            return false;
        }
        switch (annotation.stand()){
            case FRIEND:
                return friend;
            case FOE:
                return !friend;
            case ALL:
                return true;
        }
        return true;
    }

    /**
     * 是否是友方单位
     * @param minion 随从或英雄
     * @return 是友方返回true
     */
    private boolean isFriend(Minion minion){
        HeroObjectAbstract hero = getHero();
        return minions.contains(minion) || hero == minion;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 添加一个随从
     */
    public void addMinion(Minion minion) {
        BattlefieldChangeEvent battlefieldChangeEvent = new BattlefieldChangeEvent(this);
        AddMinionEvent addMinionEvent = new AddMinionEvent(this,minion);
        minion.setOwner(this);
        minions.add(minion);
        eventManager.call(battlefieldChangeEvent);
        eventManager.call(addMinionEvent);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 移除一个随从
     */
    public void removeMinion(int index) {
        BattlefieldChangeEvent battlefieldChangeEvent = new BattlefieldChangeEvent(this);
        minions.remove(index);
        eventManager.call(battlefieldChangeEvent);
    }

    /**
     * 随从死亡
     *
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     */
    public void deathMinion(int index) {
        Minion abstractMinionObject = minions.get(index);
        OutputInfo.info(abstractMinionObject.getMinionName() + "死亡");
        AbstractEvent minionDeathEvent = new MinionDeathEvent(this, abstractMinionObject);
        eventManager.call(minionDeathEvent);
        //移除随从
        this.removeMinion(index);
        //随从进入墓地
        tomb.add(abstractMinionObject);
    }

    /**
     * 获取一个随从
     */
    public Minion getMinion(int index) {
        return minions.get(index);
    }


    /**
     * 游戏结束
     */
    private void gameOver() {

    }


    /**
     * 洗牌
     */
    public List<Card> initRandomCards(List<Card> oldCards) {
        Collections.shuffle(oldCards);
        return oldCards;
    }

    /**
     * 计算总法强
     */
    public int getGamerSpellDamage() {
        int gamerSpellDamage = hero.getSpellPower();
        for (Minion minion : minions) {
            gamerSpellDamage += minion.getSpellPower();
        }
        return gamerSpellDamage;
    }

    /**
     * 获取当前状态
     */
    public void getState() {
        StringBuilder handInfo = new StringBuilder("玩家当前手牌:");
        List<Card> cards = getHand().getCards();
        for (int i = 0; i < cards.size(); i++) {
            handInfo.append(cards.get(i).getCardName());
            handInfo.append("[").append(i).append("]");
            handInfo.append("(").append(cards.get(i).getCost()).append(")");
            handInfo.append(" ");
        }
        handInfo.append("\n");
        handInfo.append("当前生命值：");
        handInfo.append(hero.getHealth());
        handInfo.append("/");
        handInfo.append(hero.getHealthLimit());
        handInfo.append("\n");
        handInfo.append("当前法力水晶：");
        handInfo.append(getManaCrystal().getAvailable());
        handInfo.append("/");
        handInfo.append(getManaCrystal().getManaCrystal());
        handInfo.append("[").append(getManaCrystal().getLocked()).append("]");
        OutputInfo.info(handInfo.toString());

        StringBuilder stringBuilder = new StringBuilder("场上随从:");
        for (Minion minionObject : minions) {
            stringBuilder.append(minionObject.getMinionName());
            stringBuilder.append(" ");
            stringBuilder.append(minionObject.getAttackValue());
            stringBuilder.append("/");
            stringBuilder.append(minionObject.getHealth());
        }
        OutputInfo.info(stringBuilder.toString());
    }

    /**
     * @date : 2018/9/13
     * 初始化
     */
    public void init(HeroObjectAbstract heroObject, List<Card> cards) {
        this.hero = heroObject;
        this.hand = new Hand();
        this.cards = initRandomCards(cards);
        this.tomb = new ArrayList<>();
        this.manaCrystal = new ManaCrystal();
        this.minions = new ArrayList<>(7);
        this.enemy = null;
        this.randomSeed = new Random();
        this.fatigueCounter = 0;
        this.chooseOne = -1;
    }

    /**
     * 是否是敌人
     */
    public boolean isEnemy() {
        return this.getEnemy() == null;
    }

    /**
     * 检查场上是否拥有指定种族的随从
     */
    public boolean checkHaveEthnicity(Race race) {
        for (Minion minion : minions) {
            if (minion.getRace() == race) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查手牌能否使用
     */
    public boolean checkUse(Card card) {
        boolean result = true;
        if (!checkCardMagic(card)) {
            OutputInfo.info("你没有足够的法力值!");
            result = false;
        }
        return result;
    }


    /**
     * 检查随从血量，小于1则死亡
     */
    public void checkMinion() {
        List<Integer> deadIds = new ArrayList<>(14);
        for (int i = 0; i < minions.size(); i++) {
            if (minions.get(i).getHealth() <= 0) {
                deadIds.add(i);
            }
        }
        Collections.reverse(deadIds);
        deadIds.forEach(this::deathMinion);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * 检查英雄血量，小于1则游戏结束
     */
    public void checkHero() {
        if (hero.getHealth() <= 0) {
            gameOver();
        }
    }

    /**
     * 友方全部随从增加攻击力
     * @param addAttack 攻击力
     */
    public void addMinionsAttack(long addAttack) {
        minions.forEach(minionObject -> minionObject.addAttack(addAttack));
    }

    /**
     * 友方全部随从增加生命值
     * @param addHealth 生命值
     */
    public void addMinionsHealth(long addHealth) {
        minions.forEach(minionObject -> minionObject.addHealthLimit(addHealth));
    }

    /**
     * @param addAttack 攻击力
     * @param addHealth 生命值
     * 友方随从获得 +[addAttack]/+[addHealth]
     */
    public void buffYourAllMinions(long addAttack, long addHealth) {
        addMinionsAttack(addAttack);
        addMinionsHealth(addHealth);
    }

    /**
     * 查找全部能够攻击的随从
     */
    public List<Integer> findAllCanAttackMinionsId() {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < minions.size(); i++) {
            if (minions.get(i).isAttack()) {
                resultList.add(i);
            }
        }
        return resultList;
    }

    /**
     * 打印全部能够攻击的随从信息
     */
    public void printAllCanAttackMinionsInfo(){
        List<Integer> allCanAttackMinionsId = findAllCanAttackMinionsId();
        StringBuilder info = new StringBuilder();
        for (Integer mid : allCanAttackMinionsId) {
            Minion minion = minions.get(mid);
            info.append("[").append(mid).append("]").append(minion.getMinionName()).append("\n");
        }
        OutputInfo.info(info.toString());
    }

    /**
     * 打印全部能够[被]攻击的随从信息
     */
    public void printAllCanBeAttackMinionsInfo(){
        List<Integer> allCanBeAttackMinionsId = findAllCanBeAttackMinionsId();
        StringBuilder info = new StringBuilder();
        for (Integer mid : allCanBeAttackMinionsId) {
            Minion minion = getEnemy().getMinions().get(mid);
            info.append("[").append(mid).append("]").append(minion.getMinionName()).append("\n");
        }
        OutputInfo.info(info.toString());
    }

    /**
     * 打印手牌信息
     */
    public void printHandsInfo(){
        StringBuilder handInfo = new StringBuilder("玩家当前手牌:");
        List<Card> cards = getHand().getCards();
        for (int i = 0; i < cards.size(); i++) {
            handInfo.append("[").append(i).append("]");
            handInfo.append(cards.get(i).getCardName());
            handInfo.append("(").append(cards.get(i).getCost()).append(")");
            handInfo.append("\n");
        }
        OutputInfo.info(handInfo.toString());
    }

    /**
     * @return : java.util.List<java.lang.Integer>
     * 查找全部能够被攻击的随从(敌方的)
     * @author : Eiden J.P Zhou
     * @date : 2018/9/21 17:12
     */
    public List<Integer> findAllCanBeAttackMinionsId() {
        List<Integer> resultList = new ArrayList<>();
        boolean hasTaunt = false;
        //判断敌方是否拥有嘲讽随从，有则添加嘲讽随从，
        for (int i = 0; i < enemy.getMinions().size(); i++) {
            Minion minion = enemy.getMinion(i);
            if (minion.isTaunt() && !minion.isStealth()) {
                resultList.add(i);
                hasTaunt = true;
            }
        }
        //无则添加全部
        if (!hasTaunt) {
            for (int i = 0; i < enemy.getMinions().size(); i++) {
                Minion minion = enemy.getMinion(i);
                if (!minion.isStealth()){
                    resultList.add(i);
                }
            }
        }
        return resultList;
    }

    /**
     * @param cardId 卡片序号
     *               检查手牌费用是否足够打出
     */
    public boolean checkCardMagic(int cardId) {
        Card card = getHand().getCard(cardId);
        return this.checkCardMagic(card);
    }

    public boolean checkCardMagic(Card cardId) {
        return getManaCrystal().checkCost(cardId.getCost());
    }

    public Hand getHand() {
        return hand;
    }

    public Gamer(HeroObjectAbstract heroObject, List<Card> cards) {
        init(heroObject, cards);
    }
}
