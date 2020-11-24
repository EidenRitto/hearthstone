package cn.eiden.hsm.cockpit.java;

import cn.eiden.hsm.cockpit.java.thread.GameServerStarterThread;
import cn.eiden.hsm.threadpool.HearthThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 注册中心
 * @author Eiden J.P Zhou
 * @date 2020/9/23 11:25
 */
public class Register {
    private static volatile Register instance;
    /**排队等待队列*/
    private BlockingQueue<UserPipeline> waitingQueue = new ArrayBlockingQueue<>(100);
    private Register(){
        //私有化构造器
    }
    public static Register getInstance(){
        if (instance == null){
            synchronized (Register.class){
                if (instance == null){
                    instance = new Register();
                    HearthThreadPoolExecutor.getInstance().execute(new GameServerStarterThread(instance));
                }
            }
        }
        return instance;
    }

    public void registered(UserPipeline userPipeline){
        waitingQueue.add(userPipeline);
        if (waitingQueue.size()>2){

        }
    }

    public BlockingQueue<UserPipeline> getWaitingQueue() {
        return waitingQueue;
    }
}
