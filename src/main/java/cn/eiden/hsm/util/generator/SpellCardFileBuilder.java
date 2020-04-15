package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.dbdata.Entity;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.util.CardGenerator;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;


/**
 * {@link } https://github.com/square/javapoet/
 *
 * @author Eiden J.P Zhou
 * @date 2020/4/15 16:34
 */
@Slf4j
public class SpellCardFileBuilder extends AbstractCardFileBuilder {

    @Override
    public void buildFile(Entity entity) {
        CardInfo cardInfo = loadInCache(entity);
        if (cardInfo.getRarity() == null){
            cardInfo.setRarity(Rarity.INVALID);
        }
        if (cardInfo.getCardType() != CardType.SPELL){
            log.debug("暂无指定建造者,跳过卡牌【"+cardInfo.getCardCnName()+"】");
            return;
        }
        final String fileName = formatFileName(cardInfo.getCardName());

        TypeSpec myClass = TypeSpec.classBuilder(fileName)
                .superclass(AbstractMagicCard.class)
                .addJavadoc("本java源文件由java poet自动生成\n")
                .addJavadoc("@author $L\n", AUTHOR)
                .addJavadoc("@date $L", DTF.format(LocalDateTime.now()))
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec.builder(int.class, "cost")
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$L", cardInfo.getCost())
                        .build())
                .addField(FieldSpec.builder(String.class, "description")
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$S", cardInfo.getCardText())
                        .build())
                .addField(FieldSpec.builder(String.class, "cardName")
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$S", cardInfo.getCardCnName())
                        .build())
                .addField(FieldSpec.builder(String.class, "cardId")
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$S", cardInfo.getCardId())
                        .build())
                .addField(FieldSpec.builder(CardSet.class, "cardSet")
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$L", cardInfo.getCardSet().getDeclaringClass().getSimpleName() + "." + cardInfo.getCardSet())
                        .build())
                .addField(FieldSpec.builder(CardClass.class, "cardClass")
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$L", cardInfo.getCardClass().getClass().getSimpleName() + "." + cardInfo.getCardClass())
                        .build())
                .addField(FieldSpec.builder(CardType.class, "cardType")
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$L", cardInfo.getCardType().getClass().getSimpleName() + "." + cardInfo.getCardType())
                        .build())
                .addField(FieldSpec.builder(Rarity.class, "rarity")
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$L", cardInfo.getRarity().getClass().getSimpleName() + "." + cardInfo.getRarity())
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("super($N, $N, $N, $N, $N, $N, $N, $N)"
                                , "cardName", "cost", "description", "cardId"
                                , "cardSet", "cardClass", "cardType", "rarity")
                        .build())
                .addMethod(MethodSpec.methodBuilder("magicEffect")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .returns(void.class)
                        .addParameter(Gamer.class, "gamer")
                        .addParameter(Minion.class, "target")
                        .addStatement("// TODO: 需要手动补全的效果")
                        .addJavadoc("$S\n", cardInfo.getCardText())
                        .addJavadoc("@param gamer 当前卡牌所有者\n")
                        .addJavadoc("@param target 所指定目标")
                        .build())
                .build();
        String packageName = "cn.eiden.hsm.game.card.dynamic."
                + cardInfo.getCardSet().name().toLowerCase()
                + "." + cardInfo.getCardClass().name().toLowerCase();
        JavaFile javaFile = JavaFile.builder(packageName, myClass)
                .build();
        String outputPath = System.getProperty("user.dir") + CardGenerator.PACKAGE_PATH;
        Path path = Paths.get(outputPath);
        try {
            log.info("正在写入卡牌" + cardInfo.getCardCnName() + ".....");
            javaFile.writeToPath(path);
            log.info(cardInfo.getCardCnName() +"写入完成，源文件包路径为：" + packageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}