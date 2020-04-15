package cn.eiden.hsm.util;

import cn.eiden.hsm.dbdata.Entity;
import cn.eiden.hsm.dbdata.Tag;
import cn.eiden.hsm.enums.GameTag;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Card生成器
 * @author Eiden J.P Zhou
 * @date 2020/4/10 11:55
 */
public class CardGenerator {
    public static final String PACKAGE_PATH = "\\src\\main\\java\\";

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
