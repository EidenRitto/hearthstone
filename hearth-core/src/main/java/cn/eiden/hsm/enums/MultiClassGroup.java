package cn.eiden.hsm.enums;

/**
 * 多职业组
 * @author Eiden J.P Zhou
 * @date 2020/8/5 17:22
 */
public enum MultiClassGroup {
    /**无效*/
    INVALID(0),
    /**污手党*/
    GRIMY_GOONS(1),
    /**玉莲帮*/
    JADE_LOTUS(2),
    /**暗金教*/
    KABAL(3),
    /**骑士-牧师*/
    PALADIN_PRIEST(4),
    /**牧师-术士*/
    PRIEST_WARLOCK(5),
    /**术士-恶魔猎手*/
    WARLOCK_DEMONHUNTER(6),
    /**恶魔猎手-猎人*/
    HUNTER_DEMONHUNTER(7),
    /**猎人-德鲁伊*/
    DRUID_HUNTER(8),
    /**德鲁伊-萨满*/
    DRUID_SHAMAN(9),
    /**萨满-法师*/
    MAGE_SHAMAN(10),
    /**法师-盗贼*/
    MAGE_ROGUE(11),
    /**盗贼-战士*/
    ROGUE_WARRIOR(12),
    /**战士-骑士*/
    PALADIN_WARRIOR(13)
    ;
    /**代号*/
    private int code;

    MultiClassGroup(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
