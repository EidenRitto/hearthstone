package cn.eiden.hsm.game.card;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.minion.Minion;
import lombok.Getter;
import lombok.Setter;

/**
 * 英雄技能抽象
 *
 * @author Eiden J.P Zhou
 * @date 2020/5/15 17:38
 */
public abstract class AbstractHeroPowerCard extends AbstractCard {
    private static final int TIMES = 1;
    @Setter
    @Getter
    /**使用次数*/
    private int timesOfUse;

    /**
     * 技能效果
     *
     * @param target 目标单位
     */
    public abstract void powerEffect(Minion target);

    public AbstractHeroPowerCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, CardClass cardClass, CardType cardType, Rarity rarity) {
        super(cardName, cost, description, id, cardId, cardSet, cardClass, cardType, rarity);
        this.recoveryTimes();
    }

    /**
     * 使用过一次技能，使用次数
     */
    public void subTimes() {
        this.timesOfUse--;
    }

    /**
     * 恢复使用次数
     */
    public void recoveryTimes(){
        this.timesOfUse = TIMES;
    }
}
