package cn.eiden.hsm.enums;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/9 11:44
 */
public enum CardType {
    /**无效*/
    INVALID (0),
    GAME (1),
    PLAYER (2),
    HERO (3),
    MINION (4),
    SPELL (5),
    ABILITY (5),
    ENCHANTMENT (6),
    WEAPON (7),
    ITEM (8),
    TOKEN (9),
    HERO_POWER (10),
    BLANK (11),
    GAME_MODE_BUTTON (12),
    MOVE_MINION_HOVER_TARGET (22),
    ;
    private int code;

    CardType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
