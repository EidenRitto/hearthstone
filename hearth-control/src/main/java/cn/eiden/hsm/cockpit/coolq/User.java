package cn.eiden.hsm.cockpit.coolq;

import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 游戏用户信息配置
 * @author Eiden J.P Zhou
 * @date 2020/6/4 22:04
 */
@Data
public class User {
    /**qq号*/
    private Long id;
    private String name;
    /**卡组代码*/
    private String deckStr;
    /**私人消息队列*/
    private BlockingQueue<String> messageQueue;
    /**私人消息输入队列*/
    private BlockingQueue<String> inputQueue;

    public boolean isReady(){
        return id!=null && name != null && deckStr != null;
    }

    public User() {
        this.messageQueue = new LinkedBlockingQueue<>();
        this.inputQueue = new LinkedBlockingQueue<>();
    }
}
