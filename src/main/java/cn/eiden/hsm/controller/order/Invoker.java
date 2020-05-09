package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import lombok.Setter;

/**
 * 命令模式-执行者
 * @author Eiden J.P Zhou
 * @date 2020/4/23 17:21
 */
@Setter
public class Invoker {
    private Order helpOrder;
    private Order endOrder;
    private Order atkOrder;
    private Order playOrder;
    private Order spellOrder;
    private Order startTurnOrder;
    private Gamer gamer;

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
        spellOrder.execute();
    }
    public void callStartTurn(){
        startTurnOrder.execute();
    }

    public Invoker(Gamer gamer) {
        helpOrder = new HelpOrder();
        atkOrder = new AtkOrder(gamer);
        startTurnOrder = new StartTurnOrder(gamer);
        playOrder = new PlayOrder(gamer);
    }
}
