package cn.eiden.hsm.controller.order;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/30 9:21
 */
public enum OrderType {
    /**无效*/
    INVALID("-1"),
    /**结束*/
    END("0"),
    /**使用（打出）*/
    PLAY("1"),
    /**技能*/
    SPELL("2"),
    /**攻击*/
    ATK("3"),
    /**帮助*/
    HELP("99")
    ;
    private String code;

    OrderType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
