package cn.eiden.hsm.game.card.sch.priest;

import cn.eiden.hsm.game.card.defs.scholomance.priest.CabalAcolyte;
import cn.eiden.hsm.game.keyword.SpellBurst;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.util.RandomUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 秘教侍僧
 * @author Eiden J.P Zhou
 * @date 2020/8/6 15:55
 */
public class CabalAcolyteCard extends CabalAcolyte {
    @Override
    protected SpellBurst selfSpellBurst() {
        //随机获得一个攻击力小于或等于2的敌方随从的控制权。
        return (s,c) -> {
            //拿到所有的敌方随从列表
            List<Minion> minions = s.getOwner().getEnemy().getMinions();
            //过滤其中攻击力小于或等于2的,并生成一个副本
            List<Minion> collect = minions.stream()
                    .filter(e -> e.getAttackValue() <= 2).collect(Collectors.toList());
            //取一个随机的随从
            Optional<Minion> randomOne = Optional.ofNullable(RandomUtils.getRandomOne(collect));
            //如果存在则
            if (randomOne.isPresent()){
                //敌方移除它
                s.getOwner().getEnemy().removeMinion(randomOne.get());
                //我方添加它
                s.getOwner().addMinion(randomOne.get());
            }
        };
    }
}
