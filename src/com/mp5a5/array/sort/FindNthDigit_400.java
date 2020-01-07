package com.mp5a5.array.sort;

import org.junit.Test;

/**
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
 * <p>
 * 注意:
 * n 是正数且在32位整形范围内 ( n < 2^31)。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 3
 * <p>
 * 输出:
 * 3
 * 示例 2:
 * <p>
 * 输入:
 * 11
 * <p>
 * 输出:
 * 0
 * <p>
 * 说明:
 * 第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,12 ... 里是0，它是10的一部分。
 * <p>
 * 解释一下题意，就比如11，其实是12345678910111213.....字符串第11个，就是0啦
 * 10111213141516171819
 * 20212223242526272829
 */
public class FindNthDigit_400 {

    /**
     * 长度为1的数字，[1,9]共有9个数字，组成的字符串长度为9 * 长度1 == 9
     * 长度为2的数字，[10,99]一共90个数字，组成的字符串长度为90 * 长度2 = 180
     * 长度为3的数字，[100, 999]一共900个数字，组成的字符串长度为900 * 长度3 = 2700
     * ...
     * 长度为n的数字，[pow(10, len - 1),  pow(10, len)),一共 9 * pow(10, len-1)个数，组成的字符串长度为 9 * pow(10, len - 1) * len
     */
    public int findNthDigit(int n) {
        if (n < 10) return n;
        //单个数字的长度
        int len = 0;
        //长度为len + 1的数字所构成的字符串的长度
        long nextSize = (long) (Math.pow(10, len) * (len + 1) * 9);
        //第一步：确定n所处的数字的位数
        while (n > nextSize) {
            n -= nextSize;
            len += 1;
            nextSize = (long) (Math.pow(10, len) * (len + 1) * 9);
        }
        //第二步：确定n是出于位数为len + 1的数字中的第几个数字，并且转换为字符串
        String str = String.valueOf(Math.pow(10, len) + (n - 1) / (len + 1));
        //返回n在这个数字中对应的位，（比如当n == 15，求得len = 1，n = 6， resStr = “12”,然后取出“12”字符串的第二位）
        return str.charAt(n - (n - 1) / (len + 1) * (len + 1) - 1) - '0';
    }

    @Test
    public void test() {
    }
}
