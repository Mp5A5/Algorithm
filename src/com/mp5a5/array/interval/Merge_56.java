package com.mp5a5.array.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Merge_56 {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            return res.toArray(new int[0][]);
        }
        // 根据二维数组第一个数字大小按每一行整体排序
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        int index = 0;
        while (index < intervals.length) {
            int left = intervals[index][0];
            int right = intervals[index][1];
            while (index < intervals.length - 1 && right >= intervals[index + 1][0]) {
                index++;
                right = Math.max(right, intervals[index][1]);//由于index++已经++了所以比较的是下一个元素
            }
            res.add(new int[]{left, right});
            index++;
        }
        return res.toArray(new int[0][]);

    }

    public int[][] merge1(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        if (intervals == null || intervals.length == 0) {
            return res.toArray(new int[0][]);
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for (int[] interval : intervals) {
            if (res.isEmpty() || res.getLast()[1] < interval[0]) {
                res.add(interval);
            } else {
                res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
            }
        }
        return res.toArray(new int[0][0]);
    }
}
