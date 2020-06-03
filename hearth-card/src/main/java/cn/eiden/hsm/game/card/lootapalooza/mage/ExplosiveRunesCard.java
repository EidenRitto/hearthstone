package cn.eiden.hsm.game.card.lootapalooza.mage;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.lootapalooza.mage.ExplosiveRunes;
import cn.eiden.hsm.game.minion.AbstractSecret;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Secret;
import cn.eiden.hsm.output.OutputInfo;

/**
 * 爆炸符文
 * @author Eiden J.P Zhou
 * @date 2020/6/3 16:33
 */
public class ExplosiveRunesCard extends ExplosiveRunes {
    @Override
    public Secret createSecret() {
        return new ExplosiveRunesSecret();
    }

    static class ExplosiveRunesSecret extends AbstractSecret{
        private static final int DMG = 6;

        @Override
        public boolean onSecret(Event event) {
            // TODO: 2020/6/3 如果奥秘挂了狙击和爆炸符文 前者导致随从死亡后，爆炸符文还触发吗？待验证后实现
            UseMinionCardFromHandEvent useMinionEvent = (UseMinionCardFromHandEvent) event;
            Gamer secretOwner = useMinionEvent.getOwner().getEnemy();
            Minion minion = useMinionEvent.getMinionObject();
            long minionHealth = minion.getHealth();
            OutputInfo.info("法师奥秘触发:爆炸符文");
            //计算总伤害（基本伤害加上法强加成）
            int damage = DMG + secretOwner.getGamerSpellDamage();
            //随从伤害最多为总伤害,最少为随从生命值
            int minionDmg = (int) Math.min(minionHealth,damage);
            minion.beHurt(minionDmg);
            useMinionEvent.getOwner().checkMinion();
            int heroDmg = (int) (damage - minionHealth);
            //如果还有溢出的伤害则给英雄
            if (heroDmg > 0){
                useMinionEvent.getOwner().getHero().beHurt(heroDmg);
            }
            return true;
        }

        @Override
        public Class<? extends Event> triggerEvent() {
            return UseMinionCardFromHandEvent.class;
        }
    }
}
