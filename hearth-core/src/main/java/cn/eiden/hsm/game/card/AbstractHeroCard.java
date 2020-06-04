package cn.eiden.hsm.game.card;

import cn.eiden.hsm.enums.*;
import cn.eiden.hsm.game.minion.hero.Hero;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 17:42
 */
@Setter
@Getter
public abstract class AbstractHeroCard extends AbstractCard{
    /**生命值上限*/
    private Long health;
    /**种族*/
    private Race race;

    public AbstractHeroCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, CardClass cardClass, CardType cardType, Rarity rarity, Long health, Race race) {
        super(cardName, cost, description, id, cardId, cardSet, cardClass, cardType, rarity);
        this.health = health;
        this.race = race;
    }

    /**
     * 创建英雄
     * @return 英雄
     */
    public abstract Hero createHero();
}
