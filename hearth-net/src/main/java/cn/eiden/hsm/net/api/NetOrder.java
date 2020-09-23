package cn.eiden.hsm.net.api;

/**
 * TCP指令
 * @author Eiden J.P Zhou
 * @date 2020/9/23 11:13
 */
public enum NetOrder {
    PLAY("开始匹配");
    private String name;

    NetOrder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
