package cn.eiden.hsm.game.card.sch.demonhunter;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.scholomance.demonhunter.Glide;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 滑翔
 * 将你的手牌洗入你的牌库，抽四张牌。<b>流放：</b>使你的对手做出相同行为。
 * @author Eiden J.P Zhou
 * @date 2020/8/7 11:52
 */
public class GlideCard extends Glide {
    @Override
    public void baseEffect(Gamer gamer, Minion target) {
        //将手牌洗入牌库
        gamer.shuffleHandToDeck();
        //抽4张牌
        gamer.drawCard(4);
    }

    @Override
    public void outcastEffect(Gamer gamer, Minion target) {
        //执行基础效果
        baseEffect(gamer, target);
        //使你的对手做出相同行为。
        baseEffect(gamer.getEnemy(), target);
    }
}
