package cn.eiden.hsm.controller.order;

/**
 * 指令
 * @author Eiden J.P Zhou
 * @date 2020/4/23 17:13
 */
public interface Order {
    /**
     * 执行指令
     */
    void execute();

    /**
     * 命令是否完整
     * @return 完整返回true
     */
    boolean isComplete();
}
