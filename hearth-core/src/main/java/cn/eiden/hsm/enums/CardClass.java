package cn.eiden.hsm.enums;

/**
 * 卡牌职业
 *
 * @author Eiden J.P Zhou
 * @date 2020/4/9 10:24
 */
public enum CardClass {
    INVALID(0, "无效"),
    DEATHKNIGHT(1, "无效"),
    DRUID(2, "德鲁伊"),
    HUNTER(3, "猎人"),
    MAGE(4, "法师"),
    PALADIN(5, "圣骑士"),
    PRIEST(6, "牧师"),
    ROGUE(7, "盗贼"),
    SHAMAN(8, "萨满"),
    WARLOCK(9, "术士"),
    WARRIOR(10, "战士"),
    DREAM(11, "无效"),
    NEUTRAL(12, "中立"),
    WHIZBANG(13, "无效"),
    DEMONHUNTER(14, "恶魔猎手");
    private int code;
    private String cnName;

    CardClass(int code, String cnName) {
        this.code = code;
        this.cnName = cnName;
    }

    public int getCode() {
        return code;
    }

    public String getCnName() {
        return cnName;
    }
}
