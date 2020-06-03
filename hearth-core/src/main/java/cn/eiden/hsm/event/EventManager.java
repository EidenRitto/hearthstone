package cn.eiden.hsm.event;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.minion.Secret;
import cn.eiden.hsm.game.rule.Rule;
import cn.eiden.hsm.listener.HearthListener;
import lombok.Getter;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 事件管理器
 *
 * @author Eiden J.P Zhou
 * @date 2019/10/29 14:31
 */
public class EventManager {
    private static EventManager eventManager = new EventManager();

    public static EventManager getInstance() {
        return eventManager;
    }

    /**
     * 监听列表
     */
    @Getter
    private ArrayList<HearthListener> registeredListeners = new ArrayList<>();
    /**
     * 事件字典，通过事件能找到它的全部监听
     */
    @Getter
    private HashMap<String, ArrayList<RegisteredListenerMethod>> registeredListenerMethods = new HashMap<>();


    private HashMap<String, ArrayList<Secret>> secretListeners = new HashMap<>();

    /**
     * 事件集合
     */
    private Set<Class<? extends Event>> eventClasses;


    private EventManager() {
        Reflections reflections = new Reflections("cn.eiden.hsm");
        //反射获取全部事件
        eventClasses = reflections.getSubTypesOf(Event.class);
        //反射获取并注册监听
        Set<Class<? extends HearthListener>> listeners = reflections.getSubTypesOf(HearthListener.class);
        for (Class<? extends HearthListener> listener : listeners) {
            if (listener.isMemberClass()) {
                continue;
            }
            try {
                HearthListener hearthListener = listener.newInstance();
                registerListener(hearthListener);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注册一个事件监听器<br/>
     * 并绑定监听和事件
     *
     * @param listener 监听器
     */
    public void registerListener(HearthListener listener) {
        registeredListeners.add(listener);

        //遍历当前监听中全部的方法
        for (Method method : listener.getClass().getMethods()) {
            //该方法只能有一个参数
            if (method.getParameterCount() != 1) {
                continue;
            }

            Class<?> event = method.getParameterTypes()[0];

            //判断传入的event是否是Event的子类
            if (!Event.class.isAssignableFrom(event)) {
                continue;
            }
            //判断当前方法是否加了EventHandler注解
            if (!method.isAnnotationPresent(EventHandler.class)) {
                continue;
            }

            // 循环全部的事件类
            // 向下注册所有子类
            for (Class<? extends Event> eventClass : eventClasses) {
                //注解方法的参数事件是否是扫描包中的事件(或其子类)
                if (!event.isAssignableFrom(eventClass)) {
                    continue;
                }

                String mapKey = eventClass.getName();

                if (registeredListenerMethods.containsKey(mapKey)) {
                    registeredListenerMethods.get(mapKey).add(new RegisteredListenerMethod(method, listener));
                } else {
                    registeredListenerMethods.put(mapKey, new ArrayList<>(Collections.singletonList(new RegisteredListenerMethod(method, listener))));
                }
            }
        }
    }

    public void registerSecret(Secret secret) {
        Class<? extends Event> triggerEvent = secret.triggerEvent();
        String mapKey = triggerEvent.getName();
        if (secretListeners.containsKey(mapKey)) {
            secretListeners.get(mapKey).add(secret);
        } else {
            secretListeners.put(mapKey, new ArrayList<>(Collections.singletonList(secret)));
        }
    }

    /**
     * 注销事件监听器</br>
     * 并解除事件监听绑定
     *
     * @param listener 需要被注销的监听
     */
    public void removeListener(final HearthListener listener) {
        registeredListeners.remove(listener);
        //遍历当前监听中全部的方法
        for (Method method : listener.getClass().getMethods()) {
            Class<?> event = method.getParameterTypes()[0];
            //判断传入的event是否是Event的子类
            if (!Event.class.isAssignableFrom(event)) {
                continue;
            }
            for (Class<? extends Event> eventClass : eventClasses) {
                //注解方法的参数事件是否是扫描包中的事件(或其子类)
                if (!event.isAssignableFrom(eventClass)) {
                    continue;
                }
                ArrayList<RegisteredListenerMethod> registeredListenerMethods = this.registeredListenerMethods.get(eventClass.getName());
                registeredListenerMethods.removeIf(e -> e.getListener().equals(listener));
            }
        }
    }

    /**
     * 执行事件
     *
     * @param event 事件对象
     */
    public void call(final Event event) {
        if (event instanceof AbstractEvent) {
            AbstractEvent abstractEvent = (AbstractEvent) event;
            final Gamer owner = abstractEvent.getOwner();
            // TODO: 2020/6/3 所有的规则变动应提取到接口中
            //重置规则cost
            owner.getHand().getCards().forEach(Card::resetRuleForceCost);
            //重置规则免疫
            owner.getHero().removeImmune();

            List<Rule> rules = owner.getRules();
            rules.removeIf(e -> e.leave(event.getClass()));
            owner.refreshRuleEffect();
        }

        String mapKey = event.getClass().getName();

        if (registeredListenerMethods.containsKey(mapKey)) {
            registeredListenerMethods.get(mapKey).forEach(registeredListenerMethod -> {
                try {
                    registeredListenerMethod.call(event);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            });
        }

        if (secretListeners.containsKey(mapKey)) {
            ArrayList<Secret> secrets = this.secretListeners.get(mapKey);
            for (Secret secret : secrets) {
                if (!secret.getOwner().isActive()){
                    secret.getOwner().onSecret(secret, event);
                }
            }
        }
    }
}
