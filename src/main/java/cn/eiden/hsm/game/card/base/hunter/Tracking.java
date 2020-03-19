package cn.eiden.hsm.game.card.base.hunter;

import cn.eiden.hsm.game.hero.Profession;
import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  追踪术
 *
 */
@Slf4j
public class Tracking extends AbstractMagicCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "从排队顶部检索3张卡牌，选择一张抽取，丢弃其他2张。";
    private static final String CARD_NAME = "追踪术";
    /**检索牌堆数量*/
    private static final int CHECK_NUMBER = 3;

    public Tracking() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Hunter);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target){
        log.info("追踪术:");
        for (int i=0;i<CHECK_NUMBER;i++){
            log.info(gamer.getLastCards().getCardName());
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
