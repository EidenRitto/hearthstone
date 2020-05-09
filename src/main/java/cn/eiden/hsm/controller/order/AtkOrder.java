package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.objct.Minion;
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
    private Gamer gamer;

    @Override
    public void execute() {
        Integer sourceIndex = getSourceIndex();
        Integer targetIndex = getTargetIndex();

        if (sourceIndex == null || targetIndex == null){
            return;
        }
        Minion attacker;
        Minion beAttacker;
        if (sourceIndex == OrderConstant.TARGET_HERO_INDEX) {
            attacker = gamer.getHero();
        } else {
            attacker = gamer.getMinion(sourceIndex);
        }
        if (targetIndex == OrderConstant.TARGET_HERO_INDEX) {
            beAttacker = gamer.getEnemy().getHero();
        } else {
            beAttacker = gamer.getEnemy().getMinion(sourceIndex);
        }
        attacker.attack(beAttacker);
    }

    public Integer getSourceIndex(){
        while (true){
            List<Integer> allCanAttackMinionsId = gamer.findAllCanAttackMinionsId();
            //打印提示信息
            gamer.printAllCanAttackMinionsInfo();
            //等待输入信息
            String input = getOrder();
            if (RegexUtil.isNumberStr(input)){
                Integer index = Integer.parseInt(input);
                if (allCanAttackMinionsId.contains(index)){
                    // TODO: 2020/5/7 提示具体非法信息
                    return index;
                }
            }else {
                return null;
            }
            OutputInfo.info("非法输入！");
        }
    }

    public Integer getTargetIndex(){
        while (true){
            List<Integer> allCanBeAttackMinionsId = gamer.findAllCanBeAttackMinionsId();
            //打印提示信息
            gamer.printAllCanBeAttackMinionsInfo();
            //等待输入信息
            String input = getOrder();
            if (RegexUtil.isNumberStr(input)){
                Integer index = Integer.parseInt(input);
                if (allCanBeAttackMinionsId.contains(index)){
                    // TODO: 2020/5/7 提示具体非法信息
                    return index;
                }
            }else {
                return null;
            }
            OutputInfo.info("非法输入！");
        }
    }

    public AtkOrder(Gamer gamer) {
        this.gamer = gamer;
    }
}
