package cn.eiden.hsm.enums;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/9 11:56
 */
public enum Faction {
    /**无效*/
    INVALID (0),
    /**部落*/
    HORDE (1),
    /**联盟*/
    ALLIANCE (2),
    /**中立*/
    NEUTRAL (3),
    ;
    private int code;

    Faction(int code) {
        this.code = code;
    }
}
