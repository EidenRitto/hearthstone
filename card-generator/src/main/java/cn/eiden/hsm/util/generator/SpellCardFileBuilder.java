package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.card.AbstractSecretCard;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Secret;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.Modifier;


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
                .addJavadoc(this.classComment())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(this.buildClassAnnotation())
                .addAnnotation(this.buildTagAnnotation())
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
                .build();
        if (cardInfo.getSecret() == 1){
            myClass = myClass.toBuilder()
                    .addMethod(MethodSpec.methodBuilder("createSecret")
                            .addModifiers(Modifier.PUBLIC)
                            .addAnnotation(Override.class)
                            .returns(Secret.class)
                            .addStatement("// 重写以补全效果")
                            .addStatement("return null")
                            .addJavadoc("$S", cardInfo.getCardText())
                            .build())
                    .superclass(AbstractSecretCard.class)
                    .build();
        }else {
            myClass = myClass.toBuilder()
                    .superclass(AbstractMagicCard.class)
                    .addMethod(MethodSpec.methodBuilder("magicEffect")
                            .addModifiers(Modifier.PUBLIC)
                            .addAnnotation(Override.class)
                            .returns(void.class)
                            .addParameter(Gamer.class, "gamer")
                            .addParameter(Minion.class, "target")
                            .addStatement("// 重写以补全效果")
                            .addJavadoc("$S\n", cardInfo.getCardText())
                            .addJavadoc("@param gamer 当前卡牌所有者\n")
                            .addJavadoc("@param target 所指定目标")
                            .build())
                    .build();
        }

        writeToSourceFile(myClass);
    }
}
