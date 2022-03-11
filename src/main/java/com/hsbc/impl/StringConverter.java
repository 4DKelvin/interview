package com.hsbc;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringConverter implements IStringConverter {

    public final static String PLACEHOLDER = "%";


    /**
     * 获取正则命中器，正则匹配连续重复出现2次以上的字符
     *
     * @param input 原始数据
     * @return 正则命中器
     */
    protected Matcher getMatcher(String input) {
        Pattern pat = Pattern.compile("(.+)\\1{2}");
        return pat.matcher(input);
    }

    /**
     * 根据输入数据判断是否需要转换
     *
     * @param input 原始数据
     * @return 是否找到匹配值
     */
    protected boolean isNeedToConvert(String input) {
        return getMatcher(input).find();
    }

    @Override
    public String convert(String input, IReplacementHandler handler) {
        List<String> inputList = Arrays.asList(input.split(""));
        if (isNeedToConvert(input)) {
            Matcher mat = getMatcher(input);
            while (mat.find()) {
                //命中将替换为指定数据
                inputList.replaceAll((e) -> {
                    int currentIndex = inputList.indexOf(e);
                    //从替换器获取替换的值
                    String replacement = handler == null ? PLACEHOLDER : handler.handle(input, mat.start(), mat.end());
                    if (currentIndex == mat.start()) {
                        //替换为目标值
                        return replacement;
                    } else if (currentIndex > mat.start() && currentIndex < mat.end()) {
                        //替换占位符
                        return PLACEHOLDER;
                    } else {
                        //未命中返回原始值
                        return e;
                    }
                });
            }
            //将占位符去掉
            String result = String.join("", inputList).replaceAll(PLACEHOLDER, "");
            System.out.printf("\r\n\t-> %s", result);
            //递归进行
            return convert(result, handler);
        } else {
            return input;
        }
    }

    @Override
    public String convert(String input) {
        return convert(input, null);
    }

    public static void main(String[] args) {

        IStringConverter converter = new StringConverter();

        String input = "aabcccbbad".toLowerCase(Locale.ROOT);
        System.out.println("\r\nAnswer Question #1:");
        System.out.printf("Input: \r\n\t-> %s%n", input);
        System.out.print("Output:");
        converter.convert(input);


        System.out.println("\r\nAnswer Question #2:");
        input = "abcccbad".toLowerCase(Locale.ROOT);
        System.out.printf("Input: \r\n\t-> %s%n", input);
        System.out.print("Output:");
        //获取命中数据前一个值为替换值
        converter.convert(input, (source, start, end) -> {
            //转Ascii码并获取Ascii上一个值
            int ascii = source.toCharArray()[start] - 1;
            //合法的[a-z]Ascii码则返回
            return ascii >= 97 && ascii <= 122 ? String.valueOf((char) ascii) : "";
        });

    }
}
