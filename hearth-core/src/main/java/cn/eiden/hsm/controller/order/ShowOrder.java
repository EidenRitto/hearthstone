package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.output.OutputInfo;
import cn.eiden.hsm.util.EnumUtils;
import cn.eiden.hsm.util.RegexUtil;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/26 14:24
 */
public class ShowOrder extends AbstractOrder{
    private Gamer gamer;

    @Override
    public void execute() {
        StringBuilder info = new StringBuilder();
        info.append("查看：\n");
        for (ShowType value : ShowType.values()) {
            info.append("[").append(value.getCode()).append("]")
                    .append(value.getExplanation()).append("\n");
        }
        info.delete(info.length() - 1, info.length());
        OutputInfo.info(info.toString());
        //等待输入信息
        String input = getOrder();
        ShowType showType = EnumUtils.getEnumObject(ShowType.class, e->e.getCode().equals(input)).orElse(null);
        if (showType == null){
            OutputInfo.info("非法输入！");
            return;
        }
        switch (showType){
            case CHECKERBOARD:
                showMinionDetail();
                break;
            case HERO:
                showHeroDetail();
                break;
            case HAND:
                gamer.printHandsInfo();
                //等待输入信息
                String input2 = getOrder();
                if (RegexUtil.isNumberStr(input2)) {
                    int index = Integer.parseInt(input);
                    if (index < gamer.getHand().getCards().size() && index >= 0) {
                        OutputInfo.info(gamer.getHand().getCard(index).getDescription());
                    }
                }
                break;
            case DECK:
                OutputInfo.info("牌库还有%s张牌",gamer.getCards().size());
                break;
            default:
        }
    }

    private void showHeroDetail() {
        StringBuilder builder = new StringBuilder();
        builder.append("敌方").append(gamer.getEnemy().getHero().getHeroInfo()).append("\n");
        builder.append("我方").append(gamer.getHero().getHeroInfo());
        OutputInfo.info(builder.toString());
    }

    private void showMinionDetail() {
        StringBuilder builder = new StringBuilder();
        builder.append("敌方").append(gamer.getEnemy().getMinionState()).append("\n");
        builder.append("我方").append(gamer.getMinionState());
        OutputInfo.info(builder.toString());
    }

    public ShowOrder(Gamer gamer) {
        this.gamer = gamer;
    }
}
