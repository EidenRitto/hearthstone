package cn.eiden.hsm.game.card;

import cn.eiden.hsm.game.tags.Profession;
import org.reflections.Reflections;

import java.util.*;

/**
 * @author 周晋平
 * @date 2020/4/3 14:56
 */
public class CardFactory {
    private static final String BASE_PACKAGE_PATH = "cn.eiden.hsm.game.card";
    /**随机数种子*/
    private Random random;
    /**自身单例对象*/
    private static CardFactory cardFactory;
    /**职业-反射缓存池*/
    private Map<Profession,Set<Class<? extends Card>>> professionCardPool;

    public static CardFactory getInstance(){
        if (cardFactory==null){
            cardFactory = new CardFactory();
        }
        return cardFactory;
    }

    private CardFactory() {
        random = new Random();
        professionCardPool = new HashMap<>(16);
    }

    public Card getRandomCard(Profession profession){
        Set<Class<? extends Card>> subTypesOfCard = this.findInCache(profession);
        //从反射获取到的类中随机取一个类，由于set无序，使用迭代器，迭代随机的次数
        Class<? extends Card> next = null;
        int randomIndex = random.nextInt(subTypesOfCard.size());
        Iterator<Class<? extends Card>> iterator = subTypesOfCard.iterator();
        for (int i = 0; i < randomIndex; i++) {
            next = iterator.next();
        }
        //反射生成实例对象
        Card card = null;
        if (next != null){
            try {
                card = next.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return card;
    }

    private Set<Class<? extends Card>> findInCache(Profession profession){
        //缓存中存在则直接返回
        if (professionCardPool.containsKey(profession)){
            return professionCardPool.get(profession);
        }
        //获取某个职业所有版本的卡牌类
        String reflectionPath = BASE_PACKAGE_PATH + profession.getPackagePath();
        Reflections reflections = new Reflections(BASE_PACKAGE_PATH);

        Set<Class<? extends Card>> subTypesOfCard = reflections.getSubTypesOf(Card.class);
        //加载到反射缓存中
        professionCardPool.put(profession,subTypesOfCard);
        return subTypesOfCard;
    }

    public static void main(String[] args) {
        CardFactory instance = CardFactory.getInstance();
        for (int i = 0; i < 30; i++) {
            Card randomCard = instance.getRandomCard(Profession.Hunter);
            System.out.println(randomCard.getCardName());
        }
    }
}
