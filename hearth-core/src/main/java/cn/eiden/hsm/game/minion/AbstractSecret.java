package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.game.Gamer;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/27 17:22
 */
public abstract class AbstractSecret implements Secret {
    private Gamer owner;

    @Override
    public Gamer getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Gamer gamer) {
        this.owner = gamer;
    }
}