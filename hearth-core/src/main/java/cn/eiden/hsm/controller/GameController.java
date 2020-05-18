package cn.eiden.hsm.controller;


import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.output.HearthLinkContext;
import cn.eiden.hsm.output.OutputInfo;
import cn.eiden.hsm.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author : Eiden J.P Zhou
 * @version : 1.1
 * @date : 2018/9/13
 * @since 1.0
 */
@Slf4j
public class GameController {
    private static final String TAKE = "1";
    private static final String ATTACK = "2";
    private static final String END = "0";
    private static final String RETURN = "99";

    private static final String HELP = "帮助";
    private static final String HELP_2 = "help";
    private static final String HELP_3 = "h";

    private boolean endTurn;
    private boolean endGame;
    private boolean isYours;
    private Gamer gamer;
    private Gamer enemy;
    private Gamer nowGamer;

    public GameController() {
    }

    public GameController(Gamer gamer, Gamer enemy) {
        this.gamer = gamer;
        this.enemy = enemy;
        this.endTurn = false;
        this.endGame = false;
        this.isYours = false;
    }

    public void gameStart() {

        isYours = gamer.newGameStart();
        while (!endGame) {
            if (isYours) {
                OutputInfo.info("=========你的回合=========");
                //开始一个新回合
                gamer.newTurnStart();
                gamer.getState();
                nowGamer = gamer;
            } else {
                OutputInfo.info("=========对手回合=========");
                enemy.newTurnStart();
                enemy.getState();
                nowGamer = enemy;
            }
            endTurn = false;
            while (!endTurn) {
                //输入指令
                OutputInfo.info("请输入指令：");
                String order = getOrder();
                this.redirectOrder(order);
            }
        }
    }


    private String getOrder() {
        String order = "";
        try {
            order = HearthLinkContext.inputMessage.take();
        } catch (InterruptedException e) {
            OutputInfo.info(e.getMessage());
        }
        return order;
    }

    private Card chooseCard(final String name) {
        List<Card> cards = nowGamer.getHand().getCards();
        return cards.stream().filter(e -> name.equals(e.getCardName())).findAny().orElse(null);
    }

    private Minion chooseMinion(final String name, Gamer gamer) {
        List<Minion> minions = gamer.getMinions();
        return minions.stream().filter(e -> name.equals(e.getMinionName())).findAny().orElse(null);
    }

    /**
     * 派发请求
     *
     * @param order 命令请求
     */
    private void redirectOrder(String order) {
        if (order.contains(TAKE)) {
            //列出全部手牌
            StringBuilder handInfo = new StringBuilder("选择手牌:\n");
            List<Card> cards = nowGamer.getHand().getCards();
            for (int i = 0; i < cards.size(); i++) {
                handInfo.append("[").append(i).append("]");
                handInfo.append(cards.get(i).getCardName());
                handInfo.append("(").append(cards.get(i).getCost()).append(")\n");
            }
            handInfo.append("[").append(RETURN).append("]返回上一级");
            while (true){
                OutputInfo.info(handInfo.toString());
                //-----等待2级指令
                String order2 = getOrder();
                if (!RegexUtil.isNumberStr(order2)){
                    OutputInfo.info("非法输入");
                }else {
                    int index = Integer.parseInt(order2);
                    break;
                }
            }

//            if (s.length == 1) {
//                this.useOrder(s[0], null);
//            } else {
//                this.useOrder(s[0], s[1]);
//            }
        } else if (order.contains(ATTACK)) {
            //列出全部能够攻击的随从
//            this.attackOrder(split[0].trim(), split[1].trim());
        } else if (order.contains(END)) {
            this.endOrder();
        } else if (order.equals(HELP) || order.equals(HELP_2) || order.equals(HELP_3)){
            this.help();
        }else {
            //输出提示信息
            this.tips();
        }
    }

    private void useOrder(String cardName, String targetName) {
        Minion target = null;

        Card card = chooseCard(cardName);
        if (card == null) {
            OutputInfo.info("无效指令");
            return;
        }
        if (nowGamer.checkUse(card)) {
            if (card instanceof AbstractMinionCard) {
                nowGamer.useThisMinionCard(card, target);
            } else {
                nowGamer.useThisMagicCard(card, target);
            }
            nowGamer.getState();
        }
    }

    private void attackOrder(String minionName, String enemyName) {
        Minion minion = chooseMinion(minionName, nowGamer);
        Minion enemy = chooseMinion(enemyName, nowGamer.getEnemy());
        minion.attack(enemy);
        nowGamer.checkMinion();
        nowGamer.getEnemy().checkMinion();
    }

    private void faceOrder(String minionName) {
        Minion minion = chooseMinion(minionName, nowGamer);
        minion.attack(nowGamer.getEnemy().getHero());
        nowGamer.checkMinion();
        nowGamer.getEnemy().checkMinion();
    }

    private void endOrder() {
        isYours = !isYours;
        endTurn = true;
    }

    private void tips() {
        StringBuilder useInfo = new StringBuilder();
        useInfo.append("操作说明：");
        useInfo.append("\n");
        useInfo.append("出牌,输入[使用]+卡牌名称");
        useInfo.append("\n");
        useInfo.append("随从攻击,输入随从名称+[攻击]+随从名称;随从打脸，输入随从名称+[打脸]");
        useInfo.append("\n");
        useInfo.append("回合结束：输入[回合结束]");
        OutputInfo.info(useInfo.toString());
    }


    private void help() {
        StringBuilder useInfo = new StringBuilder();
        useInfo.append("指令说明(指令直接输入数字)：\n");
        useInfo.append("[0]回合结束\n");
        useInfo.append("[1]出牌\n");
        useInfo.append("[2]攻击\n");
        useInfo.append("[3]英雄技能");
        OutputInfo.info(useInfo.toString());
    }

}