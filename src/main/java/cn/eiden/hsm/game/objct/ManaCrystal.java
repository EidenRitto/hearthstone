package cn.eiden.hsm.game.objct;

import lombok.Getter;

/**
 * @author Eiden J.P Zhou
 * @date 2020/3/17 19:09
 */
@Getter
public class ManaCrystal extends GameObject{
    /**上限*/
    private static final int CRYSTAL_LIMIT = 10;
    /**下限*/
    private static final int CRYSTAL_OFFLINE = 0;

    /**当前法力水晶数量*/
    private int manaCrystal;
    /**当前可用法力水晶*/
    private int available;
    /**无法使用的法力水晶*/
    private int locked;
    /**过载法力水晶*/
    private int overload;


    /**
     * 获得空的法力水晶
     * @param number 数量
     * */
    public void addEmpty(int number){
        //超过上限无效
        manaCrystal = Math.min(manaCrystal + number, CRYSTAL_LIMIT);
    }

    /**
     * 获得临时的法力水晶
     * @param number 数量
     * */
    public void addAvailable(int number){
        //超过上限无效
        available += Math.min(available + number, CRYSTAL_LIMIT);
    }

    /**
     * 恢复已使用的法力水晶
     * */
    public void recover(){
        available = manaCrystal;
    }

    /**
     * 使用
     * @param number 数量
     * */
    public void applyAvailable(int number){
        //低于下限无效
        available = Math.max(available - number, CRYSTAL_OFFLINE);
    }

    /**
     * 过载
     * @param number 数量
     */
    public void overload(int number){
        //超过上限无效
        overload = Math.min(overload + number, CRYSTAL_LIMIT);
    }

    public void locked(int number){

    }
}
