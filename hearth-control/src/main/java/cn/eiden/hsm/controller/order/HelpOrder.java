package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.output.OutputInfo;

/**
 * 帮助
 *
 * @author Eiden J.P Zhou
 * @date 2020/5/9 14:28
 */
public class HelpOrder extends AbstractOrder implements Order {

    @Override
    public void execute() {
        StringBuilder useInfo = new StringBuilder();
        useInfo.append("指令说明(指令直接输入数字)：\n");
        for (OrderType orderType : OrderType.values()) {
            if (orderType == OrderType.INVALID){
                continue;
            }
            useInfo.append("[").append(orderType.getCode()).append("]")
                    .append(orderType.getExplanation()).append("\n");
        }
        useInfo.delete(useInfo.length() - 1, useInfo.length());
        OutputInfo.info(gamer.getPrivateMessageQueue(),useInfo.toString());
    }

    public HelpOrder(Gamer gamer) {
        super(gamer);
    }
}
