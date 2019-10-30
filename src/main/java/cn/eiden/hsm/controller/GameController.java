package cn.eiden.hsm.controller;


import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.MinionCard;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @version : 1.0
 * */
@Slf4j
public class GameController {
    private static boolean endTurn = false;
    private static boolean endGame = false;
    private static boolean isYours = false;
    public static void gameStart(Gamer gamer, Gamer enemy){
        Gamer nowGamer;
        isYours = gamer.newGameStart();
        while (!endGame){
            if(isYours){
                log.info("=========你的回合=========");
                //开始一个新回合
                gamer.newTurnStart();
                gamer.getState();
                nowGamer=gamer;
            }else {
                log.info("=========对手回合=========");
                enemy.newTurnStart();
                enemy.getState();
                nowGamer=enemy;
            }
            endTurn = false;
            while (!endTurn){
                //输入指令
                log.info("请输入指令：");
                Scanner scan = new Scanner(System.in);
                String order = scan.nextLine();
                switch (order){
                    case "攻击":
                        log.info("请输入需要攻击的随从编号");
                        int minionId = scan.nextInt();
                        log.info("请选择目标");
                        int beAttackMinionId = scan.nextInt();

                        nowGamer.getMinion(minionId).attack(nowGamer.getEnemy().getMinion(beAttackMinionId));

                        gamer.checkMinion();
                        enemy.checkMinion();
                        break;
                    case "打出":
                        log.info("请输入需要打出的手牌编号");
                        int cardId = scan.nextInt();
                        if (nowGamer.checkUse(cardId)){
                            if (nowGamer.getHandsCards().get(cardId) instanceof MinionCard){
                                nowGamer.useThisMinionCard(cardId,null);
                            }else {
                                nowGamer.useThisMagicCard(cardId,null);
                            }
                            nowGamer.getState();
                        }
                        break;
                    case "回合结束":
                        isYours = !isYours;
                        endTurn = true;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}