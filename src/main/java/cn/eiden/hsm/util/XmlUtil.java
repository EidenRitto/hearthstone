package cn.eiden.hsm.util;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.dbdata.Entity;
import cn.eiden.hsm.dbdata.Tag;
import cn.eiden.hsm.util.generator.AbstractCardFileBuilder;
import cn.eiden.hsm.util.generator.CardFileDirector;
import cn.eiden.hsm.util.generator.SpellCardFileBuilder;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultText;

import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/8 10:17
 */
@Slf4j
public class XmlUtil {
    private static final String SIMPLIFIED_CHINESE = "zhCN";
    private static final String ENGLISH = "enUS";

    /**
     * 将XML转为指定的POJO
     * @param clazz 实体类类型
     * @param xmlPath xml路径
     * @return
     * @throws Exception
     */
    public static <T> List<T> xmlStrToObject(Class<T> clazz, String xmlPath, String nodeName) throws Exception {

        //1.创建Reader对象
        SAXReader reader = new SAXReader();
        //2.加载xml
        Document document = reader.read(new File(xmlPath));
        //3.获取根节点
        Element rootElement = document.getRootElement();

        return elementToObject(clazz,rootElement,nodeName);
    }

    private static <T> List<T> elementToObject(Class<T> clazz, Element rootElement,String nodeName) throws Exception{
        List<T> resultList = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        Iterator rootIterator = rootElement.elementIterator();
        while (rootIterator.hasNext()){
            Element nextElement = (Element) rootIterator.next();
            String qualifiedName = nextElement.getQualifiedName();
            if (!qualifiedName.toLowerCase().equals(nodeName.toLowerCase())){
                continue;
            }
            T rootNode = clazz.newInstance();
            List<Attribute> attributes = nextElement.attributes();
            for (Attribute attribute : attributes) {
                for (Field declaredField : declaredFields) {
                    String name = declaredField.getName();
                    if (attribute.getName().toLowerCase().equals(name.toLowerCase())){
                        Introspection.setPropertyValue(rootNode,declaredField.getName(),attribute.getValue());
                        break;
                    }
                }
            }
            //======递归遍历子节点======
            Iterator childIterator = nextElement.elementIterator();
            while (childIterator.hasNext()){
                Object next = childIterator.next();
                if (next instanceof Element){
                    Element childElement = (Element) next;
                    if (SIMPLIFIED_CHINESE.equals(childElement.getQualifiedName()) && childElement.hasContent()){
                        buildTag(rootNode, childElement, SIMPLIFIED_CHINESE);
                    }else if (ENGLISH.equals(childElement.getQualifiedName()) && childElement.hasContent()){
                        buildTag(rootNode, childElement, ENGLISH);
                    }

                    for (Field declaredField : declaredFields) {
                        String name = declaredField.getName();
                        if (childElement.getName().toLowerCase().equals(name.toLowerCase())){
                            Class<?> type = declaredField.getType();
                            if (type.equals(List.class)){
                                Type genericType = declaredField.getGenericType();
                                if (genericType == null){
                                    continue;
                                }

                                if (genericType instanceof ParameterizedType) {
                                    ParameterizedType pt = (ParameterizedType) genericType;
                                    // 得到泛型里的class类型对象
                                    Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];
                                    List<?> objects = elementToObject(actualTypeArgument, nextElement,name);
                                    Introspection.setPropertyValue(rootNode,declaredField.getName(),objects);
                                }
                            }
                            break;
                        }
                    }
                }
            }
            resultList.add(rootNode);
        }
        return resultList;
    }

    private static <T> void buildTag(T rootNode, Element childElement, String language) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        List textList = childElement.content();
        StringBuilder fullText = new StringBuilder();
        for (Object textObj : textList) {
            DefaultText text = (DefaultText) textObj;
            fullText.append(text.getStringValue());
        }
        Introspection.setPropertyValue(rootNode, language, fullText.toString());
    }

    public static void main(String[] args) {
        try {
            List<Entity> entities = XmlUtil.xmlStrToObject(Entity.class, "D:\\projectVS\\hsdata-master\\CardDefs.xml","Entity");
            CardFileDirector cardFileDirector = new CardFileDirector();
            for (Entity entity : entities) {
                cardFileDirector.createCardFile(entity);
            }
            log.info("总计写入成功卡牌数量："+ AbstractCardFileBuilder.successNum);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
