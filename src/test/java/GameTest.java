import cn.eiden.hsm.controller.GameController;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.base.FreshwaterCrocodileCard;
import cn.eiden.hsm.game.card.base.LeperGnomeCard;
import cn.eiden.hsm.game.card.base.SmallElfCard;
import cn.eiden.hsm.game.objct.hero.HeroObjectAbstract;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
    public static void main(String[] args) {
        GameTest.start();
    }

    public static void start(){
        List<Card> cards = new ArrayList<>(30);
        for (int i = 0;i<10;i++){
            cards.add(new SmallElfCard());
            cards.add(new LeperGnomeCard());
            cards.add(new FreshwaterCrocodileCard());
        }

        Gamer gamer = new Gamer(new HeroObjectAbstract(CardClass.MAGE),cards);
        Gamer enemy = new Gamer(new HeroObjectAbstract(CardClass.DRUID),cards);
        enemy.setEnemy(gamer);
        gamer.setEnemy(enemy);
        GameController gameController = new GameController(gamer,enemy);
        gameController.gameStart();
    }
}
