package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.dbdata.Entity;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.objct.Minion;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.Modifier;
import java.time.LocalDateTime;


/**
 * @author Eiden J.P Zhou
 * @date 2020/4/15 16:34
 */
@Slf4j
public class SpellCardFileBuilder extends AbstractCardFileBuilder {

    @Override
    void buildFile(Entity entity) {
        CardInfo cardInfo = loadInCache(entity);

        final String fileName = formatFileName(cardInfo.getCardName());

        TypeSpec myClass = TypeSpec.classBuilder(fileName)
                .superclass(AbstractMagicCard.class)
                .addJavadoc("本java源文件由java poet自动生成\n")
                .addJavadoc("@author $L\n",AUTHOR)
                .addJavadoc("@date $L", DTF.format(LocalDateTime.now()))
                .addModifiers(Modifier.PUBLIC)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(String.class, "greeting")
                        .addStatement("this.$N = $N", "greeting", "greeting")
                        .build())
                .addMethod(MethodSpec.methodBuilder("magicEffect")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .returns(void.class)
                        .addParameter(Gamer.class, "gamer")
                        .addParameter(Minion.class, "target")
                        .addStatement("// TODO: 需要手动补全的效果")
                        .build())
                .build();
    }
}
