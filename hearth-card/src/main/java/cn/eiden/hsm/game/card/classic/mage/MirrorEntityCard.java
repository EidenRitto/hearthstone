package cn.eiden.hsm.game.card.classic.mage;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.expert1.mage.MirrorEntity;
import cn.eiden.hsm.game.minion.AbstractSecret;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Secret;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/3 17:04
 */
public class MirrorEntityCard extends MirrorEntity {
    @Override
    public Secret createSecret() {
        return new MirrorEntitySecret();
    }
    static class MirrorEntitySecret extends AbstractSecret{

        @Override
        public boolean onSecret(Event event) {
            UseMinionCardFromHandEvent useMinionEvent = (UseMinionCardFromHandEvent) event;
            Minion minion = useMinionEvent.getMinionObject();
            Gamer secretOwner = useMinionEvent.getOwner().getEnemy();
            //创建复制
            Minion minionMirror = minion.clone();
            secretOwner.addMinion(minionMirror);
            return true;
        }

        @Override
        public Class<? extends Event> triggerEvent() {
            return UseMinionCardFromHandEvent.class;
        }
    }
}
