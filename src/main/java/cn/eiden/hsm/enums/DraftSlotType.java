package cn.eiden.hsm.enums;

/**
 * @author 周晋平
 * @date 2020/4/9 11:55
 */
public enum DraftSlotType {
    DRAFT_SLOT_NONE (0),
    DRAFT_SLOT_CARD (1),
    DRAFT_SLOT_HERO (2),
    DRAFT_SLOT_HERO_POWER (3),
    ;
    private int code;

    DraftSlotType(int code) {
        this.code = code;
    }
}
