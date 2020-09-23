package cn.eiden.hsm.net.api;

import org.msgpack.annotation.Message;

/**
 * 游戏请求
 * @author Eiden J.P Zhou
 * @date 2020/9/23 10:51
 */
@Message
public class PlayRequest {
    private String name;
    private String order;

    public PlayRequest() {
    }

    public PlayRequest(String name, String order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
