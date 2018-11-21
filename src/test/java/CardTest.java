import game.Gamer;
import game.card.AbstractCard;
import game.card.base.LeperGnomeCard;
import game.card.base.SmallElfCard;
import game.card.base.hunter.HoundMasterCard;
import game.card.base.hunter.KillCommandCard;
import game.card.base.hunter.MultiShotCard;
import game.card.classic.druid.MarkOfNatureCard;
import game.card.base.druid.SwipeCard;
import game.card.classic.druid.NaturalizeCard;
import game.card.classic.druid.PowerOfTheWildCard;
import game.hero.HeroObject;
import game.hero.Profession;
import game.objct.minion.base.FreshwaterCrocodile;
import game.objct.minion.base.LeperGnome;
import game.objct.minion.base.derivative.AnimalCompanionMisa;
import game.objct.minion.base.derivative.AnimalCompanionReoque;
import game.objct.minion.base.druid.IronbarkProtector;
import game.objct.minion.base.hunter.TimberWolf;
import game.objct.minion.classic.BloodMageThalnos;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/19
 * @Description:
 * @Modified By: 卡牌测试
 */
public class CardTest {

    @Test
    public void testHoundMaster(){
        System.out.println("随从驯兽师测试");
        Gamer gamer = initTest();
        Gamer enemy = gamer.getEnemy();
        gamer.addMinion(new FreshwaterCrocodile());
        gamer.getState();
        gamer.addHandsCard(new HoundMasterCard());
        gamer.useThisMinionCard(0,gamer.getMinion(0));
        gamer.getState();
        Assert.assertEquals(gamer.getMinion(0).getAttackValue(),4L);
        Assert.assertEquals(gamer.getMinion(0).getHealth(),5L);
    }

    @Test
    public void testMultiShotCard(){
        System.out.println("卡牌多重射击测试");
        Gamer gamer = initTest();
        Gamer enemy = gamer.getEnemy();
        enemy.addMinion(new IronbarkProtector());
        enemy.addMinion(new IronbarkProtector());
        enemy.addMinion(new IronbarkProtector());
        enemy.addMinion(new IronbarkProtector());
        new MultiShotCard().magicEffect(gamer,null);
        enemy.getState();
    }

    @Test
    public void testKillCommandCard(){
        System.out.println("卡牌杀戮命令测试");
        Gamer gamer = initTest();
        Gamer enemy = gamer.getEnemy();
        enemy.addMinion(new IronbarkProtector());
        enemy.addMinion(new IronbarkProtector());
        new KillCommandCard().magicEffect(gamer,enemy.getMinion(0));
        Assert.assertEquals(enemy.getMinion(0).getHealth(),5L);
        gamer.addMinion(new FreshwaterCrocodile());
        new KillCommandCard().magicEffect(gamer,enemy.getMinion(1));
        Assert.assertEquals(enemy.getMinion(1).getHealth(),3L);
    }

    @Test
    public void testBuffHole(){
        System.out.println("光环效果测试");
        //初始化测试环境
        Gamer gamer = initTest();
        Gamer enemy = gamer.getEnemy();
        //添加随从
        gamer.addMinion(new TimberWolf());
        gamer.addMinion(new TimberWolf());
        gamer.addMinion(new LeperGnome());
        gamer.addMinion(new FreshwaterCrocodile());
        gamer.addMinion(new AnimalCompanionReoque());
        gamer.getState();

        Assert.assertEquals(gamer.getMinion(0).getAttackValue(),3L);
        Assert.assertEquals(gamer.getMinion(2).getAttackValue(),2L);
        Assert.assertEquals(gamer.getMinion(3).getAttackValue(),5L);
        System.out.println("测试通过");
    }

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
        Assert.assertEquals(enemy.getHero().getHealth(),28);
        Assert.assertEquals(gamer.getHero().getHealth(),26);
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

        Gamer gamer = new Gamer(new HeroObject(Profession.Priest),cards);
        Gamer enemy = new Gamer(new HeroObject(Profession.Warlock),cards);
        enemy.setEnemy(gamer);
        gamer.setEnemy(enemy);
        return gamer;
    }
}
