package cn.eiden.hsm.game.objct.hero;

import cn.eiden.hsm.game.objct.AbstractMinion;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.output.OutputInfo;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/26
 */
public class HeroObjectAbstract extends AbstractMinion {
    /**生命值上限*/
    private static final Long HEALTH = 30L;
    /**职业*/
    private Profession profession;
    /**护甲*/
    private long armor;
    public HeroObjectAbstract(Profession profession) {
        super(profession.getName(),HEALTH,0L);
    }

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/19 15:59
     *  叠甲
     */
    public void addArmor(long armorNumber){
        OutputInfo.info(getMinionName()+"获得"+armorNumber+"点护甲.");
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
