import controller.GameController;
import game.Gamer;
import game.card.AbstractCard;
import game.card.base.FreshwaterCrocodileCard;
import game.card.base.LeperGnomeCard;
import game.card.base.SmallElfCard;
import game.hero.HeroObject;
import game.hero.Profession;


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

        Gamer gamer = new Gamer(new HeroObject(Profession.Mega),cards);
        Gamer enemy = new Gamer(new HeroObject(Profession.Druid),cards);
        enemy.setEnemy(gamer);
        gamer.setEnemy(enemy);

        GameController.gameStart(gamer,enemy);
    }
}
