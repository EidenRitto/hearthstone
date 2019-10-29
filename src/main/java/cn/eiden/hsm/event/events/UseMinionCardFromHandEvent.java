package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.GameObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 从手牌中打出随从卡牌事件
 * @author Eiden J.P Zhou
 * @date 2019/10/29 14:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UseMinionCardFromHandEvent extends AbstractEvent {
    private AbstractMinionObject minionObject;
    private GameObject target;
}
