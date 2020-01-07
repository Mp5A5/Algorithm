package com.mp5a5.dynamic;

/**
 * 有一个国家发现了5座金矿，每座金矿的黄金储备量不同，需要参与的挖掘的工人数也不同。参与挖掘工人总数是10人。每座金矿要么全挖要么不挖，不能派出一半人挖取一半金矿。
 * 求解要得到尽可能多的黄金，应该挖取哪几座金矿？比如5座金矿总量和用工数如下：400金/5人，500金/5人，200金/3人，300金/4人，350金/3人。
 * <p>
 * 分析：10人5金矿的最优子结构是10人4金矿时对应的最优子结构。10人4金矿最优结果有两种可能，一种是：第5座金矿不挖，只挖前4座金矿；还有就是第5座金矿挖，
 * 那么前4座金矿所需的人数就是10-第5座金矿的人数。那么4座金矿的最优选择对应5座金矿的最优选择就是：前4座金矿10人挖金数量与前4座挖金数量+第5座金矿挖金数量中的最大值，
 */
public class MostGold {

    /**
     * n:金矿的数量
     * w:工人数
     * g[n]:金矿的黄金量
     * p[n]:金矿的用工量
     * F(n,w) = 0    (n<=1, w < p[0]);
     * F(n,w) = g[0]     (n==1, w > =p[0]);
     * F(n,w) = F(n-1,w)    (n>1, w < p[n-1])
     * F(n,w) = max(F(n-1,w),  F(n-1,w-p[n-1])+g[n-1])    (n>1, w >= p[n-1])
     */
    /**
     * 方法的时间复杂度是 O(n * w)，空间复杂度是(w)。需要注意的是，当金矿只有5座的时候，动态规划的性能优势还没有体现出来。当金矿有10座，甚至更多的时候，动态规划就明显具备了优势。
     */
    public int getMostGold(int n, int w, int[] g, int[] p) {
        int[] preResults = new int[p.length];
        int[] result = new int[p.length];
        // 填充边界格子值
        for (int i = 0; i <= n; i++) {
            if (i < p[0]) {
                preResults[i] = 0;
            } else {
                preResults[i] = g[0];
            }
        }
        // 填充其余的格子值，外层循环是金矿数量，内层循环是工人数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                if (j < p[i]) {
                    result[i] = preResults[j];
                } else {
                    result[j] = Math.max(preResults[j], preResults[j - p[i]] + g[i]);
                }
            }
            preResults = result;
        }
        return result[n];
    }
}
