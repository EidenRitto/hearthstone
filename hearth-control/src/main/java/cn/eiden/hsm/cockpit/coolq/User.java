package cn.eiden.hsm.cockpit.coolq;

import lombok.Builder;
import lombok.Getter;

import java.util.concurrent.BlockingQueue;

/**
 * 游戏用户信息配置
 * @author Eiden J.P Zhou
 * @date 2020/6/4 22:04
 */
@Builder
@Getter
public class User {
    /**qq号*/
    private Long id;
    /**卡组代码*/
    private String deckStr;
    /**私人消息队列*/
    private BlockingQueue<String> messageQueue;
    /**私人消息输入队列*/
    private BlockingQueue<String> inputQueue;
}
