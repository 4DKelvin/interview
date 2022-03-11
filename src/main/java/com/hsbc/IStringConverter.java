package com.hsbc;

/**
 * @author 4dkelvin
 */
public interface IStringConverter {


    /**
     * 将连续重复3次的字符替换为替换器的返回值
     *
     * @param input   输入字符
     * @param handler 替换器
     * @return 替换后的结果
     */
    String convert(String input, IReplacementHandler handler);

    /**
     * 将连续重复3次的字符去掉
     *
     * @param input 输入字符
     * @return 去掉重复字符后的结果
     */
    String convert(String input);
}
