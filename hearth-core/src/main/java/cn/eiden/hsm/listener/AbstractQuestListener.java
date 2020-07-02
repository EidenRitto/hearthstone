package cn.eiden.hsm.listener;

import cn.eiden.hsm.game.quest.Quest;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/10 11:58
 */
public abstract class AbstractQuestListener implements QuestListener,Cloneable{
    /**
     * 监听所绑定的
     */
    private Quest quest;

    @Override
    public Quest getQuest() {
        return quest;
    }

    @Override
    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public AbstractQuestListener() {
    }

    public AbstractQuestListener(Quest quest) {
        this.quest = quest;
    }

    @Override
    public QuestListener clone() {
        try {
            QuestListener clone = (QuestListener) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
