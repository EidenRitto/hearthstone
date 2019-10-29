package cn.eiden.hsm.event;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.listener.HearthListener;
import lombok.Getter;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * 事件管理器
 * @author Eiden J.P Zhou
 * @date 2019/10/29 14:31
 */
public class EventManager {

    @Getter
    private ArrayList<HearthListener> registeredListeners = new ArrayList<>();
    @Getter
    private HashMap<String, ArrayList<RegisteredListenerMethod>> registeredListenerMethods = new HashMap<>();

    private Set<Class<? extends AbstractEvent>> eventClasses;

    /**所有者*/
    @Getter
    private final Gamer owner;

    public EventManager(Gamer owner) {
        this.owner = owner;
        Reflections reflections = new Reflections("game.event.events");
        eventClasses = reflections.getSubTypesOf(AbstractEvent.class);
    }

    /**
     * 注册一个事件监听器
     * @param listener 监听器
     */
    public void registerListener(HearthListener listener) {
        registeredListeners.add(listener);

        //遍历当前监听中全部的方法
        for (Method method : listener.getClass().getMethods())
        {
            if (method.getParameterCount() != 1) {continue;}

            Class<?> event = method.getParameterTypes()[0];

            //判断传入的event是否是Event的子类
            if (!AbstractEvent.class.isAssignableFrom(event)) {continue;}
            //判断当前方法是否加了EventHandler注解
            if (!method.isAnnotationPresent(EventHandler.class)) {continue;}

            // 循环全部的事件类
            // 向下注册所有子类
            for (Class<? extends AbstractEvent> eventClass : eventClasses)
            {
                //注解方法的参数事件是否是扫描包中的事件(或其子类)
                if (!event.isAssignableFrom(eventClass)) {continue;}

                String mapKey = eventClass.getName();

                if (registeredListenerMethods.containsKey(mapKey)){
                    registeredListenerMethods.get(mapKey).add(new RegisteredListenerMethod(method, listener));
                }else {
                    registeredListenerMethods.put(mapKey, new ArrayList<>(Collections.singletonList(new RegisteredListenerMethod(method, listener))));
                }
            }
        }
    }

    /**
     * 执行事件
     * @param abstractEvent 事件对象
     */
    public void call(AbstractEvent abstractEvent) {

        String mapKey = abstractEvent.getClass().getName();

        if (!registeredListenerMethods.containsKey(mapKey)) {return;}

        registeredListenerMethods.get(mapKey).forEach(registeredListenerMethod -> {
            try {
                registeredListenerMethod.call(abstractEvent);
            }
            catch (Throwable e) {
                e.printStackTrace();
            }
        });
    }
}
