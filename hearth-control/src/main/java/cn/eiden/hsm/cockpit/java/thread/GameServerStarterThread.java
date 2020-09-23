package cn.eiden.hsm.cockpit.java.thread;

import cn.eiden.hsm.cockpit.java.Register;
import cn.eiden.hsm.cockpit.java.UserPipeline;
import cn.eiden.hsm.threadpool.HearthThreadPoolExecutor;

import java.util.concurrent.BlockingQueue;

/**
 * 服务启动器线程
 * @author Eiden J.P Zhou
 * @date 2020/9/23 14:50
 */
public class GameServerStarterThread implements Runnable{
    private Register register;

    public GameServerStarterThread(Register register) {
        this.register = register;
    }

    @Override
    public void run() {
        BlockingQueue<UserPipeline> waitingQueue = register.getWaitingQueue();
        for (;;){
            try {
                UserPipeline user1 = waitingQueue.take();
                UserPipeline user2 = waitingQueue.take();
                GameServerThread gameServerThread = new GameServerThread(user1, user2);
                HearthThreadPoolExecutor.getInstance().execute(gameServerThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
