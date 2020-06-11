package cn.eiden.hsm.game.card.blacktemple.demonhunter;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.defs.black_temple.demonhunter.SkullOfGulDan;
import cn.eiden.hsm.game.minion.Minion;

import java.util.List;

/**
 * 古尔丹之颅
 * @author Eiden J.P Zhou
 * @date 2020/6/11 14:33
 */
public class SkullOfGulDanCard extends SkullOfGulDan {
    @Override
    public void baseEffect(Gamer gamer, Minion target) {
        gamer.drawCard(3);
    }

    @Override
    public void outcastEffect(Gamer gamer, Minion target) {
        List<Card> cards = gamer.drawCard(3);
        cards.forEach(e->e.reduceCost(3));
    }
}
