package cn.eiden.hsm.game.card;


import cn.eiden.hsm.game.objct.AbstractMinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *  随从卡
 * */
public class MinionCard extends AbstractCard {
    /**生命值上限*/
    private Long healthLimit;
    /**攻击力*/
    private Long attackValue;
    /**卡牌对应的随从*/
    private AbstractMinionObject minion;

    @Override
    public void useCard() {

    }

    public MinionCard(Long healthLimit, Long attackValue) {
        this.healthLimit = healthLimit;
        this.attackValue = attackValue;
    }

    public MinionCard(int cost, String description, String cardName, Long healthLimit, Long attackValue, AbstractMinionObject minion) {
        super(cost, description,cardName);
        this.healthLimit = healthLimit;
        this.attackValue = attackValue;
        this.minion = minion;
    }

    public Long getHealthLimit() {
        return healthLimit;
    }

    public void setHealthLimit(Long healthLimit) {
        this.healthLimit = healthLimit;
    }

    public Long getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(Long attackValue) {
        this.attackValue = attackValue;
    }

    public AbstractMinionObject getMinion() {
        return minion;
    }

    public void setMinion(AbstractMinionObject minion) {
        this.minion = minion;
    }
}
