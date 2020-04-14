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
    private static final String PACKAGEPATH = "\\src\\main\\java\\cn\\eiden\\hsm\\game\\card\\";

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
        String fileName = this.buildFileName(entity);
        String outputPath = System.getProperty("user.dir") + PACKAGEPATH;
        File file = new File(outputPath+fileName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            FileChannel channel = outputStream.getChannel();
            String javaFile = this.buildFile(entity);
            ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(javaFile);
            channel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String buildFileName(Entity entity){
        List<Tag> tags = entity.getTag();
        Tag tag = tags.stream()
                .filter(e -> e.getName().equals(GameTag.CARDNAME.name()))
                .findAny()
                .orElse(new Tag());
        return tag.getEnUS();
    }

    private String buildFile(Entity entity){
        String cardName = this.buildFileName(entity);
        return "null";
    }
}
