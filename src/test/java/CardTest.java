import game.Gamer;
import game.card.AbstractCard;
import game.card.base.LeperGnomeCard;
import game.card.base.SmallElfCard;
import game.card.base.druid.SwipeCard;
import game.hero.Priests;
import game.hero.Warlock;
import game.objct.base.FreshwaterCrocodile;
import game.objct.base.LeperGnome;
import game.objct.classic.BloodMageThalnos;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/19
 * @Description:
 * @Modified By: 卡牌测试
 */
public class CardTest {
    @Test
    public void testCard(){
        List<AbstractCard> cards = new ArrayList<>(30);
        for (int i = 0;i<10;i++){
            cards.add(new SmallElfCard());
            cards.add(new LeperGnomeCard());
            cards.add(new SwipeCard());
        }

        Gamer gamer = new Gamer(new Priests(),cards);
        Gamer enemy = new Gamer(new Warlock(),cards);
        enemy.setEnemy(gamer);
        gamer.setEnemy(enemy);

        //添加血法
        gamer.addMinion(new BloodMageThalnos());

        enemy.addMinion(new FreshwaterCrocodile());
        enemy.addMinion(new FreshwaterCrocodile());
        enemy.addMinion(new LeperGnome());
        enemy.addMinion(new LeperGnome());

        enemy.getState();

        System.out.println("===测试【血法】横扫，目标2号淡水鳄===");
        new SwipeCard().magicEffect(gamer,enemy.getMinion(1));

        enemy.getState();
        enemy.checkMinion();
        enemy.getState();
        Assert.assertEquals((long)enemy.getMinion(0).getHealth(),1);
        Assert.assertEquals(enemy.getHealth(),28);
        Assert.assertEquals(gamer.getHealth(),26);
    }

}
