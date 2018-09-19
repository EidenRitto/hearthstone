import game.Gamer;
import game.card.AbstractCard;
import game.card.base.LeperGnomeCard;
import game.card.base.SmallElfCard;
import game.card.base.SwipeCard;
import game.hero.Priests;
import game.hero.Warlock;
import game.objct.base.FreshwaterCrocodile;
import game.objct.base.LeperGnome;
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

        enemy.addServant(new FreshwaterCrocodile());
        enemy.addServant(new FreshwaterCrocodile());
        enemy.addServant(new LeperGnome());
        enemy.addServant(new LeperGnome());

        enemy.getState();

        System.out.println("测试横扫，目标淡水鳄");
        new SwipeCard().magicEffect(gamer,enemy.getServant(1));

        enemy.getState();
        enemy.checkServant();
        enemy.getState();
        Assert.assertEquals((long)enemy.getServant(0).getHealth(),2);
        Assert.assertEquals(enemy.getHealth(),29);
        Assert.assertEquals(gamer.getHealth(),26);
    }

}
