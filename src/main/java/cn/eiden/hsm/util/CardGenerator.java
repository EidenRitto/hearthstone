package cn.eiden.hsm.util;

import cn.eiden.hsm.dbdata.Entity;

import java.util.List;

/**
 * Card生成器
 * @author Eiden J.P Zhou
 * @date 2020/4/10 11:55
 */
public class CardGenerator {
    public void begin(){
        try {
            List<Entity> entities = XmlUtil.xmlStrToObject(Entity.class, "D:\\ProjectVS\\HearthDb-master\\HearthDb\\CardDefs.xml");
            for (Entity entity : entities) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeCard(Entity entity){

    }
}
