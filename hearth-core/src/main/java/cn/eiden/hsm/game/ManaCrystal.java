package cn.eiden.hsm.game;

import lombok.Getter;

/**
 * @author Eiden J.P Zhou
 * @date 2020/3/17 19:09
 */
@Getter
public class ManaCrystal extends AbstractGeneralItem {
    /**
     * 上限
     */
    private static final int CRYSTAL_LIMIT = 10;
    /**
     * 下限
     */
    private static final int CRYSTAL_OFFLINE = 0;

    /**
     * 当前法力水晶数量
     */
    private int manaCrystal;
    /**
     * 有活力的法力水晶
     */
    private int alive;
    /**
     * 无法使用的法力水晶
     */
    private int locked;
    /**
     * 过载法力水晶
     */
    private int overload;

    public ManaCrystal() {
        this.manaCrystal = 0;
        this.alive = 0;
        this.locked = 0;
        this.overload = 0;
    }

    /**
     * 获得空的法力水晶
     *
     * @param number 数量
     */
    public void addEmpty(int number) {
        //超过上限无效
        manaCrystal = Math.min(manaCrystal + number, CRYSTAL_LIMIT);
    }

    /**
     * 获得临时的法力水晶
     *
     * @param number 数量
     */
    public void addAlive(int number) {
        //超过上限无效
        alive = Math.min(alive + number, CRYSTAL_LIMIT);
    }

    /**
     * 恢复已使用的法力水晶
     */
    public void recover() {
        alive = manaCrystal;
    }

    /**
     * 使用
     *
     * @param number 数量
     */
    public void applyAvailable(int number) {
        //低于下限无效
        alive = Math.max(alive - number, CRYSTAL_OFFLINE);
    }

    /**
     * 过载
     *
     * @param number 数量
     */
    public void overload(int number) {
        //超过上限无效
        overload = Math.min(overload + number, CRYSTAL_LIMIT);
    }

    /**
     * 加锁
     */
    public void locked() {
        locked = overload;
        overload = 0;
    }

    /**
     * 解锁
     */
    public void unlock() {
        locked = 0;
    }

    /**
     * 获取当前可使用的
     *
     * @return 能用的水晶
     */
    public int getAvailable() {
        return alive - locked;
    }

    /**
     * 检查费用是否充足
     *
     * @param cost 费用
     * @return 充足返回true
     */
    public boolean checkCost(int cost) {
        return cost <= getAvailable();
    }

    /**
     * 新回合开始
     */
    public void newTurnStart() {
        //最大法力水晶加一
        addEmpty(1);
        //恢复已使用的法力水晶
        recover();
        //解锁
        unlock();
        //加锁
        locked();
    }
}
