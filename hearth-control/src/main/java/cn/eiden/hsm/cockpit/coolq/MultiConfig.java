package cn.eiden.hsm.cockpit.coolq;

import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 多人酷Q配置
 * @author Eiden J.P Zhou
 * @date 2020/6/4 22:03
 */
@Data
public class MultiConfig {
    /**qq群号*/
    private Long groupId;
    /**举办者*/
    private User organizer;
    /**响应者*/
    private User responder;
    /**组群消息队列*/
    private BlockingQueue<String> groupMessageQueue;

    private AtomicBoolean isGameStart = new AtomicBoolean(false);
}
