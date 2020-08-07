package cn.eiden.hsm.game.card.sch.rogue;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.scholomance.rogue.SecretPassage;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 秘密通道
 * @author Eiden J.P Zhou
 * @date 2020/8/7 11:41
 */
public class SecretPassageCard extends SecretPassage {
    /**
     * "将你的手牌替换为你牌库中的5张牌。下回合换回。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        // TODO: 2020/8/7 机制不太明确
    }
}
