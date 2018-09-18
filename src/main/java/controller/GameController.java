package controller;


import game.Gamer;
import game.card.ServantCard;

import java.util.Scanner;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @version : 1.0
 * @description :
 * */
public class GameController {
    private static boolean endTurn = false;
    private static boolean endGame = false;
    private static boolean isYours = false;
    public static void gameStart(Gamer gamer,Gamer enemy){
        isYours = gamer.newGameStart();
        while (!endGame){
            if(isYours){
                System.out.println("=========你的回合=========");
                //开始一个新回合
                gamer.newTurnStart();
                gamer.getState();
            }else {
                System.out.println("=========对手回合=========");
                enemy.newTurnStart();
                enemy.getState();
            }
            endTurn = false;
            while (!endTurn){
                //输入指令
                System.out.println("请输入指令：");
                Scanner scan = new Scanner(System.in);
                String order = scan.nextLine();
                switch (order){
                    case "攻击":
                        System.out.println("请输入需要攻击的随从编号");
                        int servantId = scan.nextInt();
                        System.out.println("请选择目标");
                        int beAttackServantId = scan.nextInt();
                        if (isYours){
                            gamer.getServant(servantId).attack(gamer.getEnemy().getServant(beAttackServantId));
                        }else {
                            enemy.getServant(servantId).attack(enemy.getEnemy().getServant(beAttackServantId));
                        }
                        gamer.checkServant();
                        enemy.checkServant();
                        break;
                    case "打出":
                        System.out.println("请输入需要打出的手牌编号");
                        int cardId = scan.nextInt();
                        if (isYours){
                            if (gamer.checkUse(cardId)){
                                if (gamer.getHandsCards().get(cardId) instanceof ServantCard){
                                    gamer.useThisServantCard(cardId,null);
                                }else {
                                    gamer.useThisMagicCard(cardId,null);
                                }
                                gamer.getState();
                            }
                        }else {
                            if (enemy.checkUse(cardId)){
                                if (enemy.getHandsCards().get(cardId) instanceof ServantCard){
                                    enemy.useThisServantCard(cardId,null);
                                }else {
                                    enemy.useThisMagicCard(cardId,null);
                                }
                                enemy.getState();
                            }
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