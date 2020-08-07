package cn.eiden.hsm.game.keyword;

import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 法术迸发
 * @author Eiden J.P Zhou
 * @date 2020/8/6 9:13
 */
@FunctionalInterface
public interface SpellBurst {
    /**
     * 迸发
     * @param self 当前随从
     * @param magicCard 触发法术
     */
    void burst(Minion self, AbstractMagicCard magicCard);
}
