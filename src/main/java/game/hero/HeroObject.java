package game.hero;

import game.objct.MinionObject;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/26
 * @Description:
 * @Modified By:
 */
public class HeroObject extends MinionObject {
    /**生命值上限*/
    private static final Long HEALTH = 30L;
    /**职业*/
    private Profession profession;
    /**护甲*/
    private long armor;
    public HeroObject(Profession profession) {
        super();
        setMinionName(profession.name());
        setHealthLimit(HEALTH);
        setHealth(HEALTH);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/19 15:59
     * @method : addArmor
     * @params : [armorNumber]
     * @Description : 叠甲
     */
    public void addArmor(long armorNumber){
        System.out.println(getMinionName()+"获得"+armorNumber+"点护甲.");
        armor += armorNumber;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public long getArmor() {
        return armor;
    }

    public void setArmor(long armor) {
        this.armor = armor;
    }
}
