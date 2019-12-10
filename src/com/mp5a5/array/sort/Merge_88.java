package com.mp5a5.array.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 */
public class Merge_88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    // 双指针 / 从前往后--返回nums1
    public int[] merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[m];
        System.arraycopy(nums1, 0, res, 0, m);
        System.out.println(Arrays.toString(res));
        int p = 0;//res指针
        int p1 = 0;// nums1坐标值
        int p2 = 0;// nums2指针
        while ((p < m) && (p2 < n)) {
            nums1[p1++] = res[p] < nums2[p2] ? res[p++] : nums2[p2++];
        }
        if (p < m) {
            System.arraycopy(res, p, nums1, p + p2, m + n - p - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p + p2, m + n - p - p2);
        }
        return nums1;
    }

    // 双指针 / 从前往后--返回新数组
    public int[] merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[m + n];
        int p = 0;// res数组下标索引
        int p1 = 0;//nums1的指针
        int p2 = 0;//nums2的指针
        while ((p1 < m) && (p2 < n)) {
            res[p++] = nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        }
        if (p1 < m) {
            System.arraycopy(nums1, p1, res, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, res, p1 + p2, m + n - p1 - p2);
        }
        return res;
    }

    // 双指针 / 从后往前
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while ((p1 >= 0) && (p2 >= 0)) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3, 0, 0, 0};
        int[] b = {2, 5, 6};
        System.out.println(Arrays.toString(merge2(a, 3, b, 3)));
    }
}
