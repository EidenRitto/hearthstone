package cn.eiden.hsm.game.card;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Secret;

/**
 * 奥秘抽象
 * @author Eiden J.P Zhou
 * @date 2020/5/28 10:21
 */
public abstract class AbstractSecretCard extends AbstractMagicCard {
    public AbstractSecretCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, CardClass cardClass, CardType cardType, Rarity rarity) {
        super(cardName, cost, description, id, cardId, cardSet, cardClass, cardType, rarity);
    }

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.addSecret(createSecret());
    }

    /**
     * 创建卡牌对应的奥秘
     * @return 卡牌对应的奥秘
     * */
    public abstract Secret createSecret();
}
