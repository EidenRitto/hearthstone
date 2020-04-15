package cn.eiden.hsm.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * JavaBean工具类
 * @author Eiden J.P Zhou
 * @date 2019/7/10 14:09
 */
@Slf4j
public class JavaBeansUtil {
    /**
     * 获取驼峰命名字符串
     * @param inputString 原输入字符串
     * @param firstCharacterUppercase 第一个字符是否大写,属性驼峰命名传入false
     * @return 符合驼峰命名规范的字符串
     */
    public static String getCamelCaseString(String inputString,
                                            boolean firstCharacterUppercase) {
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            switch (c) {
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                case '/':
                case '&':
                    if (sb.length() > 0) {
                        nextUpperCase = true;
                    }
                    break;

                default:
                    if (nextUpperCase) {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
                    break;
            }
        }

        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        return sb.toString();
    }

    /**
     * 将map转换为对象，map中的key应该为数据库中的字段（Oracle全大写）<br/>
     * 需要将map中的key转换为驼峰命名<br/>
     * 所以转换后的命名需要与目标类型中的字段一一对应，否则转换失败
     * @param map 源map
     * @param destinationClass 目标Class类型
     * @param <E> 目标类型
     * @return 目标类型的一个实例对象，值从map对应的key中获取,出错返回null
     */
    public static <E> E transMapToObject(Map map,Class<E> destinationClass){
        E entity = null;
        try {
            entity = destinationClass.newInstance();
            Set set = map.entrySet();
            for (Object o : set) {
                if (o instanceof Map.Entry){
                    Map.Entry entry = (Map.Entry) o;
                    String key = (String) entry.getKey();
                    Object value = entry.getValue();
                    String camelCaseKey = getCamelCaseString(key,false);
                    Method writeMethod = Introspection.getWriteMethod(destinationClass,camelCaseKey);
                    writeMethod.invoke(entity,value);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return entity;
    }

    //带泛型参数的方法，由于旧的接口大都不带泛型，故废弃
//    public static <E> E transMapToObject(Map<String,Object> map,Class<E> destinationClass){
//        E entity = null;
//        try {
//            entity = destinationClass.newInstance();
//            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
//            for (Map.Entry<String, Object> entry : entrySet) {
//                String key = entry.getKey();
//                Object value = entry.getValue();
//                String camelCaseKey = getCamelCaseString(key,false);
//                Method writeMethod = Introspection.getWriteMethod(destinationClass,camelCaseKey);
//                writeMethod.invoke(entity,value);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(),e);
//        }
//        return entity;
//    }
}
