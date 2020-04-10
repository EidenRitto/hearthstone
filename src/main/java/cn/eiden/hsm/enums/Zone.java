package cn.eiden.hsm.enums;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/10 11:22
 */
public enum Zone {
    INVALID(0),
    PLAY(1),
    DECK(2),
    HAND(3),
    GRAVEYARD(4),
    REMOVEDFROMGAME(5),
    SETASIDE(6),
    SECRET(7),
    ;
    private int code;

    Zone(int code) {
        this.code = code;
    }
}
