package cn.eiden.hsm.enums;

/**
 * @author 周晋平
 * @date 2020/4/10 11:10
 */
public enum MetaDataType {
    TARGET(0),
    META_TARGET(0),
    DAMAGE(1),
    META_DAMAGE(1),
    HEALING(2),
    META_HEALING(2),
    JOUST(3),
    CLIENT_HISTORY(4),
    SHOW_BIG_CARD(5),
    EFFECT_TIMING(6),
    HISTORY_TARGET(7),
    OVERRIDE_HISTORY(8),
    HISTORY_TARGET_DONT_DUPLICATE_UNTIL_END(9),
    BEGIN_ARTIFICIAL_HISTORY_TILE(10),
    BEGIN_ARTIFICIAL_HISTORY_TRIGGER_TILE(11),
    END_ARTIFICIAL_HISTORY_TILE(12),
    START_DRAW(13),
    BURNED_CARD(14),
    EFFECT_SELECTION(15),
    BEGIN_LISTENING_FOR_TURN_EVENTS(16),
    HOLD_DRAWN_CARD(17),
    CONTROLLER_AND_ZONE_CHANGE(18),
    ARTIFICIAL_PAUSE(19),
    ARTIFICIAL_PAUSE_STUBBED_FOR_14_2(19),
    SLUSH_TIME(20),
    ARTIFICIAL_HISTORY_INTERRUPT(21),
    ;
    private int code;

    MetaDataType(int code) {
        this.code = code;
    }
}
