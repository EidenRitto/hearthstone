package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.keyword.Aura;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.keyword.Combo;
import cn.eiden.hsm.game.keyword.DeathRattle;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.Modifier;
import java.util.List;

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
                .addField(this.buildFieldOverload())
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
                .addMethod(this.getConstructorMethod())
                .addMethod(MethodSpec.methodBuilder("createMinion")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .returns(Minion.class)
                        .addCode(addAdditionalField())
                        .addStatement("return minionObject")
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
        if (cardInfo.getBattleCry() == 1) {
            myClass = myClass.toBuilder().addMethod(MethodSpec.methodBuilder("selfBattleCry")
                    .addModifiers(Modifier.PROTECTED)
                    .returns(Battle.class)
                    .addStatement("// 重写以补全效果")
                    .addStatement("return null")
                    .addJavadoc("$S\n", cardInfo.getCardText())
                    .build()).build();
        }
        if (cardInfo.getAura() == 1){
            myClass = myClass.toBuilder().addMethod(MethodSpec.methodBuilder("selfAura")
                    .addModifiers(Modifier.PROTECTED)
                    .returns(Aura.class)
                    .addStatement("// 重写以补全效果")
                    .addStatement("return null")
                    .addJavadoc("$S\n", cardInfo.getCardText())
                    .build()).build();
        }
        if (cardInfo.getCombo() == 1) {
            myClass = myClass.toBuilder().addMethod(MethodSpec.methodBuilder("selfCombo")
                    .addModifiers(Modifier.PROTECTED)
                    .returns(Combo.class)
                    .addStatement("// 重写以补全效果")
                    .addStatement("return null")
                    .addJavadoc("$S\n", cardInfo.getCardText())
                    .build()).build();
        }
        if (cardInfo.getChooseOne() == 1) {
            myClass = myClass.toBuilder().addMethod(MethodSpec.methodBuilder("options")
                    .addModifiers(Modifier.PROTECTED)
                    .returns(List.class)
                    .addStatement("// 重写以补全效果")
                    .addStatement("return null")
                    .addJavadoc("$S\n", cardInfo.getCardText())
                    .build()).build();
        }
        writeToSourceFile(myClass);
    }

    private MethodSpec getConstructorMethod() {
        MethodSpec constructor;
        if (cardInfo.getChooseOne() == 1) {
            constructor = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("super($N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N)"
                            , "CARD_NAME", "COST", "DESCRIPTION", "ID",
                            "CARD_ID", "CARD_SET", "CARD_CLASS", "CARD_TYPE",
                            "RARITY", "HEALTH", "ATK", "RACE", "OVERLOAD")
                    .addStatement("addChooseOne(options())")
                    .build();
        }else {
            constructor = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("super($N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N)"
                            , "CARD_NAME", "COST", "DESCRIPTION", "ID",
                            "CARD_ID", "CARD_SET", "CARD_CLASS", "CARD_TYPE",
                            "RARITY", "HEALTH", "ATK", "RACE", "OVERLOAD")
                    .build();
        }
        return constructor;
    }

    private CodeBlock addAdditionalField() {
        CodeBlock core = CodeBlock.builder().addStatement("$T minionObject = new $T($N, $N, $N, $N, $N)", Minion.class, MinionObject.class, "CARD_NAME", "HEALTH", "ATK", "RACE", "ID").build();
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
        if (cardInfo.getSpellPower() > 0) {
            CodeBlock spellPower = CodeBlock.builder().addStatement("minionObject.setSpellPower($L)", cardInfo.getSpellPower()).build();
            core = core.toBuilder().add(spellPower).build();
        }
        if (cardInfo.getBattleCry() == 1) {
            CodeBlock battleCry = CodeBlock.builder().addStatement("minionObject.setBattle(this.selfBattleCry())").build();
            core = core.toBuilder().add(battleCry).build();
        }
        if (cardInfo.getAura() == 1){
            CodeBlock aura = CodeBlock.builder().addStatement("minionObject.setAura(this.selfAura())").build();
            core = core.toBuilder().add(aura).build();
        }
        if (cardInfo.getPoisonous() == 1) {
            CodeBlock poisonous = CodeBlock.builder().addStatement("minionObject.addPoisonous()").build();
            core = core.toBuilder().add(poisonous).build();
        }
        if (cardInfo.getCombo() == 1) {
            CodeBlock combo = CodeBlock.builder().addStatement("minionObject.setCombo(this.selfCombo())").build();
            core = core.toBuilder().add(combo).build();
        }
        if (cardInfo.getWindFury() == 1) {
            CodeBlock windFury = CodeBlock.builder().addStatement("minionObject.addWindFury()").build();
            core = core.toBuilder().add(windFury).build();
        }
        if (cardInfo.getLifeSteal() == 1) {
            CodeBlock lifeSteel = CodeBlock.builder().addStatement("minionObject.addLifeSteal()").build();
            core = core.toBuilder().add(lifeSteel).build();
        }
        if (cardInfo.getRush() == 1) {
            CodeBlock rush = CodeBlock.builder().addStatement("minionObject.addRush()").build();
            core = core.toBuilder().add(rush).build();
        }
        return core;
    }
}
