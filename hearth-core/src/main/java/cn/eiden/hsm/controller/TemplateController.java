package cn.eiden.hsm.controller;

import cn.eiden.hsm.controller.order.Invoker;
import cn.eiden.hsm.controller.order.OrderType;
import cn.eiden.hsm.output.HearthLinkContext;
import cn.eiden.hsm.output.OutputInfo;
import cn.eiden.hsm.util.EnumUtils;

import java.util.Objects;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/30 8:53
 */
public class TemplateController {

    private Invoker gamerInvoker;
    private Invoker enemyInvoker;
    private Invoker nowInvoker;
    private boolean finishFlag;

    public TemplateController(Invoker gamerInvoker, Invoker enemyInvoker) {
        this.gamerInvoker = gamerInvoker;
        this.enemyInvoker = enemyInvoker;
        this.finishFlag = false;
    }

    public void gameStart(boolean b) {
        nowInvoker = b ? gamerInvoker : enemyInvoker;
        while (!finishFlag) {
            runTurn();
        }
    }

    /**
     * 切换当前执行者
     */
    private void reverseInvoker() {
        nowInvoker = nowInvoker == gamerInvoker ? enemyInvoker : gamerInvoker;
    }

    /**
     * 开始一个新回合
     */
    private void runTurn() {
        if (nowInvoker == enemyInvoker){
            OutputInfo.info("测试模式下跳过对手回合");
            this.reverseInvoker();
            return;
        }
        nowInvoker.callStartTurn();
        boolean endTurnFlag = false;
        while (!endTurnFlag) {
            nowInvoker.callHelp();
            final String poll = waitOrder();
            OrderType orderType = Objects.requireNonNull(EnumUtils.getEnumObject(
                    OrderType.class,
                    e -> e.getCode().equals(poll)))
                    .orElse(OrderType.INVALID);
            redirectOrder(orderType);
            if (orderType == OrderType.END) {
                endTurnFlag = true;
                this.reverseInvoker();
            }
        }
    }

    /**
     * 等待命令
     */
    private String waitOrder() {
        String order = "";
        try {
            order = HearthLinkContext.inputMessage.take();
        } catch (InterruptedException e) {
            OutputInfo.info(e.getMessage());
        }
        return order;
    }

    /**
     * 命令分派
     */
    void redirectOrder(OrderType orderType) {

        switch (orderType) {
            case END:
                break;
            case PLAY:
                nowInvoker.callPlay();
                break;
            case HERO_POWER:
                nowInvoker.callSpell();
                break;
            case ATK:
                nowInvoker.callAtk();
                break;
            case HELP:
                nowInvoker.callHelp();
                break;
            case INVALID:
            default:
        }
    }
}
