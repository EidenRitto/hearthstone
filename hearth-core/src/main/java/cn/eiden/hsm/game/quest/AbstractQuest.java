package cn.eiden.hsm.game.quest;

import cn.eiden.hsm.game.AbstractGeneralItem;
import cn.eiden.hsm.listener.HearthListener;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/10 9:41
 */
public abstract class AbstractQuest extends AbstractGeneralItem implements Quest{
    private int questProgressTotal;
    private int questProgressCurrent = 0;

    private HearthListener questListener;

    @Override
    public void addProgress(int progress) {
        this.questProgressCurrent += progress;
    }

    @Override
    public boolean checkComplete() {
        return questProgressCurrent >= questProgressTotal;
    }

    @Override
    public HearthListener getListener() {
        return this.questListener;
    }

    public void setQuestListener(HearthListener questListener) {
        this.questListener = questListener;
    }

    public void setQuestProgressTotal(int questProgressTotal) {
        this.questProgressTotal = questProgressTotal;
    }
}
