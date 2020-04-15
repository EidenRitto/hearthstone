package cn.eiden.hsm.util.generator;

import cn.eiden.hsm.game.card.AbstractCard;
import cn.eiden.hsm.util.CardGenerator;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/15 14:18
 */
public class SpellCardGenerator {




    /**
     * java poet测试
     * @param args
     */
    public static void main(String[] args) {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .addStatement("// TODO: 需要手动补全的效果  ")
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addJavadoc("本java源文件由javapoet自动生成\n")
                .addJavadoc("@author Eiden J.P Zhou\n")
                .addJavadoc("@date $L", dtf2.format(LocalDateTime.now()))
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("cn.eiden.hsm.game.card", helloWorld)
                .build();
        String outputPath = System.getProperty("user.dir") + CardGenerator.PACKAGE_PATH;
        Path path = Paths.get(outputPath);
        try {
            javaFile.writeToPath(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
