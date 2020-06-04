package cn.eiden.hsm.controller.order;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/26 14:30
 */
public enum ShowType {
    /**战场*/
    CHECKERBOARD("0","战场"),
    /**英雄*/
    HERO("1","英雄"),
    /**手牌*/
    HAND("2","手牌"),
    /**牌库*/
    DECK("3","牌库");
    private String code;
    private String explanation;

    ShowType(String code, String explanation) {
        this.code = code;
        this.explanation = explanation;
    }

    public String getCode() {
        return code;
    }

    public String getExplanation() {
        return explanation;
    }
}
