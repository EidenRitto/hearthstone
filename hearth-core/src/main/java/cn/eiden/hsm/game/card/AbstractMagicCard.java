package cn.eiden.hsm.game.card;


import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.keyword.SpellEffect;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.Gamer;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *
 * */
public abstract class AbstractMagicCard extends AbstractCard {
    private SpellEffect spellEffect;

    public void magicEffect(Gamer gamer, Minion target){
        spellEffect.magicEffect(gamer,target);
    }

    public AbstractMagicCard(int cost, String description, String cardName, CardClass cardClass) {
        super(cost, description, cardName ,cardClass);
    }

    public AbstractMagicCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, cn.eiden.hsm.enums.CardClass cardClass, CardType cardType, Rarity rarity) {
        super(cardName, cost, description, id, cardId, cardSet, cardClass, cardType, rarity);
    }

    public SpellEffect getSpellEffect() {
        return spellEffect;
    }

    public void setSpellEffect(SpellEffect spellEffect) {
        this.spellEffect = spellEffect;
    }
}
