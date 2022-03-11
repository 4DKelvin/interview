package com.hsbc;

/**
 * @author 4dkelvin
 */
public interface IReplacementHandler {
    /**
     * 自定义替换器的主要方法
     *
     * @param source     原始数据
     * @param matchStart 命中规则的字符开始位置
     * @param matchEnd   命中规则的字符结束位置
     * @return 将命中的字符替换为返回值
     */
    String handle(String source, int matchStart, int matchEnd);
}
