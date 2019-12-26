package com.mp5a5.array.sort;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * <p>
 * Example:
 * <p>
 * Input: nums = [3,5,2,1,6,4]
 * <p>
 * Output: One possible answer is [3,5,1,6,2,4]
 * <p>
 * 规则：
 * // index为偶数，则nums[index]按照要求一定小于nums[index+1]
 * // index为奇数，则nums[index]按照要求一定大于nums[index+1]
 */
public class WiggleSort_280 {

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        }
    }

    public void wiggleSort1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (((i % 2 == 0) && nums[i] > nums[i + 1]) || (i % 2 != 0) && nums[i] < nums[i + 1]) {
                int temp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = temp;
            }
        }
    }
}
