package cn.eiden.hsm.enums;

/**
 * @author 周晋平
 * @date 2020/4/9 11:56
 */
public enum Faction {
    INVALID (0),
    HORDE (1),
    ALLIANCE (2),
    NEUTRAL (3),
    ;
    private int code;

    Faction(int code) {
        this.code = code;
    }
}
