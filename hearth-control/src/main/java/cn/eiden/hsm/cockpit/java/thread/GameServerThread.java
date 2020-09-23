package cn.eiden.hsm.cockpit.java.thread;

import cn.eiden.hsm.GameDemo;
import cn.eiden.hsm.cockpit.coolq.HearthOrderConstant;
import cn.eiden.hsm.cockpit.java.UserPipeline;
import cn.eiden.hsm.exception.GameOverException;
import cn.eiden.hsm.threadpool.HearthThreadPoolExecutor;

/**
 * 服务器线程
 * @author Eiden J.P Zhou
 * @date 2020/9/23 15:21
 */
public class GameServerThread implements Runnable{
    private UserPipeline user1;
    private UserPipeline user2;

    public GameServerThread(UserPipeline user1, UserPipeline user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    @Override
    public void run() {
        try {
            HearthThreadPoolExecutor executor = HearthThreadPoolExecutor.getInstance();
            executor.execute(user1);
            executor.execute(user2);
            new GameDemo().multiStart(HearthOrderConstant.DECK_MAGE,HearthOrderConstant.DECK_WARRIOR,
                    user1.getOutputQueue(),user1.getInputQueue(),user1.getName(),
                    user2.getOutputQueue(),user2.getInputQueue(),user2.getName());
        } catch (Exception e) {
            throw new GameOverException(e);
        }
    }
}
