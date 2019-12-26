package com.mp5a5.array.sort;

import java.util.Arrays;

/**
 * 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1, 5, 1, 1, 6, 4]
 * 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
 * 示例 2:
 * <p>
 * 输入: nums = [1, 3, 2, 2, 3, 1]
 * 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
 * <p>
 * 规则：
 * // index为偶数，则nums[index]按照要求一定小于nums[index+1]
 * // index为奇数，则nums[index]按照要求一定大于nums[index+1]
 */
public class WiggleSort_324 {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int left = 1;
        int right = nums.length - 2;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left += 2;
            right -= 2;
        }
    }

    public void wiggleSort1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (((i % 2 == 0) && nums[i] > nums[i + 1]) || ((i % 2 != 0) && nums[i] < nums[i + 1])) {
                int temp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = temp;
            }
        }
    }
}
