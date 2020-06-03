package cn.eiden.hsm.enums;

/**
 * 卡牌扩展包
 * @author Eiden J.P Zhou
 * @date 2020/4/9 11:41
 */
public enum CardSet {
    INVALID (0),
    TEST_TEMPORARY (1),
    /**基本*/
    CORE (2),
    /**经典*/
    EXPERT1 (3),
    /**荣誉室*/
    HOF (4),
    REWARD (4),
    MISSIONS (5),
    DEMO (6),
    NONE (7),
    CHEAT (8),
    BLANK (9),
    DEBUG_SP (10),
    PROMO (11),
    /**纳克萨玛斯*/
    NAXX (12),
    FP1 (12),
    /**地精大战侏儒*/
    GVG (13),
    PE1 (13),
    BRM (14),
    FP2 (14),
    TGT (15),
    PE2 (15),
    TEMP1 (15),
    CREDITS (16),
    HERO_SKINS (17),
    TB (18),
    SLUSH (19),
    LOE (20),
    OG (21),
    OG_RESERVE (22),
    KARA (23),
    KARA_RESERVE (24),
    /**龙争虎斗加基森*/
    GANGS (25),
    GANGS_RESERVE (26),
    UNGORO (27),
    ICECROWN (1001),
    /**狗头人与地下世界*/
    LOOTAPALOOZA (1004),
    GILNEAS (1125),
    BOOMSDAY (1127),
    TROLL (1129),
    DALARAN (1130),
    TAVERNS_OF_TIME (1143),
    ULDUM (1158),
    DRAGONS (1347),
    YEAR_OF_THE_DRAGON (1403),
    BLACK_TEMPLE (1414),
    WILD_EVENT (1439),
    BATTLEGROUNDS (1453),
    DEMON_HUNTER_INITIATE (1463),
    ;
    private int code;

    CardSet(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
