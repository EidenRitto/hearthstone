package cn.eiden.hsm.event;

import cn.eiden.hsm.annotation.EventHandler;
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
    private static EventManager eventManager = new EventManager();
    public static EventManager getInstance() {
        return eventManager;
    }

    /**监听列表*/
    @Getter
    private ArrayList<HearthListener> registeredListeners = new ArrayList<>();
    /**事件字典，通过事件能找到它的全部监听*/
    @Getter
    private HashMap<String, ArrayList<RegisteredListenerMethod>> registeredListenerMethods = new HashMap<>();
    /**事件集合*/
    private Set<Class<? extends AbstractEvent>> eventClasses;


    private EventManager() {
        Reflections reflections = new Reflections("cn.eiden.hsm");
        //反射获取全部事件
        eventClasses = reflections.getSubTypesOf(AbstractEvent.class);
        //反射获取并注册监听
        Set<Class<? extends HearthListener>> listeners = reflections.getSubTypesOf(HearthListener.class);
        for (Class<? extends HearthListener> listener : listeners) {
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
     * @param listener 监听器
     */
    public void registerListener(HearthListener listener) {
        registeredListeners.add(listener);

        //遍历当前监听中全部的方法
        for (Method method : listener.getClass().getMethods())
        {
            //该方法只能有一个参数
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
