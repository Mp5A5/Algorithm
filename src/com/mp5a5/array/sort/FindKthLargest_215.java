package com.mp5a5.array.sort;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 */
public class FindKthLargest_215 {


    @Test
    public void main() {
        int[] temp = {3, 2, 1, 5, 6, 4, 9, 7};
        System.out.println(findKthLargest(temp, 4));
    }


    public int findKthLargest(int[] nums, int k) {

        // 构建最小堆
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustMinHeap(nums, i, nums.length);
        }

        for (int i = nums.length - 1; i > 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            adjustMinHeap(nums, 0, i);
        }


        return nums[k - 1];
    }


    public void adjustMinHeap(int[] array, int i, int length) {
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if ((k + 1 < length) && array[k] > array[k + 1]) {
                k++;
            }
            if (array[k] < temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    public int findKthLargest1(int[] nums, int k) {

        // 构建最小堆
        Queue<Integer> heap = new PriorityQueue<>((x, y) -> x - y);

        for (int value : nums) {
            heap.add(value);
            if (heap.size() > k) heap.poll();
        }


        return heap.poll();
    }
}
