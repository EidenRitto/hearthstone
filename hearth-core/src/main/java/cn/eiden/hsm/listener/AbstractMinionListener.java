package cn.eiden.hsm.listener;

import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 9:48
 */
public abstract class AbstractMinionListener implements MinionListener, Cloneable {
    /**
     * 监听所绑定的随从
     */
    private Minion minion;

    @Override
    public Minion getMinion() {
        return this.minion;
    }

    @Override
    public void setMinion(Minion minion) {
        this.minion = minion;
    }

    public AbstractMinionListener(Minion minion) {
        this.minion = minion;
    }

    public AbstractMinionListener() {
    }

    @Override
    public MinionListener clone() {
        try {
            return (MinionListener) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
