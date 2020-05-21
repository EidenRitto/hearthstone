package cn.eiden.hsm.game;


import cn.eiden.hsm.game.keyword.DeathRattle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *  简单游戏对象抽象类
 * */
public abstract class AbstractGeneralItem implements GeneralItem {
    /**
     * 拥有者
     */
    private Gamer owner;
    /**
     * 亡语
     */
    private List<DeathRattle> deathRattle = new ArrayList<>();

    @Override
    public Gamer getOwner() {
        return owner;
    }
    @Override
    public void setOwner(Gamer owner) {
        this.owner = owner;
    }

    @Override
    public void addDeathRattle(DeathRattle deathRattle) {
        this.deathRattle.add(deathRattle);
    }

    @Override
    public void removeDeathRattle() {
        this.deathRattle = new ArrayList<>();
    }

    @Override
    public boolean hasDeathRattle() {
        return this.deathRattle.size() > 0;
    }

    @Override
    public void doDeathRattle() {
        this.deathRattle.forEach(e->e.doDeathRattle(this));
    }
}
