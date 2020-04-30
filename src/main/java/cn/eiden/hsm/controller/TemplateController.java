package cn.eiden.hsm.controller;

import cn.eiden.hsm.controller.order.Invoker;
import cn.eiden.hsm.controller.order.OrderType;
import cn.eiden.hsm.output.HearthLinkContext;
import cn.eiden.hsm.output.OutputInfo;
import cn.eiden.hsm.util.EnumUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/30 8:53
 */
public abstract class TemplateController {

    private Invoker orderInvoker;

    Queue<String> orderQueue = new LinkedList<>();

    private void template(){
        boolean endTurnFlag = false;
        while (!endTurnFlag){
            redirectOrder();
            buildOrder();
            executeOrder();
        }
    }

    /**等待命令*/
    String waitOrder(){
        String order = "";
        try {
            order = HearthLinkContext.inputMessage.take();
        } catch (InterruptedException e) {
            OutputInfo.info(e.getMessage());
        }
        return order;
    }
    /**命令分派*/
    void redirectOrder(){
        final String poll = waitOrder();
        OrderType orderType = EnumUtils.getEnumObject(
                OrderType.class,
                e -> e.getCode().equals(poll))
                .orElse(OrderType.INVALID);
        switch (orderType){
            case END:
                break;
            case PLAY:
                break;
            case SPELL:
                break;
            case ATK:
                break;
            case HELP:
                break;
            case INVALID:
            default:
        }
    }
    /**建造命令*/
    abstract void buildOrder();
    /**执行命令*/
    abstract void executeOrder();
}
