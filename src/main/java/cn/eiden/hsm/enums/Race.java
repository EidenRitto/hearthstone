package cn.eiden.hsm.enums;

/**
 * 种族
 * @author 周晋平
 * @date 2020/4/10 11:15
 */
public enum Race {
    INVALID(0),
    BLOODELF(1),
    DRAENEI(2),
    DWARF(3),
    GNOME(4),
    GOBLIN(5),
    HUMAN(6),
    NIGHTELF(7),
    ORC(8),
    TAUREN(9),
    TROLL(10),
    UNDEAD(11),
    WORGEN(12),
    GOBLIN2(13),
    MURLOC(14),
    DEMON(15),
    SCOURGE(16),
    MECHANICAL(17),
    ELEMENTAL(18),
    OGRE(19),
    BEAST(20),
    PET(20),
    TOTEM(21),
    NERUBIAN(22),
    PIRATE(23),
    DRAGON(24),
    BLANK(25),
    ALL(26),
    EGG(38),
    ;
    private int code;

    Race(int code) {
        this.code = code;
    }
}
