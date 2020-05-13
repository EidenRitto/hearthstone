package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.keyword.DeathRattle;
import cn.eiden.hsm.game.objct.MinionObject;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.Modifier;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/16 14:02
 */
@Slf4j
public class MinionCardFileBuilder extends AbstractCardFileBuilder {
    public MinionCardFileBuilder(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void buildFile() {
        final String fileName = formatFileName();
        TypeSpec myClass = TypeSpec.classBuilder(fileName)
                .superclass(AbstractMinionCard.class)
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
                .addField(this.buildFieldHealth())
                .addField(this.buildFieldAtk())
                .addField(this.buildFieldRace())
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("super($N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N)"
                                , "CARD_NAME", "COST", "DESCRIPTION", "ID",
                                "CARD_ID", "CARD_SET", "CARD_CLASS", "CARD_TYPE",
                                "RARITY", "HEALTH", "ATK", "RACE")
                        .build())
                .addMethod(MethodSpec.methodBuilder("createMinion")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .returns(MinionObject.class)
                        .addCode(addAdditionalField())
                        .addStatement("return minionObject")
                        .addJavadoc("$S\n", cardInfo.getCardText())
                        .build())
                .build();
        if (cardInfo.getDeathRattle() == 1) {
            myClass = myClass.toBuilder().addMethod(MethodSpec.methodBuilder("selfDeathRattle")
                    .addModifiers(Modifier.PRIVATE)
                    .returns(DeathRattle.class)
                    .addStatement("// 重写以补全效果")
                    .addStatement("return null")
                    .addJavadoc("$S\n", cardInfo.getCardText())
                    .build()).build();
        }
        writeToSourceFile(myClass);
    }

    private CodeBlock addAdditionalField() {
        CodeBlock core = CodeBlock.builder().addStatement("$T minionObject = new $T($N, $N, $N, $N)", MinionObject.class, MinionObject.class, "CARD_NAME", "HEALTH", "ATK", "RACE").build();
        if (cardInfo.getTaunt() == 1) {
            CodeBlock taunt = CodeBlock.builder().addStatement("minionObject.addTaunt()").build();
            core = core.toBuilder().add(taunt).build();
        }
        if (cardInfo.getCharge() == 1) {
            CodeBlock charge = CodeBlock.builder().addStatement("minionObject.addCharge()").build();
            core = core.toBuilder().add(charge).build();
        }
        if (cardInfo.getStealth() == 1) {
            CodeBlock stealth = CodeBlock.builder().addStatement("minionObject.addStealth()").build();
            core = core.toBuilder().add(stealth).build();
        }
        if (cardInfo.getDivineShield() == 1) {
            CodeBlock divineShield = CodeBlock.builder().addStatement("minionObject.addDivineShield()").build();
            core = core.toBuilder().add(divineShield).build();
        }
        if (cardInfo.getDeathRattle() == 1) {
            CodeBlock deathRattle = CodeBlock.builder().addStatement("minionObject.addDeathRattle(this.selfDeathRattle())").build();
            core = core.toBuilder().add(deathRattle).build();
        }
        return core;
    }
}
