package cn.eiden.hsm.game;


import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.EventManager;
import cn.eiden.hsm.event.events.*;
import cn.eiden.hsm.exception.GameOverException;
import cn.eiden.hsm.game.card.*;
import cn.eiden.hsm.game.history.History;
import cn.eiden.hsm.game.history.HistoryImpl;
import cn.eiden.hsm.game.minion.Weapon;
import cn.eiden.hsm.game.minion.hero.Hero;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Secret;
import cn.eiden.hsm.game.rule.Rule;
import cn.eiden.hsm.output.OutputInfo;
import cn.eiden.hsm.util.RegexUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * 玩家类
 *
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class Gamer extends AbstractGeneralItem {
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
    private Hero hero;

    /**
     * 手牌
     */
    private Hand hand;
    /**
     * 牌堆
     */
    private List<Card> deckCards;
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
     * 是否活动（当前回合中）
     */
    private boolean active;

    @Getter
    private History history = new HistoryImpl();

    private List<Secret> secretList = new ArrayList<>(5);

    private List<Rule> rules = new ArrayList<>();

    @Setter
    private BlockingQueue<String> privateMessageQueue;

    @Setter
    @Getter
    private BlockingQueue<String> inputMessageQueue;

    private String userName;

    /**
     * 事件管理器
     */
    @Getter
    private final EventManager eventManager = EventManager.getInstance();

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 开始一个新的回合
     */
    public void newTurnStart() {
        OutputInfo.info("%s的回合开始=====等待指令中====",userName);
        printPrivateQueue("=======你的回合======");
        active = true;
        //场中随从解除上场回合不能攻击
        getMinions().forEach(Minion::setReady);
        getHero().newTurnStart();
        //恢复英雄技能
        getHero().getHeroPower().recoveryTimes();
        //抽一张牌
        drawCard(1);
        getManaCrystal().newTurnStart();
        getMinions().forEach(Minion::newTurnStart);
    }

    /**
     * 回合结束
     */
    public void endTurn() {
        active = false;
        EndTurnEvent endTurnEvent = new EndTurnEvent(this);
        eventManager.call(endTurnEvent);
        printPublicQueue(String.format("%s的回合结束",userName));
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
            printPrivateQueue("你随机分配到了先手");
        } else {
            printPrivateQueue("你随机分配到了后手，你可以多一张手牌并获得幸运币");
        }
        //抽初始手牌阶段
        if (isFirstTurn) {
            drawCard(FIRST_HANDS_INIT);
            getEnemy().drawCard(LAST_HANDS_INIT);
            //后手加入幸运币
            getEnemy().getHand().addHandsCard(CardFactory.getCardById(1746));
        } else {
            drawCard(LAST_HANDS_INIT);
            getHand().addHandsCard(CardFactory.getCardById(1746));
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
        deckCards.remove(deckCards.size() - 1);
    }

    /**
     * 移除牌堆中的一张牌
     *
     * @param card 排队中的牌
     */
    public boolean deckLoss(Card card) {
        return deckCards.remove(card);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 获取牌堆顶部一张牌
     */
    public Card getLastCards() {
        return deckCards.get(deckCards.size() - 1);
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
            drawCard(lastCard);
        }
    }

    /**
     * 从牌堆中抽牌
     *
     * @param card 被抽的牌
     */
    public void drawCard(Card card) {
        card.setOwner(this);
        //移除牌堆顶的牌
        lossLastCards();
        //添加到手牌中
        getHand().addHandsCard(card);
        printPrivateQueue("--你抽到了" + card.getCardName());
    }

    /**
     * 从牌堆中指定卡牌的类型的牌
     *
     * @param cardType 卡牌类型
     */
    public void drawCardOfCardType(CardType cardType) {
        for (Card deckCard : deckCards) {
            if (deckCard.getCardType() == cardType) {
                drawCard(deckCard);
                break;
            }
        }
    }

    /**
     * 从牌堆中指定类型的牌
     *
     * @param clazz 卡牌类型
     */
    public void drawCardOfType(Class<? extends Card> clazz) {
        for (Card deckCard : deckCards) {
            if (clazz.isInstance(deckCard)) {
                drawCard(deckCard);
                break;
            }
        }
    }


    public void useThisCard(Card card, Minion target) {
        String info;
        if ((card instanceof AbstractSecretCard)){
            info = String.format("%s打出了%s奥秘",userName,card.getCardClass().getCnName());
        }else {
            info = String.format("%s打出了%s",userName,card.getCardName());
            if (target != null){
                info += String.format(",目标%s",target.getMinionName());
            }
        }
        printPublicQueue(info);
        eventManager.call(new UseCardFromHandEvent(this, card));
        if (card instanceof AbstractMagicCard) {
            this.useThisMagicCard(card, target);
        } else if (card instanceof AbstractMinionCard) {
            this.useThisMinionCard(card, target);
        } else if (card instanceof AbstractWeaponCard) {
            this.useThisWeaponCard(card, target);
        }
        history.addUsedCard(card);
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
        this.useThisMinionCard(card, target);
    }

    public void useThisWeaponCard(Card card, Minion target) {
        AbstractWeaponCard weaponCard = (AbstractWeaponCard) card;
        getManaCrystal().applyAvailable(weaponCard.getCost());
        Weapon weapon = weaponCard.createWeapon();
        hero.equipWeapons(weapon);
        //从手牌中移除卡牌
        getHand().loss(card);
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
        UseMinionCardFromHandEvent abstractEvent = new UseMinionCardFromHandEvent(this, minion, target);
        eventManager.call(abstractEvent);

        addMinion(abstractEvent.getMinionObject());
        //从手牌中移除随从卡牌
        getHand().loss(card);
    }

    /**
     * 使用英雄技能
     *
     * @param target 技能目标
     */
    public void useHeroPower(Minion target) {
        AbstractHeroPowerCard heroPower = this.getHero().getHeroPower();
        if (!checkUse(heroPower)) {
            return;
        }
        if (heroPower.getTimesOfUse() < 1) {
            printPrivateQueue("本回合已经使用过技能");
            return;
        }
        if (!isRightTarget(heroPower, target)) {
            printPrivateQueue("这不是一个有效的目标");
            return;
        }
        String info = String.format("%s使用英雄技能%s",userName,heroPower);
        if (target != null){
            info += String.format(",目标%s",target.getMinionName());
        }
        printPublicQueue(info);
        //消耗对应的法力值
        getManaCrystal().applyAvailable(heroPower.getCost());
        //技能效果
        heroPower.powerEffect(this, target);
        //次数-1
        heroPower.subTimes();
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     * 从手牌中打出法术
     */
    public void useThisMagicCard(int number, Minion target) {
        Card card = getHand().getCard(number);
        this.useThisMagicCard(card, target);
    }

    /**
     * 使用法术牌
     *
     * @param card   卡牌
     * @param target 目标
     */
    public void useThisMagicCard(Card card, Minion target) {
        if (!isRightTarget(card, target)) {
            printPrivateQueue("这不是一个有效的目标");
            return;
        }
        //获得法术卡
        AbstractMagicCard magicCard = (AbstractMagicCard) card;
        //消耗对应的法力值
        getManaCrystal().applyAvailable(magicCard.getCost());
        //发出事件
        eventManager.call(new UseSpellCardFromHandEvent(this, magicCard, target));
        if (magicCard instanceof AbstractSecretCard) {
            AbstractSecretCard secretCard = (AbstractSecretCard) magicCard;
            eventManager.call(new UseSecretCardFromHandEvent(this, secretCard));
        }
        //没有被反制则执行魔法效果
        if (!magicCard.isCounter()) {
            magicCard.magicEffect(this, target);
        }
        //从手牌中移除随从卡牌
        getHand().loss(card);
    }

    /**
     * 是否是正确的目标
     *
     * @param card   当前使用卡
     * @param target 所选目标，可能为随从或英雄
     * @return 正确的目标返回true
     */
    private boolean isRightTarget(Card card, Minion target) {
        Class<? extends Card> clazz = card.getClass();
        TargetScope annotation = clazz.getAnnotation(TargetScope.class);
        if (target == null) {
            return annotation == null;
        }
        Class<? extends Minion> targetClass = target.getClass();
        boolean friend = isFriend(target);

        if (!annotation.classScope().isAssignableFrom(targetClass)) {
            return false;
        }
        switch (annotation.stand()) {
            case FRIEND:
                return friend;
            case FOE:
                return !friend;
            case ALL:
                return true;
        }
        return true;
    }

    public void addSecret(Secret secret) {
        if (!this.hasSecret(secret)) {
            String format = String.format("%s挂上了一个奥秘", userName);
            printPublicQueue(format);
            secret.setOwner(this);
            eventManager.registerSecret(secret);
            secretList.add(secret);
        }
    }

    public void onSecret(Secret secret, Event event) {
        if (secretList.contains(secret)) {
            if (secret.onSecret(event)) {
                removeSecret(secret);
            }
        }
    }

    public void removeSecret(Secret secret) {
        secretList.remove(secret);
    }

    public boolean hasSecret() {
        return secretList.size() > 0;
    }

    public boolean hasSecret(Secret secret) {
        for (Secret secret1 : secretList) {
            if (secret1.getClass() == secret.getClass()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是友方单位
     *
     * @param minion 随从或英雄
     * @return 是友方返回true
     */
    public boolean isFriend(Minion minion) {
        Hero hero = getHero();
        return minions.contains(minion) || hero == minion;
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 添加一个随从
     */
    public void addMinion(Minion minion) {
        //添加随从前注册特效监听
        if (minion.getMinionListener() != null) {
            eventManager.registerListener(minion.getMinionListener());
        }
        minion.setOwner(this);
        minions.add(minion);

        BattlefieldChangeEvent battlefieldChangeEvent = new BattlefieldChangeEvent(this);
        eventManager.call(battlefieldChangeEvent);
        //随从进入战场
        AddMinionEvent addMinionEvent = new AddMinionEvent(minion);
        eventManager.call(addMinionEvent);
        printPublicQueue(minion.getMinionName() + "进入战场");
        checkMinion();
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 移除一个随从
     */
    public void removeMinion(Minion minion) {
        //移除随从前注销特效监听
        if (minion.getMinionListener() != null) {
            eventManager.removeListener(minion.getMinionListener());
        }
        BattlefieldChangeEvent battlefieldChangeEvent = new BattlefieldChangeEvent(this);
        minions.remove(minion);
        eventManager.call(battlefieldChangeEvent);
    }

    /**
     * 随从死亡
     *
     * @author : Eiden J.P Zhou
     * @date : 2018/9/14
     */
    public void deathMinion(int index) {
        Minion minion = minions.get(index);
        printPublicQueue(minion.getMinionName() + "死亡");
        Event minionDeathEvent = new MinionDeathEvent(this, minion);
        eventManager.call(minionDeathEvent);
        //移除随从
        this.removeMinion(minion);
        //随从进入墓地
        tomb.add(minion);
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
        printPublicQueue("游戏胜利---结束");
        throw new GameOverException();
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
        String handInfoStr = getHandInfoStr();
        printPrivateQueue(handInfoStr);

        String minionState = getMinionState();
        printPrivateQueue(minionState);
    }

    private String getHandInfoStr() {
        StringBuilder handInfo = new StringBuilder("玩家当前手牌:\n");
        List<Card> cards = getHand().getCards();
        for (Card card : cards) {
            handInfo.append(card.getCardName());
            handInfo.append("(").append(card.getCost()).append(")");
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
        return handInfo.toString();
    }

    public String getMinionState() {
        StringBuilder stringBuilder = new StringBuilder("场上随从:");
        for (Minion minionObject : minions) {
            stringBuilder.append(minionObject.getMinionName());
            stringBuilder.append(" ");
            stringBuilder.append(minionObject.getAttackValue());
            stringBuilder.append("/");
            stringBuilder.append(minionObject.getHealth());
        }
        return stringBuilder.toString();
    }

    /**
     * @date : 2018/9/13
     * 初始化
     */
    public void init(Hero heroObject, List<Card> cards) {
        this.hero = heroObject;
        this.hero.setOwner(this);
        this.hand = new Hand();
        this.deckCards = initRandomCards(cards);
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
            printPrivateQueue("你没有足够的法力值!");
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
     *
     * @param addAttack 攻击力
     */
    public void addMinionsAttack(long addAttack) {
        minions.forEach(minionObject -> minionObject.addAttack(addAttack));
    }

    /**
     * 友方全部随从增加生命值
     *
     * @param addHealth 生命值
     */
    public void addMinionsHealth(long addHealth) {
        minions.forEach(minionObject -> minionObject.addHealthLimit(addHealth));
    }

    /**
     * @param addAttack 攻击力
     * @param addHealth 生命值
     *                  友方随从获得 +[addAttack]/+[addHealth]
     */
    public void buffYourAllMinions(long addAttack, long addHealth) {
        addMinionsAttack(addAttack);
        addMinionsHealth(addHealth);
    }

    /**
     * 查找全部能够攻击的随从
     */
    public List<Minion> findAllCanAttackMinionsId() {
        List<Minion> resultList = new ArrayList<>();
        if (hero.isAttack()) {
            resultList.add(hero);
        }
        for (Minion minion : minions) {
            if (minion.isAttack()) {
                resultList.add(minion);
            }
        }
        return resultList;
    }

    /**
     * 打印手牌信息
     */
    public void printHandsInfo() {
        StringBuilder handInfo = new StringBuilder("选择需要使用的手牌:\n");
        handInfo.append("(可用的法力水晶：").append(manaCrystal.getAvailable()).append(")\n");
        List<Card> cards = getHand().getCards();
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            handInfo.append("[").append(i).append("]");
            handInfo.append(card.getCardName());
            handInfo.append("(cost:").append(card.getCost());
            if (card instanceof AbstractMinionCard){
                handInfo.append(" atk:").append(((AbstractMinionCard) card).getAtk());
                handInfo.append(" hp:").append(((AbstractMinionCard) card).getHealth());
            }
            handInfo.append(" ").append(RegexUtil.removeHtmlTag(card.getDescription()));
            handInfo.append(")");
            handInfo.append("\n");
        }
        printPrivateQueue(handInfo.toString());
    }

    /**
     * 获取卡牌的合法目标
     *
     * @param card 卡牌
     * @return 合法目标集合
     */
    public List<Minion> getLegitimateTarget(final Card card) {
        List<Minion> allTarget = getAllTarget();
        allTarget.removeIf(target -> !isRightTarget(card, target));
        return allTarget;
    }

    public void printMinion(List<Minion> minionList, String title) {
        printPrivateQueue(getMinionInfo(minionList, title));
    }

    private String getMinionInfo(List<Minion> minionList, String title) {
        StringBuilder sb = new StringBuilder(title);
        for (int i = 0; i < minionList.size(); i++) {
            sb.append("\n");
            sb.append("[").append(i).append("]");
            sb.append(minionList.get(i).getMinionName());
            sb.append(" atk:").append(minionList.get(i).getAttackValue());
            sb.append(" hp:").append(minionList.get(i).getHealth());
        }
        return sb.toString();
    }


    public List<Minion> getAllTarget() {
        List<Minion> allTarget = new ArrayList<>();
        allTarget.add(getHero());
        allTarget.addAll(getMinions());
        allTarget.add(getEnemy().getHero());
        allTarget.addAll(getEnemy().getMinions());
        return allTarget;
    }


    /**
     * @return : java.util.List<java.lang.Integer>
     * 查找全部能够被攻击的随从(敌方的)
     * @author : Eiden J.P Zhou
     * @date : 2018/9/21 17:12
     */
    public List<Minion> findAllCanBeAttackMinionsId() {
        List<Minion> resultList = new ArrayList<>();
        List<Minion> enemyMinions = enemy.getMinions();
        boolean hasTaunt = false;
        for (Minion minion : enemyMinions) {
            if (minion.isTaunt() && !minion.isStealth()) {
                resultList.add(minion);
                hasTaunt = true;
            }
        }
        //无则添加全部
        if (!hasTaunt) {
            resultList.add(enemy.getHero());
            resultList.addAll(enemyMinions);
        }
        return resultList;
    }

    /**
     * 添加规则
     *
     * @param rule 具体规则
     */
    public void addRule(Rule rule) {
        rules.add(rule);
        this.refreshRuleEffect();
    }


    /**
     * 获取规则
     *
     * @return 全部规则
     */
    public List<Rule> getRules() {
        return rules;
    }

    /**
     * 刷新规则
     */
    public void refreshRuleEffect() {
        rules.forEach(e -> e.effective(this));
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

    public boolean isActive() {
        return active;
    }

    /**
     * 将一张牌吸入牌库，范围是第一张到最后一张
     *
     * @param card 被洗牌
     */
    public void shuffleCard(Card card) {
        int index = randomSeed.nextInt(deckCards.size() + 1);
        deckCards.add(index, card);
    }

    public void printPrivateQueue(String msg){
        OutputInfo.info(privateMessageQueue,msg);
    }

    public void printPublicQueue(String msg){
        printPrivateQueue(msg);
        OutputInfo.info(msg);
    }

    public Gamer(Hero heroObject, List<Card> deckCards) {
        init(heroObject, deckCards);
        this.hero.setOwner(this);
    }

    public static Gamer createGamer(Deck deck) {
        int heroDbfId = deck.getHeroDbfId();
        AbstractHeroCard heroCard = (AbstractHeroCard) CardFactory.getCardById(heroDbfId);
        assert heroCard != null;
        Hero hero = heroCard.createHero();
        return new Gamer(hero, deck.getDeck());
    }

}
