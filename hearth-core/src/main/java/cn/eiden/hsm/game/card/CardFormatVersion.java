package cn.eiden.hsm.game.card;

import cn.eiden.hsm.enums.CardSet;

import java.util.HashSet;
import java.util.Set;

/**
 * 标准和狂野包集合
 * @author Eiden J.P Zhou
 * @date 2020/6/9 11:50
 */
public class CardFormatVersion {
    public static Set<CardSet> standard = new HashSet<CardSet>(){{
        add(CardSet.CORE);
        add(CardSet.EXPERT1);
        add(CardSet.DALARAN);
        add(CardSet.ULDUM);
        add(CardSet.DRAGONS);
        add(CardSet.YEAR_OF_THE_DRAGON);
        add(CardSet.BLACK_TEMPLE);
    }};

    public static Set<CardSet> wild = new HashSet<CardSet>(){{
        addAll(standard);
        add(CardSet.NAXX);
        add(CardSet.GVG);
        add(CardSet.BRM);
        add(CardSet.TGT);
        add(CardSet.LOE);
        add(CardSet.OG);
        add(CardSet.KARA);
        add(CardSet.GANGS);
        add(CardSet.UNGORO);
        add(CardSet.ICECROWN);
        add(CardSet.LOOTAPALOOZA);
        add(CardSet.GILNEAS);
        add(CardSet.BOOMSDAY);
        add(CardSet.TROLL);
    }};
}
