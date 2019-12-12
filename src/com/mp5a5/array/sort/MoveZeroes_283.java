package com.mp5a5.array.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class MoveZeroes_283 {

    /**
     * 遍历数组，不为0的元素移动数组前方，用index下标记录。
     * 遍历结束，对index值后的元素统一设为0
     * 核心思想：现将不为0的元素重新赋值到该数组中，然后将剩余的元素赋值为0
     */
    public int[] moveZeroes(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
        return nums;
    }


    /**
     * 变量index永远指向已处理好的不为零元素位置
     * 依次遍历数组，每次遇到非零元素，则将index++元素位置出的和非0元素交换，将非0元素置为0
     */
    public int[] moveZeroes1(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
                nums[i] = 0;
            }
        }
        return nums;
    }

    @Test
    public void test() {
        int[] a = {0, 1, 0, 3, 12}; //{1、0、0、3、12} {1、3、0、0、12} {1、2、12、0、0}
        System.out.println(Arrays.toString(moveZeroes1(a)));
    }
}
