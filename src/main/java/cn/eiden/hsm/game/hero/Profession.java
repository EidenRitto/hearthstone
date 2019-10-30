package cn.eiden.hsm.game.hero;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 *  职业
 *
 */
public enum Profession {
    /**法师*/
    Mega("法师",0),
    /**术士*/
    Warlock("术士",1),
    /**牧师*/
    Priest("牧师",2),
    /**圣骑士*/
    Paladin("圣骑士",3),
    /**猎人*/
    Hunter("猎人",4),
    /**战士*/
    Warrior("战士",5),
    /**德鲁伊*/
    Druid("德鲁伊",6),
    /**潜行者*/
    Rogue("潜行者",7),
    /**萨满*/
    Shaman("萨满",8);

    Profession(String name, int id) {
        _name = name;
        _id = id;
    }
    private String _name;
    private int _id;
}
