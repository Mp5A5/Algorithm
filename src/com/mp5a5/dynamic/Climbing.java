package com.mp5a5.dynamic;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 有一座高度是n级台阶的楼梯，从下往上走，每跨一步只能向上1级或者2级台阶。要求用程序来求出一共有多少种走法。
 */
public class Climbing {
    /**
     * 假设有10级台阶：
     * 暂且不管从0到8级台阶的过程，也不管0到9级台阶的过程。想要走到10级，最后一步必然是从8级或者9级开始的。
     * 引申出一个新的问题：如果我们已知从0到9级台阶的走法有M种，0到8级台阶的走法有N种，那么0到10级台阶有多少种？
     * 10级台阶所有的走法可以根据最后一步的不用而分成两个部分，第一个部分的最后一步是从9级到10级，这部分的走法数量和9级台阶的走法数量是相等的，也就是M。
     * 第二部分的最后一步是从8级到10级，这部分的走法数量和8级台阶的走法数量是相等的。也就是N。这两部分相加总的数量是M+N。即：F(10)=F(9)+F(8)由此我们可以归纳出公式：
     * F(1)=1;
     * F(2)=2;
     * F(n)=F(n-1)+F(n-2) (n>=3)
     * 动态规划中包含三个重要概念：最优子结构、边界、状态转移公式：
     * 我们分析出F(10)=F(9)+F(8)，因此F(9)和F(8)是F(10)的最优子结构
     * 当只有1级或2级台阶时，我们可以直接得到结果，无需继续简化。我们称F(1)和F(2)是问题的边界。如果一个问题没有边界，将永远无法得到结果。
     * F(n)=F(n-1)+F(n-2)是阶段与阶段之间的状态转移公式。这是动态规划的核心，决定了问题的每一个阶段和下一个阶段的关系。
     */

    /**
     * 时间复杂度：
     * 要计算F(n)就要先得到F(n-1)和F(n-2)的值。要计算F(n-1)，就要先得到F(n-2)和F(n-3)的值，以此类推，我们可以归纳出一个类似二叉树的图：
     * F(n)
     * F(n-1)         F(n-2)
     * F(n-2)   F(n-3)  F(n-3)   F(n-4)
     * 树的节点就是我们递归方法所需要计算的次数。不难看出这棵树的高度是N-1，节点接近2的N-1次方。所以方法的时间复杂度可以近似看成是O(2^N)
     */
    public int getClimbingWay(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        return getClimbingWay(n - 1) + getClimbingWay(n - 2);
    }

    /**
     * 优化上面算法：
     * 由于上面的F(n-2)、F(n-3) 等都存在重复计算，所以加入缓存策略，先创建一个哈希表，每次把不同参数的结果存入表中。当遇到参数相同时再从表里取出来，就不用重复计算了。
     * 这种暂存结果的计算方式就叫：备忘录算法。
     * 在下面代码中，集合map是一个备忘录。当每次需要计算F(N)的时候，会首先从map中寻找匹配元素。如果map中存在，就直接返回结果，如果map中不存在，就计算出结果，存入备忘录中。
     * 从F(1)到F(n)一共有N个不同的输入，再哈希表里存了N-2个结果，所以时间复杂度和控件复杂度都是O(n)
     */
    public int getClimbingWay(int n, Map<Integer, Integer> map) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (map.containsKey(n)) {
            System.out.println("缓存的值的索引是：" + n);
            return map.get(n);
        } else {
            int value = getClimbingWay(n - 1, map) + getClimbingWay(n - 2, map);
            map.put(n, value);
            return value;
        }
    }

    /**
     * 空间复杂度优化：
     * 我们在计算的时候一定要对F(n)自顶向下做递归运算吗？可不可以自底向下，用迭代的方式推导出结果？我们通过一张表格来说明一下F(n)自底向上的求解过程
     * 台阶数 1  2  3  4  5  6  7  8  9  10
     * 走法数 1  2
     * 表格第一行代表了楼梯的台阶数目，第二行代表了若干级台阶对应的走法数。
     * <p>
     * 台阶数 1  2  3  4  5  6  7  8  9  10
     * 走法数 1  2  3
     * 第一次迭代台阶数等于3的时候，走法数是3，这个结果是F(1)和F(2)这两个结果相加得到的。所以F(3)只依赖于F(1)和F(2) 依次类推，每一次迭代过程
     * 只要保留之前的两个状态，就可以推导出新的状态，而不需要像备忘录那样保留全部的子状态。
     * 控件复杂度只要：O(1)
     */
    public int getClimbingWays(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }



    @Test
    public void test() {
        System.out.println(getClimbingWay(10, new HashMap<Integer, Integer>()));
    }
}
