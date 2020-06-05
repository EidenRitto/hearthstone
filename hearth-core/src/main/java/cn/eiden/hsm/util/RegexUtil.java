package cn.eiden.hsm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author Eiden J.P Zhou
 * @date 2019/5/8/9:14
 */
public class RegexUtil {
    /**
     * 匹配数字
     * ?:0或1个, *:0或多个, +:1或多个
     */
    private static Pattern numberPattern = Pattern.compile("-?[0-9]+.?[0-9]*");
    private static Pattern booleanPattern = Pattern.compile("true|false");
    /**判断是不是大括号或者中括号开头*/
    private static Pattern jsonHeadPattern = Pattern.compile("[\\{\\[].*?");
    /**
     * 科学计数法正则表达式
     */
    private static Pattern E_REGX = Pattern.compile("^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$");

    /**
     * 判断传入的数字是否在0~1之间
     */
    private static Pattern REGEX = Pattern.compile("^(?!^[0.]+$)0\\.[0-9]+|1$");


    private static Pattern EXCEL_SHHET_NAME = Pattern.compile("[\\:\\\\\\/\\?\\*\\[\\]\\'\\？].*?");

    private static Pattern YJJH_CDM = Pattern.compile("(CJXDM[0-9]+)");

    private static final String REGEX_HTML = "<[^>]+>";

    private RegexUtil() {
    }

    public static Pattern getNumberPattern() {
        return numberPattern;
    }


    /**
     * 判断字符串的内容是不是正确的整数，小数
     */
    public static boolean isNumberStr(String str) {
        Matcher matcher = numberPattern.matcher(str);
        boolean b = matcher.matches();
        return b;
    }

    public static String getAllowSheetName(String str){
        Matcher matcher = EXCEL_SHHET_NAME.matcher(str);
        return matcher.replaceAll("");
    }

    /**
     * 判断输入字符串是否为科学计数法
     *
     * @param input 字符
     * @return
     */
    public static boolean isENum(String input) {
        boolean b = E_REGX.matcher(input).matches();
        return b;
    }


    /**
     * 判断字符串的内容是不是布尔类型
     */
    public static boolean isBooleanStr(String str) {
        Matcher matcher = booleanPattern.matcher(str);
        return matcher.matches();
    }

    public static boolean haveJsonHead(String str){
        Matcher matcher = jsonHeadPattern.matcher(str);
        return matcher.matches();
    }


    /**
     * 判断字符串内容是否在(0,1)之间, 不包括0和1
     * @param str
     * @return
     */
    public static boolean isBetweenZeroAndOne(String str){
        Matcher matcher = REGEX.matcher(str);
        return matcher.matches();
    }

    /**
     * 从表达式中获取采集项代码
     * @param exp 规则引擎表达式
     * @return 表达式中出现过的采集项代码
     */
    public static List<String> getCdmFromExp(String exp){
        List<String> result = new ArrayList<>();
        Matcher matcher = YJJH_CDM.matcher(exp);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    public static String removeHtmlTag(String str){
        if (str == null){
            return "";
        }
        str = str.replaceAll(" +","");
        return str.replaceAll(REGEX_HTML,"");
    }
}
