package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.annotation.Id;
import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.enums.*;
import cn.eiden.hsm.util.CardGeneratorUtils;
import cn.eiden.hsm.util.JavaBeansUtil;
import com.google.common.base.Joiner;
import com.squareup.javapoet.*;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    protected static final String PACKAGE_NAME_PREFIX = "cn.eiden.hsm.game.card.defs.";

    public AbstractCardFileBuilder(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    /**
     * 创建文件
     */
    abstract public void buildFile();

    /**
     * 把英文名称格式化为Java所允许的合法文件名</br>
     * 当且仅当同包下出现同名文件时，添加新的后缀 如： xx_1 xx_2
     *
     * @return 合法java类名
     */
    protected String formatFileName() {
        try {
            String className = JavaBeansUtil.getCamelCaseString(cardInfo.getCardName(), true);
            String originName = className;
            String packageClassName = getPackageName() + className;
            int i = 1;
            while (CardGeneratorUtils.classNameSet.contains(packageClassName)) {
                className = String.format(originName + "_%s", i);
                packageClassName = getPackageName() + className;
                i++;
            }
            CardGeneratorUtils.classNameSet.add(packageClassName);
            return className;
        } catch (Exception e) {
            log.error(cardInfo.getCardCnName());
            return "unknow";
        }
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
        if (cardInfo.getQuestProgressTotal() > 0) {
            myClass = myClass.toBuilder().addField(this.buildFieldQuestProgressTotal()).build();
        }
        if (cardInfo.getQuestRewardDatabaseId() > 0) {
            myClass = myClass.toBuilder().addField(this.buildFieldQuestRewardDatabaseId()).build();
        }
        JavaFile javaFile = JavaFile.builder(packageName, myClass)
                .indent("    ")
                .build();
        String outputPath = System.getProperty("user.dir") + "\\hearth-card" + CardGeneratorUtils.PACKAGE_PATH;
        Path path = Paths.get(outputPath);
        try {
            log.info("正在写入卡牌" + cardInfo.getCardCnName() + ".....");
            javaFile.writeToPath(path);
            log.info(cardInfo.getCardCnName() + "写入完成，源文件包路径为：" + packageName);
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

    protected FieldSpec buildFieldOverload() {
        return FieldSpec.builder(int.class, "OVERLOAD")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", cardInfo.getOverload())
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

    protected FieldSpec buildFieldId() {
        return FieldSpec.builder(String.class, "ID")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$S", cardInfo.getId())
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

    protected FieldSpec buildFieldDurability() {
        return FieldSpec.builder(long.class, "DURABILITY")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", Long.parseLong(String.valueOf(cardInfo.getDurability())))
                .build();
    }

    protected FieldSpec buildFieldHeroPower() {
        return FieldSpec.builder(int.class, "HERO_POWER_ID")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", Integer.parseInt(String.valueOf(cardInfo.getHeroPower())))
                .build();
    }

    private FieldSpec buildFieldQuestProgressTotal() {
        return FieldSpec.builder(int.class, "QUEST_PROGRESS_TOTAL")
                .addModifiers(Modifier.PROTECTED, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", Integer.parseInt(String.valueOf(cardInfo.getQuestProgressTotal())))
                .build();
    }

    private FieldSpec buildFieldQuestRewardDatabaseId() {
        return FieldSpec.builder(int.class, "QUEST_REWARD_DATABASE_ID")
                .addModifiers(Modifier.PROTECTED, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", Integer.parseInt(String.valueOf(cardInfo.getQuestRewardDatabaseId())))
                .build();
    }

    protected FieldSpec buildFieldMultiClassGroup() {
        return FieldSpec.builder(MultiClassGroup.class, "MULTI_CLASS_GROUP")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("$L", cardInfo.getMultiClassGroup().getClass().getSimpleName() + "." + cardInfo.getMultiClassGroup())
                .build();
    }

    protected AnnotationSpec buildClassAnnotation() {
        return AnnotationSpec.builder(Id.class)
                .addMember("value", "$L", Integer.parseInt(cardInfo.getId()))
                .addMember("name", "$S", cardInfo.getCardCnName())
                .build();
    }

    protected AnnotationSpec buildTagAnnotation() {
        return AnnotationSpec.builder(Tags.class)
                .addMember("cardClass", "$L", cardInfo.getCardClass().getDeclaringClass().getSimpleName() + "." + cardInfo.getCardClass())
                .addMember("cardSet", "$L", cardInfo.getCardSet().getDeclaringClass().getSimpleName() + "." + cardInfo.getCardSet())
                .addMember("value", "$L", "{" + Joiner.on(",").join(cardInfo.getTagList()) + "}")
                .build();
    }

}
