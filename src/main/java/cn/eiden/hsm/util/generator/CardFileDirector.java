package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.dbdata.Entity;

/**
 * 指挥者
 * @author Eiden J.P Zhou
 * @date 2020/4/15 10:13
 */
public class CardFileDirector {
    /**指定的建造者*/
    private AbstractCardFileBuilder abstractCardFileBuilder;

    public CardFileDirector() {
    }

    /***
     * 生成java源文件
     * @param entity 内存中的数据对象
     */
    public void createCardFile(Entity entity){
        abstractCardFileBuilder.buildFile(entity);
    }
}
