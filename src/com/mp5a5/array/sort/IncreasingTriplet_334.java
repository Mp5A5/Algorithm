package com.mp5a5.array.sort;

import org.junit.Test;

/**
 * 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
 * <p>
 * 数学表达式如下:
 * <p>
 * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
 * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [5,4,3,2,1]
 * 输出: false
 */
public class IncreasingTriplet_334 {

    @Test
    public void test() {
        int[] array = {5, 1, 5, 5, 2, 5, 4};
        System.out.println(increasingTriplet(array));
    }

    /**
     * 3个连续递增子序列
     * 有3个槽位，a,b,c
     * 满足条件 a < b < c，即可
     * 需要将合适的元素填入这3个槽位
     */
    public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        for (int value : nums) {
            if (value <= a) {
                a = value;
            } else if (value <= b) {
                b = value;
            } else {
                return true;
            }
        }
        return false;
    }
}
