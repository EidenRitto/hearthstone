import cn.eiden.hsm.controller.GameController;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractCard;
import cn.eiden.hsm.game.card.base.FreshwaterCrocodileCard;
import cn.eiden.hsm.game.card.base.LeperGnomeCard;
import cn.eiden.hsm.game.card.base.SmallElfCard;
import cn.eiden.hsm.game.hero.HeroObjectAbstract;
import cn.eiden.hsm.game.hero.Profession;


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

        Gamer gamer = new Gamer(new HeroObjectAbstract(Profession.Mega),cards);
        Gamer enemy = new Gamer(new HeroObjectAbstract(Profession.Druid),cards);
        enemy.setEnemy(gamer);
        gamer.setEnemy(enemy);

        GameController.gameStart(gamer,enemy);
    }
}
