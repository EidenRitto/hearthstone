package game.card;


import game.objct.ServantObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description : 随从卡
 * */
public class ServantCard extends AbstractCard {
    /**生命值上限*/
    private Long healthLimit;
    /**攻击力*/
    private Long attackValue;
    /**卡牌对应的随从*/
    private ServantObject servant;

    @Override
    public void useCard() {

    }

    public ServantCard(Long healthLimit, Long attackValue) {
        this.healthLimit = healthLimit;
        this.attackValue = attackValue;
    }

    public ServantCard(int cost, String description, String cardName, Long healthLimit, Long attackValue,ServantObject servant) {
        super(cost, description,cardName);
        this.healthLimit = healthLimit;
        this.attackValue = attackValue;
        this.servant = servant;
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

    public ServantObject getServant() {
        return servant;
    }

    public void setServant(ServantObject servant) {
        this.servant = servant;
    }
}
