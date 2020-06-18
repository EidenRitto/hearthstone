package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.output.HearthLinkContext;
import cn.eiden.hsm.output.OutputInfo;
import cn.eiden.hsm.util.RegexUtil;

import java.util.List;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/7 16:20
 */
public abstract class AbstractOrder implements Order{
    protected Gamer gamer;

    protected String getOrder() {
        String order = "";
        try {
            if (gamer.getInputMessageQueue() != null){
                order = gamer.getInputMessageQueue().take();
            }else {
                order = HearthLinkContext.inputMessage.take();
            }
        } catch (InterruptedException e) {
            gamer.printPublicQueue(e.getMessage());
        }
        return order;
    }

    public Integer getTargetNum(List<Minion> legitimateTarget, Gamer gamer){
        while (true){
            //打印提示信息
            gamer.printMinion(legitimateTarget,"选择合法的目标：");
            //等待输入信息
            String input = getOrder();
            if (RegexUtil.isNumberStr(input)) {
                int index = Integer.parseInt(input);
                if (index < legitimateTarget.size() && index >= 0) {
                    return index;
                }
            } else {
                return null;
            }
            gamer.printPrivateQueue("非法输入！");
        }
    }

    public AbstractOrder(Gamer gamer) {
        this.gamer = gamer;
    }
}
