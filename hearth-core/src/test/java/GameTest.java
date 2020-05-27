import cn.eiden.hsm.controller.GameController;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.minion.hero.HeroMinion;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
    public static void main(String[] args) {
        GameTest.start();
    }

    public static void start(){
        List<Card> cards = new ArrayList<>(30);
        for (int i = 0;i<10;i++){
            cards.add(null);
        }

        Gamer gamer = new Gamer(new HeroMinion(CardClass.MAGE),cards);
        Gamer enemy = new Gamer(new HeroMinion(CardClass.DRUID),cards);
        enemy.setEnemy(gamer);
        gamer.setEnemy(enemy);
        GameController gameController = new GameController(gamer,enemy);
        gameController.gameStart();
    }
}
