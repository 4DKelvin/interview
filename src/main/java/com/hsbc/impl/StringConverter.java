package com.hsbc.impl;

import com.hsbc.IReplacementHandler;
import com.hsbc.IStringConverter;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author 4dkelvin
 */
public class StringConverter implements IStringConverter {

    public final static String PLACEHOLDER = "%";


    /**
     * 获取正则命中器，正则匹配连续重复出现2次以上的字符
     *
     * @param input 原始数据
     * @return 正则命中器
     */
    protected Matcher getMatcher(String input) {
        Pattern pat = Pattern.compile("(.+)\\1{2,}");
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
                Stream.iterate(0, i -> i + 1).limit(inputList.size()).forEach(index -> {
                    //从替换器获取替换的值
                    String replacement = handler == null ? PLACEHOLDER : handler.handle(input, mat.start(), mat.end());
                    if (index == mat.start()) {
                        //替换为目标值
                        inputList.set(index, replacement);
                    } else if (index > mat.start() && index < mat.end()) {
                        //替换占位符
                        inputList.set(index, PLACEHOLDER);
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
}
