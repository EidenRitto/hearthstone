package cn.eiden.hsm.game.card.uldum.neutral;

import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.uldum.neutral.GoldenScarab;
import cn.eiden.hsm.game.keyword.Battle;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/9 16:57
 */
public class GoldenScarabCard extends GoldenScarab {
    /**
     * "<b>战吼：发现</b>一张\n"
     *         + "法力值消耗为（4）的卡牌。"
     */
    @Override
    protected Battle selfBattleCry() {
        return (s, t) -> s.getOwner().discoverChooseOne(() -> CardFactory.buildCard().cost(4).discover());
    }
}
