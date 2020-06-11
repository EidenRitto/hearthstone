package cn.eiden.hsm.game.card;


import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.Gamer;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *
 * */
public abstract class AbstractMagicCard extends AbstractCard {
    /**是否被反制*/
    boolean isCounter = false;
    /**是否是双生法术*/
    boolean isTwinSpell = false;

    public abstract void magicEffect(Gamer gamer, Minion target);

    public AbstractMagicCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, CardClass cardClass, CardType cardType, Rarity rarity, int overload) {
        super(cardName, cost, description, id, cardId, cardSet, cardClass, cardType, rarity, overload);
    }

    public AbstractMagicCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, CardClass cardClass, CardType cardType, Rarity rarity, int overload, boolean isTwinSpell) {
        super(cardName, cost, description, id, cardId, cardSet, cardClass, cardType, rarity, overload);
        this.isTwinSpell = isTwinSpell;
    }

    public boolean isCounter() {
        return isCounter;
    }

    public void setCounter(boolean counter) {
        isCounter = counter;
    }
}
