package com.mp5a5.array.son;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。
 * <p>
 * 注意:
 *  nums 数组的总和是一定在 32 位有符号整数范围之内的。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1, -1, 5, -2, 3], k = 3
 * 输出: 4
 * 解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
 * 示例 2:
 * <p>
 * 输入: nums = [-2, -1, 2, 1], k = 1
 * 输出: 2
 * 解释: 子数组 [-1, 2] 和等于 1，且长度最长。
 */
public class MaxSubArrayLen_325 {

    /**
     * 首先假设这么一个场景，从下标为0到下标为100，和sum = 2000，假设我们要求的目标k=800，那么我们只要知道从0开始，最早出现的下标i，
     * 使得0到i之间的和为1200，这样就能保证i+1到100的和为800，同时还能保证这个长度是以100结尾的最大的长度
     */
    /**
     * 1.我们知道如何最终结果为sum(i,j)为从i到j的子序列和，则sum(i,j) = sum(0,j) - sum(0,i-1);
     * 2.按照第一条的规则，假设sum(i,j) = k,则 sum(0,j) = k + sum(0,i-1),因此我们利用hash查找是否存在前N项和的值为sum(0,n) - k,如果找到，则输出结果
     */
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int res = 0;
        map.put(0, -1);// 如果出现nums=[2] key=2的状况
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (map.containsKey(sum - k)) {
                res = Math.max(res, i - map.get(sum - k));
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return res;
    }

    @Test
    public void test() {

        System.out.println(maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
    }
}
