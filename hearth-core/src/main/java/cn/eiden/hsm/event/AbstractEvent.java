package cn.eiden.hsm.event;

import cn.eiden.hsm.game.Gamer;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Eiden J.P Zhou
 * @date 2019/10/29 14:48
 */
@AllArgsConstructor
@Data
public abstract class AbstractEvent {
    protected Gamer owner;
}
