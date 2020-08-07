package cn.eiden.hsm.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 * 随机工具
 * @author Eiden J.P Zhou
 * @date 2020/8/6 16:04
 */
public class RandomUtils {
    private static Random random = new Random();

    public static <T> T getRandomOne(Collection<T> collection){
        int size = collection.size();
        Iterator<T> iterator = collection.iterator();
        int times = random.nextInt(size);
        if (iterator.hasNext()){
            T one = iterator.next();
            for (int i = 0; i < times; i++) {
                one = iterator.next();
            }
            return one;
        }else {
            return null;
        }
    }
}
