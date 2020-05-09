package cn.eiden.hsm.test;

import cn.eiden.hsm.controller.GameController;
import cn.eiden.hsm.controller.TemplateController;
import cn.eiden.hsm.controller.order.Invoker;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.objct.hero.HeroObjectAbstract;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/1 12:55
 */
public class GameDemo {
    public void start(){
        List<Card> druidCards = new ArrayList<>(30);
        for (int i = 0;i<30;i++){
            druidCards.add(CardFactory.getInstance().getRandomCard(CardClass.DRUID));
        }

        List<Card> hunterCards = new ArrayList<>(30);
        for (int i = 0;i<30;i++){
            hunterCards.add(CardFactory.getInstance().getRandomCard(CardClass.HUNTER));
        }

        Gamer gamer = new Gamer(new HeroObjectAbstract(CardClass.HUNTER),hunterCards);
        Gamer enemy = new Gamer(new HeroObjectAbstract(CardClass.DRUID),druidCards);
        enemy.setEnemy(gamer);
        gamer.setEnemy(enemy);

        boolean b = gamer.newGameStart();
        Invoker gamerInvoker = new Invoker(gamer);
        Invoker enemyInvoker = new Invoker(enemy);
        TemplateController templateController = new TemplateController(gamerInvoker,enemyInvoker);
        templateController.gameStart(b);
    }
}
