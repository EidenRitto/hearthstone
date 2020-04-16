package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.dbdata.Entity;
import cn.eiden.hsm.dbdata.Tag;
import cn.eiden.hsm.enums.*;
import cn.eiden.hsm.util.CardGeneratorUtils;
import cn.eiden.hsm.util.EnumUtils;
import cn.eiden.hsm.util.JavaBeansUtil;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * java文件建造者
 *
 * @author Eiden J.P Zhou
 * @date 2020/4/15 10:14
 */
@Slf4j
public abstract class AbstractCardFileBuilder {
    public static int successNum = 0;

    /**
     * 卡牌信息
     */
    protected final CardInfo cardInfo;

    /**
     * 时间格式化
     */
    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    /**
     * 作者
     */
    protected static final String AUTHOR = "Eiden J.P Zhou";
    /**
     * 输出生成文件的包名
     */
    protected static final String PACKAGE_NAME_PREFIX = "cn.eiden.hsm.game.card.dynamic.";

    public AbstractCardFileBuilder(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    /**
     * 创建文件
     */
    abstract public void buildFile();

    /**
     * 把英文名称格式化为Java所允许的合法文件名
     *
     * @return 合法java类名
     */
    protected String formatFileName() {
        return JavaBeansUtil.getCamelCaseString(cardInfo.getCardName(), true);
    }

    /**
     * 生成类注释
     *
     * @return 注释代码块
     */
    protected CodeBlock classComment() {
        return CodeBlock.builder()
                .add("$L\n", cardInfo.getCardCnName())
                .add("本java源文件由java poet自动生成<br/>\n")
                .add("@author $L\n", AUTHOR)
                .add("@date $L", DTF.format(LocalDateTime.now()))
                .build();
    }

    protected void writeToSourceFile(TypeSpec myClass) {
        String packageName = getPackageName();
        JavaFile javaFile = JavaFile.builder(packageName, myClass)
                .build();
        String outputPath = System.getProperty("user.dir") + CardGeneratorUtils.PACKAGE_PATH;
        Path path = Paths.get(outputPath);
        try {
            log.info("正在写入卡牌" + cardInfo.getCardCnName() + ".....");
            javaFile.writeToPath(path);
            log.info(cardInfo.getCardCnName() +"写入完成，源文件包路径为：" + packageName);
            successNum++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPackageName() {
        return PACKAGE_NAME_PREFIX
                + cardInfo.getCardSet().name().toLowerCase()
                + "." + cardInfo.getCardClass().name().toLowerCase();
    }

    protected FieldSpec buildFieldCost() {
        return FieldSpec.builder(int.class, "COST")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", cardInfo.getCost())
                .build();
    }

    protected FieldSpec buildFieldDesc() {
        return FieldSpec.builder(String.class, "DESCRIPTION")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$S", cardInfo.getCardText())
                .build();
    }

    protected FieldSpec buildFieldCardName() {
        return FieldSpec.builder(String.class, "CARD_NAME")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$S", cardInfo.getCardCnName())
                .build();
    }

    protected FieldSpec buildFieldCardId() {
        return FieldSpec.builder(String.class, "CARD_ID")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$S", cardInfo.getCardId())
                .build();
    }

    protected FieldSpec buildFieldCardSet() {
        return FieldSpec.builder(CardSet.class, "CARD_SET")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", cardInfo.getCardSet().getDeclaringClass().getSimpleName() + "." + cardInfo.getCardSet())
                .build();
    }

    protected FieldSpec buildFieldCardClass() {
        return FieldSpec.builder(CardClass.class, "CARD_CLASS")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", cardInfo.getCardClass().getClass().getSimpleName() + "." + cardInfo.getCardClass())
                .build();
    }

    protected FieldSpec buildFieldCardType() {
        return FieldSpec.builder(CardType.class, "CARD_TYPE")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", cardInfo.getCardType().getClass().getSimpleName() + "." + cardInfo.getCardType())
                .build();
    }

    protected FieldSpec buildFieldRarity() {
        return FieldSpec.builder(Rarity.class, "RARITY")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", cardInfo.getRarity().getClass().getSimpleName() + "." + cardInfo.getRarity())
                .build();
    }

    protected FieldSpec buildFieldHealth() {
        return FieldSpec.builder(long.class, "HEALTH")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", Long.parseLong(String.valueOf(cardInfo.getHealth())))
                .build();
    }

    protected FieldSpec buildFieldAtk() {
        return FieldSpec.builder(long.class, "ATK")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", Long.parseLong(String.valueOf(cardInfo.getAtk())))
                .build();
    }

    protected FieldSpec buildFieldRace() {
        return FieldSpec.builder(Race.class, "RACE")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", cardInfo.getRace().getClass().getSimpleName() + "." + cardInfo.getRace())
                .build();
    }

}
