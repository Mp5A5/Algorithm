package com.mp5a5.array.son;

/**
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 */

public class ProductExceptSelf_238 {
    public int[] productExceptSelf(int[] nums) {

        if (nums == null || nums.length == 0) return null;

        int len = nums.length;
        int left = 1;//左积
        int right = 1;//右积

        // 初始化数组
        int[] res = new int[len];

        for (int i = 0; i < len; i++) {
            res[i] = 1;
        }

        //最终每个元素其左右乘积进行相乘得出结果
        for (int i = 0; i < len; i++) {
            //乘以其左边的乘积
            res[i] *= left;
            left *= nums[i];
            //乘以其右边的乘积
            res[len - 1 - i] *= right;
            right *= nums[len - 1 - i];
        }

        return res;
    }
}
