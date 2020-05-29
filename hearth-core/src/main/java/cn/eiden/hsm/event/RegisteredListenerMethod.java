package cn.eiden.hsm.event;

import cn.eiden.hsm.listener.HearthListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 事件监听绑定类
 * @author Eiden J.P Zhou
 * @date 2019/10/29 14:56
 */
@Data
@AllArgsConstructor
public class RegisteredListenerMethod {
    private Method method;
    private HearthListener listener;

    /**
     * 反射执行监听方法
     * @param abstractEvent 事件
     * @throws InvocationTargetException 反射失败
     * @throws IllegalAccessException 无访问权限 (不可能发生)
     */
    public void call(Event abstractEvent) throws InvocationTargetException, IllegalAccessException
    {
        method.setAccessible(true);
        method.invoke(listener, abstractEvent);
    }
}
