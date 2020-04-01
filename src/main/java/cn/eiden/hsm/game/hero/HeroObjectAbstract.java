package cn.eiden.hsm.game.hero;

import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.output.OutputInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/26
 */
@Slf4j
public class HeroObjectAbstract extends AbstractMinionObject {
    /**生命值上限*/
    private static final Long HEALTH = 30L;
    /**职业*/
    private Profession profession;
    /**护甲*/
    private long armor;
    public HeroObjectAbstract(Profession profession) {
        super(profession.name(),HEALTH,0L);
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
