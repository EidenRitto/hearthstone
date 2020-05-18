package cn.eiden.hsm.enums;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/9 11:54
 */
public enum DeckType {
    CLIENT_ONLY_DECK (-1),
    UNKNOWN_DECK_TYPE (0),
    NORMAL_DECK (1),
    AI_DECK (2),
    DRAFT_DECK (4),
    PRECON_DECK (5),
    TAVERN_BRAWL_DECK (6),
    FSG_BRAWL_DECK (7),
    HIDDEN_DECK (1000),
    ;
    private int code;

    DeckType(int code) {
        this.code = code;
    }
}
