package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.BlockingQueue;

/**
 * 命令模式-执行者
 * @author Eiden J.P Zhou
 * @date 2020/4/23 17:21
 */
@Setter
public class Invoker {
    @Getter
    private Gamer gamer;
    private HelpOrder helpOrder;
    private EndTurnOrder endOrder;
    private AtkOrder atkOrder;
    private PlayOrder playOrder;
    private HeroPowerOrder heroPowerOrder;
    private StartTurnOrder startTurnOrder;
    private ShowOrder showOrder;

    public void callHelp(){
        helpOrder.execute();
    }
    public void callEnd(){
        endOrder.execute();
    }
    public void callAtk(){
        atkOrder.execute();
    }
    public void callPlay(){
        playOrder.execute();
    }
    public void callSpell(){
        heroPowerOrder.execute();
    }
    public void callStartTurn(){
        startTurnOrder.execute();
    }
    public void callShow(){
        showOrder.execute();
    }

    public Invoker(Gamer gamer) {
        this.gamer = gamer;
        helpOrder = new HelpOrder(gamer);
        atkOrder = new AtkOrder(gamer);
        startTurnOrder = new StartTurnOrder(gamer);
        playOrder = new PlayOrder(gamer);
        heroPowerOrder = new HeroPowerOrder(gamer);
        showOrder = new ShowOrder(gamer);
        endOrder = new EndTurnOrder(gamer);
    }
}
