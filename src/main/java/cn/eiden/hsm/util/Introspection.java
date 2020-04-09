package cn.eiden.hsm.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 内省
 *
 */
public class Introspection {
    private Introspection() {
    }

    /**
     * 获取属性的值
     * @param bean 需要内省的bean
     * @param propertyName 属性名称
     * @return
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object getPropertyValue(Object bean, String propertyName) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Method method = getReadMethod(bean, propertyName);
        Object object = method.invoke(bean);
        return object;
    }

    public static void setPropertyValue(Object bean,String propertyName,Object propertyValue) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        Method method = getWriteMethod(bean.getClass(),propertyName);
        setPropertyValue(method,bean,propertyValue);
    }

    /**
     * 获取属性的值
     * @param readMethod 该属性的get方法
     * @param bean 需要内省的bean
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object getPropertyValue(Method readMethod,Object bean) throws InvocationTargetException, IllegalAccessException {
        Object object = readMethod.invoke(bean);
        return object;
    }

    public static void setPropertyValue(Method writeMethod,Object bean,Object ...args) throws InvocationTargetException, IllegalAccessException {
        writeMethod.invoke(bean,args);
    }

    /**
     * 获取属性的get方法，如果没有与之对应的get方法就返回null
     * @param bean 需要内省的bean
     * @param propertyName 属性名称
     * @return
     * @throws IntrospectionException
     */
    public static Method getReadMethod(Object bean, String propertyName) throws IntrospectionException {

        return getReadMethod(bean.getClass(),propertyName);
    }
    /**
     * 获取属性的get方法，如果没有与之对应的get方法就返回null
     * @param bean 需要内省的bean
     * @param propertyName 属性名称
     * @return
     * @throws IntrospectionException
     */
    public static Method getReadMethod(Class<?> bean, String propertyName) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getName().equals(propertyName)) {
                Method method = propertyDescriptor.getReadMethod();
                return method;
            }
        }
        return null;
    }

    public static Method getWriteMethod(Class<?> bean,String propertyName) throws IntrospectionException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, bean);
        return propertyDescriptor.getWriteMethod();
    }

}
