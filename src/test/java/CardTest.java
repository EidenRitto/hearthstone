import game.Gamer;
import game.card.AbstractCard;
import game.card.base.LeperGnomeCard;
import game.card.base.SmallElfCard;
import game.card.classic.druid.MarkOfNatureCard;
import game.card.base.druid.SwipeCard;
import game.card.classic.druid.NaturalizeCard;
import game.card.classic.druid.PowerOfTheWildCard;
import game.hero.Priests;
import game.hero.Warlock;
import game.objct.base.FreshwaterCrocodile;
import game.objct.base.LeperGnome;
import game.objct.base.derivative.AnimalCompanionMisa;
import game.objct.base.derivative.Panther;
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
    public void testSwipeCard(){
        Gamer gamer = initTest();
        Gamer enemy = gamer.getEnemy();

        //添加血法
        gamer.addMinion(new BloodMageThalnos());

        enemy.addMinion(new AnimalCompanionMisa());
        enemy.addMinion(new FreshwaterCrocodile());
        enemy.addMinion(new LeperGnome());
        enemy.addMinion(new LeperGnome());
        enemy.getState();
        System.out.println("===测试【血法】[横扫]，目标淡水鳄===");
        enemy.getState();


        new SwipeCard().magicEffect(gamer,enemy.getMinion(1));


        enemy.checkMinion();
        enemy.getState();
        Assert.assertEquals((long)enemy.getMinion(0).getHealth(),2);
        Assert.assertEquals(enemy.getHealth(),28);
        Assert.assertEquals(gamer.getHealth(),26);
    }

    @Test
    public void NaturalizeTest(){
        //测试自然平衡
        Gamer gamer = initTest();
        Gamer enemy = gamer.getEnemy();
        //敌方添加淡水鳄，马云
        enemy.addMinion(new FreshwaterCrocodile());
        enemy.addMinion(new LeperGnome());
        enemy.getState();
        System.out.println();
        System.out.println("===测试自然平衡，目标淡水鳄===");
        new NaturalizeCard().magicEffect(gamer,enemy.getMinion(0));
        enemy.checkMinion();
        enemy.getState();
        Assert.assertEquals(enemy.getHandsCards().size(),2);
    }

    @Test
    public void PowerOfTheWildTest() throws ClassNotFoundException {
        Class clazz = Class.forName("game.Gamer");
        //野性之力卡牌测试
        Gamer gamer = initTest();
        Gamer enemy = gamer.getEnemy();
        if (gamer.getChooseOne()==-1){
            gamer.setChooseOne(1);
        }
        new PowerOfTheWildCard().magicEffect(gamer,null);
        gamer.setChooseOne(1);
        new PowerOfTheWildCard().magicEffect(gamer,null);
        gamer.setChooseOne(1);
        new PowerOfTheWildCard().magicEffect(gamer,null);
        gamer.setChooseOne(0);
        new PowerOfTheWildCard().magicEffect(gamer,null);
        gamer.getState();
        Assert.assertEquals(gamer.getMinions().size(),3);
        Assert.assertEquals(gamer.getMinion(0).getHealthLimit(),3L);
        Assert.assertEquals(gamer.getMinion(0).getAttackValue(),4L);
    }

    @Test
    public void MarkofNatureTest(){
        //测试自然印记
        Gamer gamer = initTest();
        Gamer enemy = gamer.getEnemy();

        //召唤猎豹 * 2
        gamer.setChooseOne(1);
        new PowerOfTheWildCard().magicEffect(gamer,null);
        gamer.setChooseOne(1);
        new PowerOfTheWildCard().magicEffect(gamer,null);
        gamer.getState();
        gamer.setChooseOne(0);
        new MarkOfNatureCard().magicEffect(gamer,gamer.getMinion(0));
        gamer.setChooseOne(1);
        new MarkOfNatureCard().magicEffect(gamer,gamer.getMinion(1));
        gamer.getState();


        Assert.assertEquals(gamer.getMinion(0).getAttackValue(),7);
        Assert.assertEquals(gamer.getMinion(1).getHealthLimit(),6);
        Assert.assertTrue(gamer.getMinion(1).isTaunt());
    }


    private Gamer initTest(){
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
        return gamer;
    }
}
