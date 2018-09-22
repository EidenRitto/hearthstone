package game.card.base.hunter;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;
import game.objct.MinionObject;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/22
 * @Description: 追踪术
 * @Modified By:
 */
public class Tracking extends MagicCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "从排队顶部检索3张卡牌，选择一张抽取，丢弃其他2张。";
    private static final String CARD_NAME = "追踪术";
    /**检索牌堆数量*/
    private static final int CHECK_NUMBER = 3;

    public Tracking() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target){
        System.out.println("追踪术:");
        for (int i=0;i<CHECK_NUMBER;i++){
            System.out.println(gamer.getLastCards().getCardName());
        }
        if (gamer.getChooseOne()!=-1){
            if (gamer.getChooseOne()==0){
                gamer.drawCard(1);
                gamer.lossLastCards();
                gamer.lossLastCards();
            }else if (gamer.getChooseOne()==1){
                gamer.lossLastCards();
                gamer.drawCard(1);
                gamer.lossLastCards();
            }else {
                gamer.lossLastCards();
                gamer.lossLastCards();
                gamer.drawCard(1);
            }
        }
    }
}
