package cn.eiden.hsm.game.quest;

import cn.eiden.hsm.game.GeneralItem;
import cn.eiden.hsm.listener.QuestListener;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/9 23:14
 */
public interface Quest extends GeneralItem {
    /**
     * 增加进度
     * @param progress 增加的进度量
     * */
    void addProgress(int progress);

    /**
     * 设置进度量
     * @param progress 进度量
     */
    void setProgress(int progress);

    /**
     * 检查进度是否完成
     * @return 完成返回true
     */
    boolean checkComplete();

    /**
     * 获取奖励
     * @return 奖励
     */
    Reward getReward();

    /**
     * 获取监听
     * @return 任务监听
     */
    QuestListener getListener();

    /**
     * 输出进度
     */
    void printProgress();

    /**
     * 克隆
     * @return 返回一个副本,需要手动修改引用
     */
    Quest clone();
}
