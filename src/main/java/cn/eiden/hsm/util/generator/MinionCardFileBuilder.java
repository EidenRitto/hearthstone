package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.game.objct.MinionObject;
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
                .addField(this.buildFieldCost())
                .addField(this.buildFieldDesc())
                .addField(this.buildFieldCardName())
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
                        .addStatement("super($N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N)"
                                , "CARD_NAME", "COST", "DESCRIPTION", "CARD_ID"
                                , "CARD_SET", "CARD_CLASS", "CARD_TYPE", "RARITY"
                                ,"HEALTH","ATK","RACE")
                        .build())
                .addMethod(MethodSpec.methodBuilder("createMinion")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .returns(MinionObject.class)
                        .addStatement("return new $T($N, $N, $N, $N)",MinionObject.class,"CARD_NAME","HEALTH","ATK","RACE")
                        .addJavadoc("$S\n", cardInfo.getCardText())
                        .build())
                .build();
        writeToSourceFile(myClass);
    }
}
