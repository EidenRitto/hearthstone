package cn.eiden.hsm.threadpool;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

/**
 * 自定义连接池
 * @author Eiden J.P Zhou
 * @date 2020/9/23 14:36
 */
public class HearthThreadPoolExecutor extends ThreadPoolExecutor {
    public static volatile HearthThreadPoolExecutor hearthThreadPool;

    private HearthThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public HearthThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public static HearthThreadPoolExecutor getInstance() {
        if (hearthThreadPool == null) {
            synchronized (HearthThreadPoolExecutor.class) {
                if (hearthThreadPool == null) {
                    hearthThreadPool = new HearthThreadPoolExecutor(10, 50, 2, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));
                }
            }
        }
        return hearthThreadPool;
    }

    /**
     * 连接池线程后置增强
     * @param r 线程
     * @param t 异常
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (t != null){
            //异常处理
        }
    }


    static class HearthThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(@NotNull Runnable r) {
            return null;
        }
    }
}
