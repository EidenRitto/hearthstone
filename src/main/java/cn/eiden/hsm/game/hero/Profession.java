package cn.eiden.hsm.game.hero;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 *  职业
 *
 */
public enum Profession {
    /**中立*/
    Neutral("中立",0),
    /**法师*/
    Mega("法师",1),
    /**术士*/
    Warlock("术士",2),
    /**牧师*/
    Priest("牧师",3),
    /**圣骑士*/
    Paladin("圣骑士",4),
    /**猎人*/
    Hunter("猎人",5),
    /**战士*/
    Warrior("战士",6),
    /**德鲁伊*/
    Druid("德鲁伊",7),
    /**潜行者*/
    Rogue("潜行者",8),
    /**萨满*/
    Shaman("萨满",9);

    Profession(String name, int id) {
        this.name = name;
        this.id = id;
    }
    private String name;
    private int id;

    public String getName() {
        return name;
    }
}
