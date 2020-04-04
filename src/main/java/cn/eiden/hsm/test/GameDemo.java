package cn.eiden.hsm.test;

import cn.eiden.hsm.controller.GameController;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.base.FreshwaterCrocodileCard;
import cn.eiden.hsm.game.card.base.LeperGnomeCard;
import cn.eiden.hsm.game.card.base.SmallElfCard;
import cn.eiden.hsm.game.hero.HeroObjectAbstract;
import cn.eiden.hsm.game.tags.Profession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周晋平
 * @date 2020/4/1 12:55
 */
public class GameDemo {
    public void start(){
        List<Card> druidCards = new ArrayList<>(30);
        for (int i = 0;i<30;i++){
            druidCards.add(CardFactory.getInstance().getRandomCard(Profession.Druid));
        }

        List<Card> hunterCards = new ArrayList<>(30);
        for (int i = 0;i<30;i++){
            hunterCards.add(CardFactory.getInstance().getRandomCard(Profession.Hunter));
        }

        Gamer gamer = new Gamer(new HeroObjectAbstract(Profession.Hunter),hunterCards);
        Gamer enemy = new Gamer(new HeroObjectAbstract(Profession.Druid),druidCards);
        enemy.setEnemy(gamer);
        gamer.setEnemy(enemy);

        GameController gameController = new GameController(gamer,enemy);
        gameController.gameStart();
    }
}
