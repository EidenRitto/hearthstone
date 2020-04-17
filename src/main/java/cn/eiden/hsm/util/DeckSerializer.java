package cn.eiden.hsm.util;

import cn.eiden.hsm.annotation.Id;
import cn.eiden.hsm.enums.FormatType;
import cn.eiden.hsm.game.Deck;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.output.OutputInfo;
import com.google.common.base.Strings;
import org.reflections.Reflections;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 序列化工具类
 *
 * @author Eiden J.P ZHou
 * @date 2020/4/16 17:49
 */
public class DeckSerializer {
    private String demoDeckStr = "AAECAQcCrwSRvAIOHLACkQP/A44FqAXUBaQG7gbnB+8HgrACiLACub8CAA==";
    private int offset;

    public DeckSerializer() {
        demoDeckStr = "AAECAQcCrwSRvAIOHLACkQP/A44FqAXUBaQG7gbnB+8HgrACiLACub8CAA==";
        int offset = 0;
    }

    public static Deck deserialize(String input) {
        Deck deck = null;
        String[] lines = input.split("\n");
        for (String line : lines) {
            if (Strings.isNullOrEmpty(line)) {
                continue;
            }
            if (line.startsWith("#")) {

            }
        }
        return deck;
    }

    public Deck deserializeDeckString(String deckString) {
        Reflections reflections = new Reflections("cn.eiden.hsm.game.card.dynamic");
        //反射获取全部事件
        Map<Integer, String> cardDictionary = new HashMap<>(7000);
        Set<Class<? extends Card>> cardSet = reflections.getSubTypesOf(Card.class);
        for (Class<? extends Card> cardClass : cardSet) {
            Id annotation = cardClass.getAnnotation(Id.class);
            if (annotation != null) {
                cardDictionary.put(annotation.value(), annotation.name());
            }
        }


        Deck deck = new Deck();
        byte[] bytes = DatatypeConverter.parseBase64Binary(deckString);
        offset = 0;
        //Zero byte
        offset++;
        //Version - always 1
        read(bytes);
        int formatLong = (int) read(bytes);
        FormatType format = EnumUtils.getEnumObject(FormatType.class, e -> e.getCode() == formatLong).get();
        //Num Heroes - always 1
        read(bytes);

        int heroDbfId = (int) read(bytes);

        int numSingleCards = (int) read(bytes);
        for (int i = 0; i < numSingleCards; i++) {
            int dbfId = (int) read(bytes);
            OutputInfo.info(cardDictionary.get(dbfId) + "1张");
        }

        int numDoubleCards = (int) read(bytes);
        for (int i = 0; i < numDoubleCards; i++) {
            int dbfId = (int) read(bytes);
            OutputInfo.info(cardDictionary.get(dbfId) + "2张");
        }

        int numMultiCards = (int) read(bytes);
        for (int i = 0; i < numMultiCards; i++) {
            int dbfId = (int) read(bytes);
            int count = (int) read(bytes);
            OutputInfo.info(cardDictionary.get(dbfId) + count + "张");
        }
        offset = 0;
        return null;
    }

    public static void main(String[] args) {
        new DeckSerializer().deserializeDeckString("AAECAQcCrwSRvAIOHLACkQP/A44FqAXUBaQG7gbnB+8HgrACiLACub8CAA==");
    }

    public static void decodeStr(String deckStr) {
        new DeckSerializer().deserializeDeckString(deckStr);
    }

    public long read(byte[] bytes) {
        if (offset > bytes.length) {
            throw new RuntimeException("Input is not a valid deck string.");
        }
        return readNext(Arrays.copyOfRange(bytes, offset, bytes.length));
    }


    public byte[] getBytes(long value) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while (value != 0) {
            long b = value & 0x7f;
            value >>= 7;
            if (value != 0) {
                b |= 0x80;
            }
            output.write((byte) b);
        }
        return output.toByteArray();
    }

    public long readNext(byte[] bytes) {
        int length = 0;
        long result = 0;
        for (byte b : bytes) {
            long value = (long) b & 0x7f;
            result |= value << length * 7;
            if ((b & 0x80) != 0x80) {
                break;
            }
            length++;
        }
        length++;
        offset += length;
        return result;
    }
}
