package cn.eiden.hsm.game.tags;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 *  职业
 *
 */
public enum Profession {
    /**中立*/
    Neutral("中立",0,""),
    /**法师*/
    Mega("法师",1,"mega"),
    /**术士*/
    Warlock("术士",2,"warlock"),
    /**牧师*/
    Priest("牧师",3,"priest"),
    /**圣骑士*/
    Paladin("圣骑士",4,"paladin"),
    /**猎人*/
    Hunter("猎人",5,"hunter"),
    /**战士*/
    Warrior("战士",6,"warrior"),
    /**德鲁伊*/
    Druid("德鲁伊",7,"druid"),
    /**潜行者*/
    Rogue("潜行者",8,"rogue"),
    /**萨满*/
    Shaman("萨满",9,"shaman");

    Profession(String name, int id, String packagePath) {
        this.name = name;
        this.id = id;
        this.packagePath = packagePath;
    }

    private String name;
    private int id;
    private String packagePath;

    public String getName() {
        return name;
    }

    public String getPackagePath() {
        return packagePath;
    }
}
