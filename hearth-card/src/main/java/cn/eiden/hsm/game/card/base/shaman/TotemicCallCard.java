package cn.eiden.hsm.game.card.base.shaman;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.core.shaman.TotemicCall;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.util.EnumUtils;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/26 16:35
 */
public class TotemicCallCard extends TotemicCall {
    @Override
    public void powerEffect(Gamer gamer, Minion target) {
        TotemEnum totemEnum = EnumUtils.randomEnum(TotemEnum.class);
        AbstractMinionCard totemCard = (AbstractMinionCard) CardFactory.getCardById(totemEnum.getCardId());
        assert totemCard != null;
        gamer.addMinion(totemCard.createMinion());
    }

    enum TotemEnum{
        WrathOfAirTotem(458),
        StoneclawTotem(850),
        SearingTotem(537),
        HealingTotem(764)
        ;
        private int cardId;

        TotemEnum(int cardId) {
            this.cardId = cardId;
        }

        public int getCardId() {
            return cardId;
        }
    }

}
