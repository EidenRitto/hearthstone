package cn.eiden.hsm.game.card.sch.demonhunter;

import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.defs.scholomance.demonhunter.SoulshardLapidary;
import cn.eiden.hsm.game.keyword.Battle;

import java.util.Optional;

/**
 * @author Eiden J.P Zhou
 * @date 2020/8/10 17:00
 */
public class SoulshardLapidaryCard extends SoulshardLapidary {
    @Override
    protected Battle selfBattleCry() {
        return (s,t) -> {
            Optional<Card> any = s.getOwner().getDeckCards().stream().filter(e -> Integer.parseInt(e.getId()) == 59723).findAny();
            if (any.isPresent()){
                s.getOwner().getDeckCards().remove(any.get());
                s.getOwner().getHero().addAttack(5);
            }
        };
    }
}
