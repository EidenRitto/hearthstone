package cn.eiden.hsm.game.card;


import cn.eiden.hsm.enums.*;
import cn.eiden.hsm.game.minion.Minion;
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
    private Long health;
    /**攻击力*/
    private Long atk;
    /**种族*/
    private Race race;

    public AbstractMinionCard(int cost, String description, String cardName, CardClass cardClass, Long health, Long atk) {
        super(cost, description, cardName, cardClass);
        this.health = health;
        this.atk = atk;
    }

    public AbstractMinionCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, cn.eiden.hsm.enums.CardClass cardClass, CardType cardType, Rarity rarity, Long health, Long atk, Race race, int overload) {
        super(cardName, cost, description, id, cardId, cardSet, cardClass, cardType, rarity, overload);
        this.health = health;
        this.atk = atk;
        this.race = race;
    }

    /**
     * 组合优于继承！
     * 创建卡牌对应的随从
     * @return 卡牌对应的随从
     * */
    public abstract Minion createMinion();
}
