package com.mp5a5.array.son;


import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [0,1,2,4,5,7]
 * 输出: ["0->2","4->5","7"]
 * 解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
 * 示例 2:
 * <p>
 * 输入: [0,2,3,4,6,8,9]
 * 输出: ["0","2->4","6","8->9"]
 * 解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
 */
public class SummaryRanges_228 {
    /**
     * 官方解法：为了得出这些区间，我们需要找到一种方法将它们分开。题目所给出的数组是有序的，同时还没有重复元素。在这样的数组里面，
     * 两个相邻的元素的差值要么等于 1 要么大于 1。对于那些差值等于 1 的就将它们被放在同一段区间内；否则，就将把它们放在不同的区间。
     * 我们还需要知道的是一段区间的起始坐标，这样就可以把它们放进结果里面了。因此，我们需要保存两个坐标，分别代表一段区间的两个分界点。
     * 对于遍历到的每个新元素来说，检查一下它是否可以拓展当前的区间，如果不能，就把当前的元素作为一个新的区间的开始。
     * 不要忘记把最后一段区间也放进结果里面。这个逻辑很容易实现，你可以在循环里通过一个特定的判断条件来加入或者在循环结束后加入。
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null) return res;
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            while (j + 1 < nums.length && nums[j + 1] == nums[j] + 1) j++;
            if (i == j) {
                res.add(nums[i] + "");
            } else {
                res.add(nums[i] + "->" + nums[j]);
            }
            i = j + 1;
            j++;
        }
        return res;
    }

    public List<String> summaryRanges1(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null) return res;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i + 1] == nums[i] + 1) {
                count++;
            } else {
                if (count == 0) {
                    res.add(nums[i] + "");
                } else {
                    res.add(nums[i - count] + "->" + nums[i]);
                }
                count = 0;
            }
        }
        return res;
    }
}
