package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.output.OutputInfo;
import cn.eiden.hsm.util.RegexUtil;

import java.util.List;

/**
 * 一版使用命令
 *
 * @author Eiden J.P Zhou
 * @date 2020/5/9 11:39
 */
public class PlayOrder extends AbstractOrder implements Order {

    @Override
    public void execute() {
        Integer cardNum = getCardNum();
        if (cardNum == null){
            return;
        }
        Card originCard = gamer.getHand().getCard(cardNum);
        Card card = originCard;
        if (gamer.checkUse(card)){
            if (card.hasChooseOne()){
                List<Card> options = card.getOptions();
                printOptions(options);
                Integer optionNum = getOptionNum(options);
                if (optionNum == null){
                    return;
                }
                card = options.get(optionNum);
            }
            if (card.isNoneTarget()){
                gamer.useThisCard(card,null);
            }else {
                List<Minion> legitimateTarget = gamer.getLegitimateTarget(card);
                Integer targetNum = getTargetNum(legitimateTarget,gamer);
                Minion targetMinion = legitimateTarget.get(targetNum);
                gamer.useThisCard(card,targetMinion);
            }
            //从手牌中移除卡牌
            gamer.getHand().loss(originCard);
        }
    }

    /**
     * 打印选项
     */
    public void printOptions(List<Card> options) {
        StringBuilder handInfo = new StringBuilder("选择以下选项:\n");
        for (int i = 0; i < options.size(); i++) {
            Card card = options.get(i);
            handInfo.append("[").append(i).append("]");
            handInfo.append(card.getCardName());
            handInfo.append(" ").append(RegexUtil.removeHtmlTag(card.getDescription()));
            handInfo.append(")");
            handInfo.append("\n");
        }
        gamer.printPrivateQueue(handInfo.toString());
    }

    public Integer getOptionNum(List<Card> options) {
        while (true) {
            //等待输入信息
            String input = getOrder();
            if (RegexUtil.isNumberStr(input)) {
                int index = Integer.parseInt(input);
                if (index < options.size() && index >= 0) {
                    return index;
                }
            } else {
                return null;
            }
            OutputInfo.info(gamer.getPrivateMessageQueue(),"非法输入！");
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
            OutputInfo.info(gamer.getPrivateMessageQueue(),"非法输入！");
        }
    }

    public PlayOrder(Gamer gamer) {
        super(gamer);
    }
}
