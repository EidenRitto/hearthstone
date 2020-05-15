package cn.eiden.hsm.game.card;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 英雄技能抽象
 * @author Eiden J.P Zhou
 * @date 2020/5/15 17:38
 */
public abstract class AbstractHeroPowerCard extends AbstractCard{
    /**
     * 技能效果
     * @param gamer 当前玩家
     * @param target 目标单位
     */
    public abstract void powerEffect(Gamer gamer, Minion target);

    public AbstractHeroPowerCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, CardClass cardClass, CardType cardType, Rarity rarity) {
        super(cardName, cost, description, id, cardId, cardSet, cardClass, cardType, rarity);
    }
}
