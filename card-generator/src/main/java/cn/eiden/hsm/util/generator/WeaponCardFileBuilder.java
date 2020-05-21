package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.game.card.AbstractWeaponCard;
import cn.eiden.hsm.game.keyword.DeathRattle;
import cn.eiden.hsm.game.minion.WeaponObject;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.Modifier;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/17 20:09
 */
@Slf4j
public class WeaponCardFileBuilder extends AbstractCardFileBuilder {
    public WeaponCardFileBuilder(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void buildFile() {
        final String fileName = formatFileName();

        TypeSpec myClass = TypeSpec.classBuilder(fileName)
                .superclass(AbstractWeaponCard.class)
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
                .addField(this.buildFieldAtk())
                .addField(this.buildFieldDurability())
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("super($N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N)"
                                , "CARD_NAME", "COST", "DESCRIPTION", "ID", "CARD_ID"
                                , "CARD_SET", "CARD_CLASS", "CARD_TYPE", "RARITY", "ATK", "DURABILITY")
                        .build())
                .addMethod(MethodSpec.methodBuilder("createWeapon")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .returns(WeaponObject.class)
                        .addCode(this.addAdditionalField())
                        .addStatement("return new $T($N, $N, $N)", WeaponObject.class, "CARD_NAME", "ATK", "DURABILITY")
                        .addJavadoc("$S\n", cardInfo.getCardText())
                        .build())
                .build();
        if (cardInfo.getDeathRattle() == 1) {
            myClass = myClass.toBuilder().addMethod(MethodSpec.methodBuilder("selfDeathRattle")
                    .addModifiers(Modifier.PROTECTED)
                    .returns(DeathRattle.class)
                    .addStatement("// 重写以补全效果")
                    .addStatement("return null")
                    .addJavadoc("$S\n", cardInfo.getCardText())
                    .build()).build();
        }
        writeToSourceFile(myClass);
    }

    private CodeBlock addAdditionalField() {
        CodeBlock core = CodeBlock.builder().addStatement("$T weaponObject = new $T($N, $N, $N)", WeaponObject.class, WeaponObject.class, "CARD_NAME", "ATK", "DURABILITY").build();
        if (cardInfo.getDeathRattle() == 1) {
            CodeBlock deathRattle = CodeBlock.builder().addStatement("weaponObject.addDeathRattle(this.selfDeathRattle())").build();
            core = core.toBuilder().add(deathRattle).build();
        }
        return core;
    }
}
