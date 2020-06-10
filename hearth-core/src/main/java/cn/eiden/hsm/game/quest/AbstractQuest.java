package cn.eiden.hsm.game.quest;

import cn.eiden.hsm.game.AbstractGeneralItem;
import cn.eiden.hsm.listener.AbstractQuestListener;
import cn.eiden.hsm.output.OutputInfo;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/10 9:41
 */
public abstract class AbstractQuest extends AbstractGeneralItem implements Quest {
    private int questProgressTotal;
    private int questProgressCurrent = 0;

    private AbstractQuestListener questListener;

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
    public AbstractQuestListener getListener() {
        return this.questListener;
    }

    @Override
    public void printProgress() {
        OutputInfo.info("任务进度%s/%s", questProgressCurrent, questProgressTotal);
    }

    public void setQuestListener(AbstractQuestListener questListener) {
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
}
