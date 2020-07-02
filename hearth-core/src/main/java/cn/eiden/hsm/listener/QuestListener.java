package cn.eiden.hsm.listener;

import cn.eiden.hsm.game.quest.Quest;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/10 12:00
 */
public interface QuestListener extends HearthListener {
    /**
     * 获取任务
     * @return 任务
     */
    Quest getQuest();

    /**
     * 设置任务
     * @param quest 任务
     */
    void setQuest(Quest quest);

    /**
     * 克隆
     * @return 返回一个副本,需要手动修改引用
     */
    QuestListener clone();
}
