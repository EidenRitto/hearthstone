package cn.eiden.hsm.dbdata;

import cn.eiden.hsm.enums.*;
import lombok.Data;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/14 10:09
 */
@Data
public class CardInfo {
    /**名称*/
    private String cardName;
    /**id*/
    private String cardId;
    private String id;
    /**中文名*/
    private String cardCnName;
    /**版本*/
    private CardSet cardSet;
    /**职业*/
    private CardClass cardClass;
    /**类型*/
    private CardType cardType;
    /**稀有度*/
    private Rarity rarity;
    /**文本描述*/
    private String cardText;
    /**费用*/
    private int cost;
    /**攻击力*/
    private int atk;
    /**生命值*/
    private int health;
    /**冲锋*/
    private int charge;
    /**种族*/
    private Race race;
    /**武器耐久*/
    private int durability;
    /**嘲讽*/
    private int taunt;
    /**潜行*/
    private int stealth;
    /**圣盾*/
    private int divineShield;
    /**亡语*/
    private int deathRattle;
    /**战吼*/
    private int battleCry;
    /**法强*/
    private int spellPower;
    /**光环*/
    private int aura;
    /**奥秘*/
    private int secret;
    /**英雄技能*/
    private int heroPower;
    /**剧毒*/
    private int poisonous;
    /**连击*/
    private int combo;
    /**风怒*/
    private int windFury;
    /**
     * 吸血
     */
    private int lifeSteal;
    /**突袭*/
    private int rush;
    /**抉择*/
    private int chooseOne;
    /**过载*/
    private int overload = 0;
    /**任务*/
    private int quest;
    /**任务量*/
    private int questProgressTotal;
    /**任务奖励id*/
    private int questRewardDatabaseId;
    /**双生法术*/
    private int twinSpell;
    /**双生法术 - 复制*/
    private int twinSpellCopy;
    /**复生*/
    private int reborn;

    public CardInfo() {
        this.rarity = Rarity.INVALID;
        this.cardClass = CardClass.INVALID;
        this.race = Race.INVALID;
    }
}
