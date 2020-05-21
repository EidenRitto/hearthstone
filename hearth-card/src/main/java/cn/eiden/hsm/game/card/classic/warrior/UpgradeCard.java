package cn.eiden.hsm.game.card.classic.warrior;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractWeaponCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.expert1.warrior.Upgrade;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Weapon;
import cn.eiden.hsm.game.minion.hero.Hero;

/**
 * 升级
 * 如果你装备一把武器，使它获得+1/+1。否则，装备一把1/3的武器。
 * @author Eiden J.P Zhou
 * @date 2020/5/20 16:00
 */
public class UpgradeCard extends Upgrade {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        Hero hero = gamer.getHero();
        if (hero.hasWeapon()){
            Weapon weapon = hero.getWeapon();
            weapon.addAttackValue(1L);
            weapon.addDurable(1L);
        }else {
            AbstractWeaponCard weapon = (AbstractWeaponCard) CardFactory.getCardById(1661);
            assert weapon != null;
            hero.equipWeapons(weapon.createWeapon());
        }
    }
}
