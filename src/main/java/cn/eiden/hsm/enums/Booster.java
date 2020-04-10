package cn.eiden.hsm.enums;

/**
 * @author 周晋平
 * @date 2020/4/9 11:36
 */
public enum Booster
{
    /**无效*/
    INVALID (0),
    /**经典*/
    CLASSIC (1),
    /**地精大战侏儒*/
    GOBLINS_VS_GNOMES (9),
    THE_GRAND_TOURNAMENT (10),
    OLD_GODS (11),
    FIRST_PURCHASE_OLD (17),
    SIGNUP_INCENTIVE (18),
    MEAN_STREETS (19),
    UNGORO (20),
    FROZEN_THRONE (21),
    GOLDEN_CLASSIC_PACK (23),
    KOBOLDS_AND_CATACOMBS (30),
    KOBOLDS_CATACOMBS (30),
    WITCHWOOD (31),
    THE_BOOMSDAY_PROJECT (38),
    RASTAKHANS_RUMBLE (40),
    MAMMOTH_BUNDLE (41),
    DALARAN (49),
    ULDUM (128),
    FIRST_PURCHASE (181),
    DRAGONS (347),
    BLACK_TEMPLE (423),
    STANDARD_HUNTER (470),
    YEAR_OF_DRAGON (498),
    STANDARD_MAGE (545),
    ;
    private int code;

    Booster(int code) {
        this.code = code;
    }
}
