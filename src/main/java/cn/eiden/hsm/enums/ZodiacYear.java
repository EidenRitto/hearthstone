package cn.eiden.hsm.enums;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/10 11:21
 */
public enum ZodiacYear {
    INVALID(-1),
    PRE_STANDARD(0),
    KRAKEN(1),
    MAMMOTH(2),
    RAVEN(3),
    DRAGON(4),
    PHOENIX(5),
    ;
    private int code;

    ZodiacYear(int code) {
        this.code = code;
    }
}
