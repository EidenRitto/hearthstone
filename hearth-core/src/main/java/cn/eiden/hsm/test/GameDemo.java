package cn.eiden.hsm.test;

import cn.eiden.hsm.controller.TemplateController;
import cn.eiden.hsm.controller.order.Invoker;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractHeroPowerCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.minion.hero.HeroMinion;
import cn.eiden.hsm.util.DeckSerializer;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/1 12:55
 */
public class GameDemo {
    public void start(String deckStr)throws Exception{
        List<Card> druidCards = new ArrayList<>(30);
        for (int i = 0;i<30;i++){
            druidCards.add(CardFactory.getInstance().buildCardById(585));
        }

        DeckSerializer deckSerializer = new DeckSerializer();
        List<Card> deck = deckSerializer.deserializeDeckString(deckStr).getDeck();
        Gamer gamer = new Gamer(new HeroMinion(CardClass.WARRIOR, (AbstractHeroPowerCard) CardFactory.getCardById(58799)),deck);
        Gamer enemy = new Gamer(new HeroMinion(CardClass.MAGE, (AbstractHeroPowerCard) CardFactory.getCardById(807)),druidCards);
        enemy.setEnemy(gamer);
        gamer.setEnemy(enemy);

        boolean b = gamer.newGameStart();
        Invoker gamerInvoker = new Invoker(gamer);
        Invoker enemyInvoker = new Invoker(enemy);
        TemplateController templateController = new TemplateController(gamerInvoker,enemyInvoker);
        templateController.gameStart(b);
    }
}
