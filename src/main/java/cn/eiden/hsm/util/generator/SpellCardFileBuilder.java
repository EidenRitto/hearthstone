package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.annotation.Id;
import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.util.CardGeneratorUtils;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author Eiden J.P Zhou
 * @date 2020/4/15 16:34
 */
@Slf4j
public class SpellCardFileBuilder extends AbstractCardFileBuilder {

    public SpellCardFileBuilder(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void buildFile() {

        final String fileName = formatFileName();

        TypeSpec myClass = TypeSpec.classBuilder(fileName)
                .superclass(AbstractMagicCard.class)
                .addJavadoc(this.classComment())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(Id.class)
                        .addMember("value","$L",Integer.parseInt(cardInfo.getId()))
                        .addMember("name","$S",cardInfo.getCardCnName())
                        .build())
                .addField(this.buildFieldCost())
                .addField(this.buildFieldDesc())
                .addField(this.buildFieldCardName())
                .addField(this.buildFieldId())
                .addField(this.buildFieldCardId())
                .addField(this.buildFieldCardSet())
                .addField(this.buildFieldCardClass())
                .addField(this.buildFieldCardType())
                .addField(this.buildFieldRarity())
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("super($N, $N, $N, $N, $N, $N, $N, $N, $N)"
                                , "CARD_NAME", "COST", "DESCRIPTION", "ID", "CARD_ID"
                                , "CARD_SET", "CARD_CLASS", "CARD_TYPE", "RARITY")
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
        writeToSourceFile(myClass);
    }
}
