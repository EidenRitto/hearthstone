package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.game.card.AbstractHeroCard;
import cn.eiden.hsm.game.card.AbstractHeroPowerCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.game.minion.hero.Hero;
import cn.eiden.hsm.game.minion.hero.HeroMinion;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 17:24
 */
public class HeroFileBuilder extends AbstractCardFileBuilder {
    public HeroFileBuilder(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void buildFile() {
        final String fileName = formatFileName();

        TypeSpec myClass = TypeSpec.classBuilder(fileName)
                .superclass(AbstractHeroCard.class)
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
                .addField(this.buildFieldRace())
                .addField(this.buildFieldHeroPower())
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("super($N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N, $N)"
                                , "CARD_NAME", "COST", "DESCRIPTION", "ID",
                                "CARD_ID", "CARD_SET", "CARD_CLASS", "CARD_TYPE",
                                "RARITY", "HEALTH", "RACE", "HERO_POWER_ID")
                        .build())
                .addMethod(MethodSpec.methodBuilder("createHero")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .returns(Hero.class)
                        .addCode(addAdditionalField())
                        .addStatement("return HeroMinion")
                        .addJavadoc("$S\n", cardInfo.getCardText())
                        .build())
                .build();

        writeToSourceFile(myClass);
    }

    private CodeBlock addAdditionalField() {
        CodeBlock core = CodeBlock.builder().addStatement("$T HeroMinion = new $T($N, $N, $N, $N, $N)"
                , HeroMinion.class, HeroMinion.class, "CARD_NAME", "HEALTH", "RACE", "ID", "CARD_CLASS").build();
        core = core.toBuilder().addStatement("$T powerCard = ($T)$T.getCardById($N)", AbstractHeroPowerCard.class, AbstractHeroPowerCard.class, CardFactory.class, "HERO_POWER_ID")
                .addStatement("HeroMinion.setHeroPower(powerCard)")
                .build();
        return core;
    }
}
