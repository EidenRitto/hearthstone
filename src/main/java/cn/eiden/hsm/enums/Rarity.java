package cn.eiden.hsm.enums;

/**
 * 稀有度
 * @author 周晋平
 * @date 2020/4/10 11:16
 */
public enum Rarity {
    INVALID(0),
    COMMON(1),
    FREE(2),
    RARE(3),
    EPIC(4),
    LEGENDARY(5),
    UNKNOWN_6(6),
    ;
    private int code;

    Rarity(int code) {
        this.code = code;
    }
}
