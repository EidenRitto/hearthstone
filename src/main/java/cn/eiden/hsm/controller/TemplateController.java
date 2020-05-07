package cn.eiden.hsm.controller;

import cn.eiden.hsm.controller.order.Invoker;
import cn.eiden.hsm.controller.order.OrderType;
import cn.eiden.hsm.output.HearthLinkContext;
import cn.eiden.hsm.output.OutputInfo;
import cn.eiden.hsm.util.EnumUtils;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/30 8:53
 */
public class TemplateController {

    private Invoker orderInvoker;

    private void template(){
        boolean endTurnFlag = false;
        while (!endTurnFlag){
            redirectOrder();
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
                orderInvoker.callEnd();
                break;
            case PLAY:
                orderInvoker.callPlay();
                break;
            case SPELL:
                orderInvoker.callSpell();
                break;
            case ATK:
                orderInvoker.callAtk();
                break;
            case HELP:
                orderInvoker.callHelp();
                break;
            case INVALID:
            default:
        }
    }
}
