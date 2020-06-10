package cn.eiden.hsm.game.quest;

import cn.eiden.hsm.game.GeneralItem;
import cn.eiden.hsm.listener.HearthListener;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/9 23:14
 */
public interface Quest extends GeneralItem {
    void addProgress(int progress);

    boolean checkComplete();

    Reward getReward();

    HearthListener getListener();
}
