package com.hsbc;

import com.hsbc.impl.StringConverter;

import java.util.Locale;

/**
 * @author 4dkelvin
 */
public class interview {

    private IStringConverter converter;

    public interview() {
        converter = new StringConverter();
    }


    /**
     * 去掉连续重复3次的字符
     * @param input 输入的值
     * @return 去掉重复后的值
     */
    public String convertToEmpty(String input) {
        String mInput = input.toLowerCase(Locale.ROOT);
        System.out.println("\r\nAnswer Question #1:");
        System.out.printf("Input: \r\n\t-> %s%n", mInput);
        System.out.print("Output:");
        return converter.convert(mInput);
    }

    /**
     * 将连续重复3次的字符替换为ASCII码[a-z]的前一位
     * @param input 输入的值
     * @return 替换后的值
     */
    public String convertToAsciiBefore(String input) {
        String mInput = input.toLowerCase(Locale.ROOT);
        System.out.println("\r\nAnswer Question #2:");
        System.out.printf("Input: \r\n\t-> %s%n", mInput);
        System.out.print("Output:");

        //获取命中数据前一个值为替换值
        return converter.convert(mInput, (source, start, end) -> {
            //转Ascii码并获取Ascii上一个值
            int ascii = source.toCharArray()[start] - 1;
            //合法的[a-z]Ascii码则返回
            return ascii >= 97 && ascii <= 122 ? String.valueOf((char) ascii) : "";
        });
    }
}
