package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.output.OutputInfo;
import cn.eiden.hsm.util.RegexUtil;
import lombok.Setter;

import java.util.List;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/23 17:32
 */
@Setter
public class AtkOrder extends AbstractOrder implements Order {


    @Override
    public void execute() {
        Minion sourceMinion = getSourceMinion();
        if (sourceMinion == null) {
            return;
        }
        Minion targetMinion = getTargetIndex();
        if (targetMinion == null) {
            return;
        }
        sourceMinion.attack(targetMinion);
        sourceMinion.getOwner().checkMinion();
        sourceMinion.getOwner().getEnemy().checkMinion();
    }

    public Minion getSourceMinion() {
        while (true) {
            List<Minion> allCanAttackMinionsId = gamer.findAllCanAttackMinionsId();
            if (allCanAttackMinionsId.size() == 0) {
                OutputInfo.info(gamer.getPrivateMessageQueue(), "当前没有能攻击的随从");
                return null;
            }
            //打印提示信息
            gamer.printMinion(allCanAttackMinionsId, "选择需要攻击的随从");
            //等待输入信息
            String input = getOrder();
            if (RegexUtil.isNumberStr(input)) {
                int i = Integer.parseInt(input);
                if (getInRange(allCanAttackMinionsId, i)) {
                    return allCanAttackMinionsId.get(i);
                }
            } else {
                return null;
            }
            OutputInfo.info(gamer.getPrivateMessageQueue(),"非法输入！");
        }
    }

    public boolean getInRange(List<Minion> allCanAttackMinionsId, int i) {
        return i >= 0 && i < allCanAttackMinionsId.size();
    }

    public Minion getTargetIndex() {
        while (true) {
            List<Minion> allCanBeAttackMinionsId = gamer.findAllCanBeAttackMinionsId();
            if (allCanBeAttackMinionsId.size() == 0) {
                OutputInfo.info(gamer.getPrivateMessageQueue(),"当前没有能被攻击的随从/英雄，这是不可能的，看见这句话请反馈BUG");
                return null;
            }
            //打印提示信息
            gamer.printMinion(allCanBeAttackMinionsId, "选择要攻击的随从/英雄:");
            //等待输入信息
            String input = getOrder();
            if (RegexUtil.isNumberStr(input)) {
                int i = Integer.parseInt(input);
                if (getInRange(allCanBeAttackMinionsId, i)) {
                    return allCanBeAttackMinionsId.get(i);
                }
            } else {
                return null;
            }
            OutputInfo.info(gamer.getPrivateMessageQueue(),"非法输入！");
        }
    }

    public AtkOrder(Gamer gamer) {
        super(gamer);
    }
}
