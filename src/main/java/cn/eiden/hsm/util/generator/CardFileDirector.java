package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.dbdata.Entity;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.util.CardGeneratorUtils;

/**
 * 指挥者
 * @author Eiden J.P Zhou
 * @date 2020/4/15 10:13
 */
public class CardFileDirector {
    public CardFileDirector() {

    }
    /***
     * 生成java源文件
     * @param entity 内存中的数据对象
     */
    public void createCardFile(Entity entity){
        AbstractCardFileBuilder abstractCardFileBuilder = null;

        CardInfo cardInfo = CardGeneratorUtils.loadInCache(entity);
        CardType cardType = cardInfo.getCardType();
        switch (cardType){
            case SPELL:
                abstractCardFileBuilder = new SpellCardFileBuilder(cardInfo);
                break;
            case MINION:
                break;
            default:
                break;
        }
        if (abstractCardFileBuilder != null){
            abstractCardFileBuilder.buildFile();
        }
    }
}
