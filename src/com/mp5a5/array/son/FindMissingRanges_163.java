package com.mp5a5.array.son;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个排序的整数数组 nums ，其中元素的范围在闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 * <p>
 * 示例：
 * <p>
 * 输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
 * 输出: ["2", "4->49", "51->74", "76->99"]
 */
public class FindMissingRanges_163 {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        long left = lower;
        long right = upper;
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            //如果长度为0，直接把l，r加入即可
            add(res, left - 1, right + 1);
            return res;
        }

        add(res, left - 1, nums[0]);

        for (int i = 1; i < nums.length; i++) {
            add(res, nums[i - 1], nums[i]);
        }

        add(res, nums[nums.length - 1], right + 1);

        return res;
    }

    private void add(List<String> res, long left, long right) {
        if (left == right || left + 1 == right) {
            return;
        } else if (left + 1 == right - 1) {
            res.add(String.valueOf(left + 1));
        } else {
            res.add((left + 1) + "->" + (right - 1));
        }
    }


    public List<String> findMissingRanges1(int[] nums, int lower, int upper) {

        if (nums == null || nums.length == 0) {
            nums = new int[]{lower - 1, upper + 1};
        } else {
            int[] temp = new int[nums.length + 2];
            System.arraycopy(nums, 0, temp, 1, temp.length - 2);
            temp[0] = lower - 1;
            temp[temp.length - 1] = upper + 1;
            nums = temp;
        }

        int left = 0;
        int right = left + 1;
        List<String> res = new ArrayList<>();
        while (right < nums.length) {
            long vl = nums[left];
            long vr = nums[right];
            if (vr - vl == 2) {
                res.add(String.valueOf(vl + 1));
            } else if (vr - vl > 2) {
                res.add((vl + 1) + "->" + (vr - 1));
            }
            ++left;
            ++right;
        }
        return res;
    }
}
