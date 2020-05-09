package cn.eiden.hsm.controller.order;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/30 9:21
 */
public enum OrderType {
    /**无效*/
    INVALID("-1","无效"),
    /**结束*/
    END("0","结束"),
    /**使用（打出）*/
    PLAY("1","使用"),
    /**技能*/
    SPELL("2","技能"),
    /**攻击*/
    ATK("3","攻击"),
    /**帮助*/
    HELP("99","帮助")
    ;
    private String code;
    private String explanation;

    public String getCode() {
        return code;
    }

    public String getExplanation() {
        return explanation;
    }

    OrderType(String code, String explanation) {
        this.code = code;
        this.explanation = explanation;
    }
}
