package cn.eiden.hsm.cockpit.coolq;

import lombok.Builder;
import lombok.Getter;

import java.util.concurrent.BlockingQueue;

/**
 * 多人酷Q配置
 * @author Eiden J.P Zhou
 * @date 2020/6/4 22:03
 */
@Builder
@Getter
public class MultiConfig {
    private Long groupId;
    private User user1;
    private User user2;
    /**组群消息队列*/
    private BlockingQueue<String> groupMessageQueue;
}
