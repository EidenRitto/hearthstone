package cn.eiden.hsm.game.card;


import cn.eiden.hsm.game.hero.Profession;
import cn.eiden.hsm.game.objct.Minion;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *  随从卡
 * */
@Setter
@Getter
public abstract class AbstractMinionCard extends AbstractCard {
    /**生命值上限*/
    private Long healthLimit;
    /**攻击力*/
    private Long attackValue;
    /**卡牌对应的随从*/
    private Minion minion;

    public AbstractMinionCard(Long healthLimit, Long attackValue) {
        this.healthLimit = healthLimit;
        this.attackValue = attackValue;
    }

    public AbstractMinionCard(int cost, String description, String cardName, Profession profession, Long healthLimit, Long attackValue, Minion minion) {
        super(cost, description, cardName, profession);
        this.healthLimit = healthLimit;
        this.attackValue = attackValue;
        this.minion = minion;
    }
}
