package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.output.OutputInfo;
import cn.eiden.hsm.util.RegexUtil;

/**
 * 一版使用命令
 *
 * @author Eiden J.P Zhou
 * @date 2020/5/9 11:39
 */
public class PlayOrder extends AbstractOrder implements Order {
    private Gamer gamer;

    @Override
    public void execute() {
        Integer cardNum = getCardNum();
        Card card = gamer.getHand().getCard(cardNum);
        if (gamer.checkUse(card)){
            if (card.isNoneTarget()){
                gamer.useThisMinionCard(cardNum,null);
            }else {
                // TODO: 2020/5/12
                //带目标
            }
        }
    }

    public Integer getCardNum() {
        while (true) {
            //打印提示信息
            gamer.printHandsInfo();
            //等待输入信息
            String input = getOrder();
            if (RegexUtil.isNumberStr(input)) {
                int index = Integer.parseInt(input);
                if (index < gamer.getHand().getCards().size() && index >= 0) {
                    return index;
                }
            } else {
                return null;
            }
            OutputInfo.info("非法输入！");
        }
    }

    public PlayOrder(Gamer gamer) {
        this.gamer = gamer;
    }
}
