package cn.eiden.hsm.controller;


import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.output.HearthLinkContext;
import cn.eiden.hsm.output.OutputInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @since 1.0
 * @version : 1.1
 * */
@Slf4j
public class GameController {
    private static final String TAKE = "使用";
    private static final String ATTACK = "攻击";
    private static final String END = "回合结束";

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

    public void gameStart(){

        isYours = gamer.newGameStart();
        while (!endGame){
            if(isYours){
                OutputInfo.info("=========你的回合=========");
                //开始一个新回合
                gamer.newTurnStart();
                gamer.getState();
                nowGamer=gamer;
            }else {
                OutputInfo.info("=========对手回合=========");
                enemy.newTurnStart();
                enemy.getState();
                nowGamer=enemy;
            }
            endTurn = false;
            while (!endTurn){
                //输入指令
                OutputInfo.info("请输入指令：");
                String order = getOrder();
                this.redirectOrder(order);
            }
        }
    }





    private String getOrder(){
        String order = "";
        try {
            order = HearthLinkContext.inputMessage.take();
        } catch (InterruptedException e) {
            OutputInfo.info(e.getMessage());
        }
        return order;
    }

    private Card chooseCard(final String name){
        List<Card> cards = nowGamer.getHand().getCards();
        return cards.stream().filter(e -> name.equals(e.getCardName())).findAny().orElse(null);
    }

    private Minion chooseMinion(final String name,Gamer gamer){
        List<Minion> minions = gamer.getMinions();
        return minions.stream().filter(e->name.equals(e.getMinionName())).findAny().orElse(null);
    }

    /**
     * 派发请求
     * @param order 命令请求
     */
    private void redirectOrder(String order){
        if (order.contains(TAKE)){
            int i = order.indexOf(TAKE);
            String minString = order.substring(i+TAKE.length()).trim();
            this.useOrder(minString);
        }else if (order.contains(ATTACK)){
            String[] split = order.split(ATTACK);
            assert split.length == 2;
            this.attackOrder(split[0].trim(),split[1].trim());
        }else if (order.contains(END)){
            this.endOrder();
        }else {
            //输出提示信息
            this.tips();
        }
    }

    private void useOrder(String cardName){
        Card card = chooseCard(cardName);
        if (card == null){
            OutputInfo.info("无效指令");
            return;
        }
        if (nowGamer.checkUse(card)){
            if (card instanceof AbstractMinionCard){
                nowGamer.useThisMinionCard(card,null);
            }else {
                nowGamer.useThisMagicCard(card,null);
            }
            nowGamer.getState();
        }
    }

    private void attackOrder(String minionName,String enemyName){
        Minion minion = chooseMinion(minionName,nowGamer);
        Minion enemy = chooseMinion(enemyName,nowGamer.getEnemy());
        minion.attack(enemy);
        nowGamer.checkMinion();
        nowGamer.getEnemy().checkMinion();
    }

    private void endOrder(){
        isYours = !isYours;
        endTurn = true;
    }

    private void tips(){
        StringBuilder useInfo = new StringBuilder();
        useInfo.append("操作说明：");
        useInfo.append("\n");
        useInfo.append("出牌,输入[使用]+卡牌名称");
        useInfo.append("\n");
        useInfo.append("随从攻击,输入随从名称+[攻击]+随从名称");
        useInfo.append("\n");
        useInfo.append("回合结束：输入[回合结束]");
        OutputInfo.info(useInfo.toString());
    }

    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.redirectOrder("我使用 大");
    }

}