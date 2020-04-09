package cn.eiden.hsm.game.objct;

/**
 * 随从（不包含英雄）的父类
 * @author Eiden J.P Zhou
 * @date 2020/4/4 15:44
 */
public class AbstractMinionObject extends AbstractMinion {
    public AbstractMinionObject() {
    }

    public AbstractMinionObject(String minionName, Long healthLimit, Long attackValue) {
        super(minionName, healthLimit, attackValue);
    }
}
