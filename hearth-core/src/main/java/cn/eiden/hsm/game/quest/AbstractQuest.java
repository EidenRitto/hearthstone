package cn.eiden.hsm.game.quest;

import cn.eiden.hsm.game.AbstractGeneralItem;
import cn.eiden.hsm.listener.AbstractQuestListener;
import cn.eiden.hsm.listener.QuestListener;
import cn.eiden.hsm.listener.WeaponListener;
import cn.eiden.hsm.output.OutputInfo;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/10 9:41
 */
public abstract class AbstractQuest extends AbstractGeneralItem implements Quest, Cloneable {
    private int questProgressTotal;
    private int questProgressCurrent = 0;

    private QuestListener questListener;

    @Override
    public void addProgress(int progress) {
        this.questProgressCurrent += progress;
        printProgress();
    }

    @Override
    public void setProgress(int progress) {
        this.questProgressCurrent = progress;
        printProgress();
    }

    @Override
    public boolean checkComplete() {
        return questProgressCurrent >= questProgressTotal;
    }

    @Override
    public QuestListener getListener() {
        return this.questListener;
    }

    @Override
    public void printProgress() {
        getOwner().printPublicQueue(String.format("任务进度%s/%s", questProgressCurrent, questProgressTotal));
    }

    public void setQuestListener(QuestListener questListener) {
        this.questListener = questListener;
    }

    public void setQuestProgressTotal(int questProgressTotal) {
        this.questProgressTotal = questProgressTotal;
    }

    public AbstractQuest(int questProgressTotal, AbstractQuestListener questListener) {
        this.questProgressTotal = questProgressTotal;
        this.questListener = questListener;
        this.questListener.setQuest(this);
    }

    @Override
    public Quest clone() {
        try {
            AbstractQuest clone = (AbstractQuest) super.clone();
            if (this.questListener != null) {
                QuestListener listener = this.questListener.clone();
                listener.setQuest(clone);
                clone.setQuestListener(listener);
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
