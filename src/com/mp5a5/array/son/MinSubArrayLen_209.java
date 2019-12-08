package com.mp5a5.array.son;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例: 
 * <p>
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 */
public class MinSubArrayLen_209 {

    // 窗口滑动方法
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = 0;
        int sum = 0;
        int len = Integer.MAX_VALUE;
        while (right < nums.length) {

            if (sum + nums[right] < s) {//若滑窗之间的和小于s，右边框右移，sum增大
                sum += nums[right++];
            } else {//若滑窗之间的和大于等于s，左边框右移，sum减小
                if (right - left < len) //若当前符合条件的连续子数组比ans内记录的长度更小，则更新ans值
                    len = right - left + 1;
                sum -= nums[left++];
            }


        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }

    public int minSubArrayLen1(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0;
        int j = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (j < nums.length) {
            sum += nums[j++];
            while (sum >= s) {
                min = Math.min(min, j - i);
                sum -= nums[i++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
