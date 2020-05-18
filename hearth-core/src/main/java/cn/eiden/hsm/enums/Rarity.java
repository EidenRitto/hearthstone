package cn.eiden.hsm.enums;

/**
 * 稀有度
 * @author Eiden J.P Zhou
 * @date 2020/4/10 11:16
 */
public enum Rarity {
    /**无效*/
    INVALID(0),
    /**普通*/
    COMMON(1),
    /**基本*/
    FREE(2),
    /**稀有*/
    RARE(3),
    /**史诗*/
    EPIC(4),
    /**传说*/
    LEGENDARY(5),
    /**未知*/
    UNKNOWN_6(6),
    ;
    private int code;

    Rarity(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
