package cn.eiden.hsm.game.card;

import cn.eiden.hsm.annotation.Id;
import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.*;
import cn.eiden.hsm.util.EnumUtils;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/3 14:56
 */
@Slf4j
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

    private Map<Integer, Set<Integer>> cardCostPool = new HashMap<>(16);
    private Map<CardSet, Set<Integer>> cardSetPool = new HashMap<>(32);
    private Map<CardClass, Set<Integer>> cardClassPool = new HashMap<>(16);
    private Map<CardType, Set<Integer>> cardTypePool = new HashMap<>(8);
    private Map<Race, Set<Integer>> cardRacePool = new HashMap<>(32);
    private Map<GameTag, Set<Integer>> cardGameTagPool = new HashMap<>(32);
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
        cardPool = new HashMap<>(7000);
        this.initCache();
    }

    private void initCache() {

        //获取某个包中所有版本的卡牌类
        Reflections reflections = new Reflections(BASE_PACKAGE_PATH);

        Set<Class<? extends Card>> subTypesOfCard = reflections.getSubTypesOf(Card.class);
        for (Class<? extends Card> cardClass : subTypesOfCard) {
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
                this.initDifferentKindsPool(id.value(), cardClass);
            }
        }
    }

    private void initDifferentKindsPool(int id, Class<? extends Card> cardClass) {
        try {
            Field costField = cardClass.getDeclaredField("COST");
            costField.setAccessible(true);
            int cost = costField.getInt(cardClass);
            this.addInPool(cardCostPool, cost, id);

            Field setField = cardClass.getDeclaredField("CARD_SET");
            setField.setAccessible(true);
            CardSet cardSet = (CardSet) setField.get(cardClass);
            this.addInPool(cardSetPool, cardSet, id);

            Field classField = cardClass.getDeclaredField("CARD_CLASS");
            classField.setAccessible(true);
            CardClass cardClass1 = (CardClass) classField.get(cardClass);
            this.addInPool(cardClassPool, cardClass1, id);

            Field typeField = cardClass.getDeclaredField("CARD_TYPE");
            typeField.setAccessible(true);
            CardType cardType = (CardType) typeField.get(cardClass);
            this.addInPool(cardTypePool, cardType, id);

            if (cardType == CardType.MINION) {
                Field raceField = cardClass.getDeclaredField("RACE");
                raceField.setAccessible(true);
                Race race = (Race) raceField.get(cardClass);
                this.addInPool(cardRacePool, race, id);
            }

            //创建对应的标签缓存池
            Tags tags = cardClass.getAnnotation(Tags.class);
            int[] value = tags.value();
            Arrays.stream(value)
                    .boxed()
                    .map(e -> EnumUtils.getEnumObject(GameTag.class, f -> f.getCode() == e))
                    .filter(Objects::nonNull)
                    .map(Optional::get)
                    .forEach(e->addInPool(cardGameTagPool, e, id));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.warn(String.format("%s找不到属性", cardClass.getSimpleName()));
        }
    }

    private <T> void addInPool(Map<T, Set<Integer>> pool, T val, Integer id) {
        if (pool.containsKey(val)) {
            pool.get(val).add(id);
        } else {
            pool.put(val, new HashSet<Integer>() {{
                add(id);
            }});
        }
    }

    public Card buildCardById(Integer id) throws Exception {
        Class<? extends Card> aClass = cardPool.get(id);
        if (aClass == null) {
            String format = String.format("找不到id为%s的卡牌", id);
            throw new NullPointerException(format);
        }
        return aClass.newInstance();
    }

    public static Card getCardById(int id) {
        try {
            return getInstance().buildCardById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CardFactory.CardBuilder buildCard() {
        return new CardFactory.CardBuilder();
    }

    /**
     * 使用建造者对象进行条件查询<br/>
     * retainAll set集合取交集
     *
     * @param builder 建造者对象
     * @return 条件查询结果集合
     */
    private static Set<Integer> condition(CardBuilder builder) {
        Set<Integer> result = new HashSet<>(getInstance().cardPool.keySet());
        if (builder.cost.size() > 0) {
            Set<Integer> idSet = new HashSet<>();
            for (Integer cardCost : builder.cost) {
                idSet.addAll(getInstance().cardCostPool.get(cardCost));
            }
            result.retainAll(idSet);
        }
        if (builder.cardSet.size() > 0) {
            Set<Integer> idSet = new HashSet<>();
            for (CardSet cardSet : builder.cardSet) {
                idSet.addAll(getInstance().cardSetPool.get(cardSet));
            }
            result.retainAll(idSet);
        }
        if (builder.cardClass.size() > 0) {
            Set<Integer> idSet = new HashSet<>();
            for (CardClass cardClass : builder.cardClass) {
                idSet.addAll(getInstance().cardClassPool.get(cardClass));
            }
            result.retainAll(idSet);
        }
        if (builder.cardType.size() > 0) {
            Set<Integer> idSet = new HashSet<>();
            for (CardType cardType : builder.cardType) {
                idSet.addAll(getInstance().cardTypePool.get(cardType));
            }
            result.retainAll(idSet);
        }
        if (builder.race.size() > 0) {
            Set<Integer> idSet = new HashSet<>();
            for (Race race : builder.race) {
                idSet.addAll(getInstance().cardRacePool.get(race));
            }
            result.retainAll(idSet);
        }
        if (builder.gameTags.size() > 0) {
            Set<Integer> idSet = new HashSet<>();
            for (GameTag gameTag : builder.gameTags) {
                idSet.addAll(getInstance().cardGameTagPool.get(gameTag));
            }
            result.retainAll(idSet);
        }

        return result;
    }

    public static class CardBuilder {
        private Set<Integer> cost;
        private Set<CardSet> cardSet;
        private Set<CardClass> cardClass;
        private Set<CardType> cardType;
        private Set<Race> race;
        private Set<GameTag> gameTags;
        private Random random;

        public CardBuilder() {
            random = new Random();
            cost = new HashSet<>(16);
            cardSet = new HashSet<>(16);
            cardClass = new HashSet<>(16);
            cardType = new HashSet<>(16);
            race = new HashSet<>(16);
            gameTags = new HashSet<>(16);
        }

        public CardFactory.CardBuilder cost(Integer cost) {
            this.cost.add(cost);
            return this;
        }

        public CardFactory.CardBuilder cardSet(CardSet cardSet) {
            this.cardSet.add(cardSet);
            return this;
        }

        public CardFactory.CardBuilder cardClass(CardClass cardClass) {
            this.cardClass.add(cardClass);
            return this;
        }

        public CardFactory.CardBuilder cardType(CardType cardType) {
            this.cardType.add(cardType);
            return this;
        }

        public CardFactory.CardBuilder race(Race race) {
            this.race.add(race);
            return this;
        }

        public CardFactory.CardBuilder gameTags(GameTag gameTag){
            this.gameTags.add(gameTag);
            return this;
        }

        public Card randomBuild() {
            Set<Integer> condition = condition(this);
            Integer randomCardId = getRandomCardId(condition);
            return getCardById(randomCardId);
        }

        private Integer getRandomCardId(Set<Integer> condition) {
            Iterator<Integer> iterator = condition.iterator();
            int target = random.nextInt(condition.size());
            Integer index = null;
            for (int i = 0; i < target; i++) {
                index = iterator.next();
            }
            return index;
        }

        public List<Card> discover(){
            List<Card> result = new ArrayList<>();
            Set<Integer> condition = condition(this);
            for (int i = 0; i < 3; i++) {
                if (condition.size() == 0){
                    break;
                }
                Integer randomCardId = getRandomCardId(condition);
                condition.remove(randomCardId);
                result.add(getCardById(randomCardId));
            }
            return result;
        }
    }
}
