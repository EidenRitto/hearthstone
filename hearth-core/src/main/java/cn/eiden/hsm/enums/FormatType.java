package cn.eiden.hsm.enums;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/9 11:58
 */
public enum FormatType {
    FT_UNKNOWN (0),
    FT_WILD (1),
    FT_STANDARD (2),
    ;
    private int code;

    FormatType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
