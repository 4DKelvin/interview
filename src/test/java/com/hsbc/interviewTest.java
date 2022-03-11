package com.hsbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 4dkelvin
 */
class interviewTest {

    private interview target;

    @BeforeEach
    void setUp() {

        target = new interview();
    }


    /**
     * 测试去重结果期望目标为D
     */
    @Test
    void convertTest1() {
        assertEquals("d", target.convertToEmpty("aabcccbbad"));
    }

    /**
     * 测试替换字符的结果期望目标为D
     */
    @Test
    void convertTest2() {
        assertEquals("d", target.convertToAsciiBefore("abcccbad"));
    }

    /**
     * 测试去重结果期望目标为zixuuqxyyx
     */
    @Test
    void convertTest3() {
        assertEquals("zixuuqxyyx", target.convertToEmpty("zixuuqxyyzzzx"));
    }

    /**
     * 测试去重结果期望目标为13asdoinzxn
     */
    @Test
    void convertTest4() {
        assertEquals("13asdoinzxn", target.convertToAsciiBefore("12223asdoinzxn"));
    }

    /**
     * 测试去重结果期望目标为空
     */
    @Test
    void convertTest5() {
        assertEquals("", target.convertToEmpty("         "));
    }
}