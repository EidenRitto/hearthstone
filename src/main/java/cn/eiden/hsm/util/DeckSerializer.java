package cn.eiden.hsm.util;

import cn.eiden.hsm.game.Deck;
import com.google.common.base.Strings;

import javax.xml.bind.DatatypeConverter;

/**
 * 序列化工具类
 * @author Eiden J.P ZHou
 * @date 2020/4/16 17:49
 */
public class DeckSerializer {
    private static final String DEMO_DECK_STR = "AAECAQcCrwSRvAIOHLACkQP/A44FqAXUBaQG7gbnB+8HgrACiLACub8CAA==";


    public static Deck deserialize(String input){
        Deck deck = null;
        String[] lines = input.split("\n");
        for (String line : lines) {
            if (Strings.isNullOrEmpty(line)){
                continue;
            }
            if (line.startsWith("#")){

            }
        }
        return deck;
    }

    public static Deck deserializeDeckString(String deckString){
        Deck deck = new Deck();
        byte[] bytes = DatatypeConverter.parseBase64Binary(deckString);
        int offset = 0;


        return null;
    }
}
