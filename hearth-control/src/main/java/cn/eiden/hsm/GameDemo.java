package cn.eiden.hsm;

import cn.eiden.hsm.cockpit.coolq.MultiConfig;
import cn.eiden.hsm.controller.TemplateController;
import cn.eiden.hsm.controller.order.Invoker;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.game.Deck;
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
    public void start(String deckStr) throws Exception {
        List<Card> mageCards = new ArrayList<>(30);
        for (int i = 0; i < 15; i++) {
            mageCards.add(CardFactory.getInstance().buildCardById(52639));
            mageCards.add(CardFactory.getInstance().buildCardById(791));
        }
        HeroMinion mageHero = new HeroMinion(CardClass.MAGE, 30, (AbstractHeroPowerCard) CardFactory.getCardById(807));
        mageHero.changeHealth(1);

        DeckSerializer deckSerializer = new DeckSerializer();
        List<Card> deck = deckSerializer.deserializeDeckString(deckStr).getDeck();
        Gamer gamer = new Gamer(new HeroMinion(CardClass.WARRIOR, 30, (AbstractHeroPowerCard) CardFactory.getCardById(58799)), deck);
        Gamer enemy = new Gamer(mageHero, mageCards);
        gameStart(gamer, enemy);
    }

    private void gameStart(Gamer gamer, Gamer enemy) {
        enemy.setEnemy(gamer);
        gamer.setEnemy(enemy);

        boolean b = gamer.newGameStart();
        Invoker gamerInvoker = new Invoker(gamer);
        Invoker enemyInvoker = new Invoker(enemy);
        TemplateController templateController = new TemplateController(gamerInvoker, enemyInvoker);
        templateController.gameStart(b);
    }

    public void multiStart(String deckStr, String deckStr2, MultiConfig multiConfig) throws Exception {
        DeckSerializer deckSerializer = new DeckSerializer();
        Deck deck = deckSerializer.deserializeDeckString(deckStr);
        Gamer gamer = Gamer.createGamer(deck);
        gamer.setPrivateMessageQueue(multiConfig.getOrganizer().getMessageQueue());
        gamer.setInputMessageQueue(multiConfig.getOrganizer().getInputQueue());
        gamer.setUserName(multiConfig.getOrganizer().getName());

        deckSerializer = new DeckSerializer();
        Deck deck2 = deckSerializer.deserializeDeckString(deckStr2);
        Gamer gamer2 = Gamer.createGamer(deck2);
        gamer2.setPrivateMessageQueue(multiConfig.getResponder().getMessageQueue());
        gamer2.setInputMessageQueue(multiConfig.getResponder().getInputQueue());
        gamer2.setUserName(multiConfig.getResponder().getName());

        gameStart(gamer,gamer2);
    }
}
