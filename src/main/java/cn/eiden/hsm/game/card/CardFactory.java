package cn.eiden.hsm.game.card;

import cn.eiden.hsm.annotation.Id;
import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.CardClass;
import org.reflections.Reflections;

import java.util.*;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/3 14:56
 */
public class CardFactory {
    private static final String BASE_PACKAGE_PATH = "cn.eiden.hsm.game.card";
    /**
     * 随机数种子
     */
    private Random random;
    /**
     * 自身单例对象
     */
    private static CardFactory cardFactory;
    /**
     * 职业-反射缓存池
     */
    private Map<CardClass, Set<Class<? extends Card>>> professionCardPool;
    /**
     * id卡牌池
     */
    private Map<Integer, Class<? extends Card>> cardPool;

    public static CardFactory getInstance() {
        if (cardFactory == null) {
            cardFactory = new CardFactory();
        }
        return cardFactory;
    }

    private CardFactory() {
        random = new Random();
        professionCardPool = new HashMap<>(16);
        cardPool = new HashMap<>(7000);
        this.initCache();
    }

    public Card getRandomCard(CardClass profession) {
        Set<Class<? extends Card>> subTypesOfCard = this.findInCache(profession);
        //从反射获取到的类中随机取一个类，由于set无序，使用迭代器，迭代随机的次数
        Class<? extends Card> next = null;
        int randomIndex = random.nextInt(subTypesOfCard.size());
        Iterator<Class<? extends Card>> iterator = subTypesOfCard.iterator();
        for (int i = 0; i < randomIndex + 1; i++) {
            next = iterator.next();
        }
        //反射生成实例对象
        Card card = null;
        if (next != null) {
            try {
                card = next.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return card;
    }

    private Set<Class<? extends Card>> findInCache(CardClass profession) {
        //缓存中存在则直接返回
        return professionCardPool.get(profession);
    }

    private void initCache() {
        //初始化职业卡牌缓存池
        for (CardClass profession : CardClass.values()) {
            Set<Class<? extends Card>> professionSet = new HashSet<>();
            professionCardPool.put(profession, professionSet);
        }
        //获取某个包中所有版本的卡牌类
        Reflections reflections = new Reflections(BASE_PACKAGE_PATH);

        Set<Class<? extends Card>> subTypesOfCard = reflections.getSubTypesOf(Card.class);
        for (Class<? extends Card> cardClass : subTypesOfCard) {
            Tags annotation = cardClass.getAnnotation(Tags.class);
            if (annotation != null) {
                //存入缓存池
                professionCardPool.get(annotation.cardClass()).add(cardClass);
            }
            Id id = cardClass.getAnnotation(Id.class);
            if (id != null) {
                if (cardPool.containsKey(id.value())) {
                    Class<? extends Card> aClass = cardPool.get(id.value());
                    //如果缓存池中存在父类则用子类覆盖，否则跳过(什么也不做)
                    if (aClass.isAssignableFrom(cardClass)) {
                        cardPool.put(id.value(), cardClass);
                    }
                } else {
                    cardPool.put(id.value(), cardClass);
                }
            }
        }
    }

    public Card buildCardById(Integer id) throws Exception {
        Class<? extends Card> aClass = cardPool.get(id);
        return aClass.newInstance();
    }

    public static Card getCardById(int id){
        try {
            return getInstance().buildCardById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
