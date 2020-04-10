package cn.eiden.hsm.enums;

/**
 * 卡牌职业
 * @author 周晋平
 * @date 2020/4/9 10:24
 */
public enum CardClass {
    INVALID (0),
    DEATHKNIGHT (1),
    DRUID (2),
    HUNTER (3),
    MAGE (4),
    PALADIN (5),
    PRIEST (6),
    ROGUE (7),
    SHAMAN (8),
    WARLOCK (9),
    WARRIOR (10),
    DREAM (11),
    NEUTRAL (12),
    WHIZBANG (13),
    DEMONHUNTER (14)
    ;
    private int code;

    CardClass(int code) {
        this.code = code;
    }
}
