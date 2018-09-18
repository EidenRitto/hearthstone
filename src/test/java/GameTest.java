import controller.GameController;
import game.Gamer;
import game.card.AbstractCard;
import game.card.base.FreshwaterCrocodileCard;
import game.card.base.LeperGnomeCard;
import game.card.base.SmallElfCard;
import game.hero.Priests;
import game.hero.Warlock;


import java.util.ArrayList;
import java.util.List;

public class GameTest {
    public static void main(String[] args) {
        List<AbstractCard> cards = new ArrayList<>(30);
        for (int i = 0;i<10;i++){
            cards.add(new SmallElfCard());
            cards.add(new LeperGnomeCard());
            cards.add(new FreshwaterCrocodileCard());
        }

        Gamer gamer = new Gamer(new Priests(),cards);
        Gamer enemy = new Gamer(new Warlock(),cards);
        enemy.setEnemy(gamer);
        gamer.setEnemy(enemy);

        GameController.gameStart(gamer,enemy);
    }
}
