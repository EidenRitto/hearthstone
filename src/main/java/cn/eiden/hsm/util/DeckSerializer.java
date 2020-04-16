package cn.eiden.hsm.util;

import cn.eiden.hsm.enums.FormatType;
import cn.eiden.hsm.game.Deck;
import com.google.common.base.Strings;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 序列化工具类
 * @author Eiden J.P ZHou
 * @date 2020/4/16 17:49
 */
public class DeckSerializer {
    private static final String DEMO_DECK_STR = "AAECAQcCrwSRvAIOHLACkQP/A44FqAXUBaQG7gbnB+8HgrACiLACub8CAA==";
    private static int offset = 0;

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
        offset = 0;
        //Zero byte
        offset++;
        //Version - always 1
        read(bytes);
        int formatLong = (int) read(bytes);
        FormatType format = EnumUtils.getEnumObject(FormatType.class,e->e.getCode() == formatLong).get();
        //Num Heroes - always 1
        read(bytes);

        int heroDbfId = (int) read(bytes);

        int numSingleCards = (int) read(bytes);
        for (int i = 0; i < numSingleCards; i++) {
            int dbfId = (int) read(bytes);
            System.out.println(dbfId);
        }
        return null;
    }

    public static void main(String[] args) {
        deserializeDeckString(DEMO_DECK_STR);
    }

    public static void addCard(){

    }

    public static long read(byte[] bytes){
        if(offset > bytes.length){
            throw new RuntimeException("Input is not a valid deck string.");
        }
        return readNext(Arrays.copyOfRange(bytes,offset,bytes.length));
    }


    public static byte[] getBytes(long value){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while(value != 0)
        {
            long b = value & 0x7f;
            value >>= 7;
            if(value != 0) {
                b |= 0x80;
            }
            output.write((byte)b);
        }
        return output.toByteArray();
    }

    public static long readNext(byte[] bytes){
        int length = 0;
        long result = 0;
        for (byte b : bytes) {
            long value = (long)b & 0x7f;
            result |= value << length * 7;
            if((b & 0x80) != 0x80) {
                break;
            }
            length++;
        }
        length++;
        offset += length;
        return result;
    }
}
