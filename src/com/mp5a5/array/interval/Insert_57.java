package com.mp5a5.array.interval;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 示例 2:
 * <p>
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class Insert_57 {

    // O(n^2)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new LinkedList<>();
        if (intervals == null || intervals.length == 0) {
            return res.toArray(new int[0][]);
        }
        List<int[]> temp = new LinkedList<>(Arrays.asList(intervals));
        temp.add(newInterval);
        temp.sort((a, b) -> a[0] - b[0]);
        int index = 0;
        while (index < temp.size()) {
            int left = temp.get(index)[0];
            int right = temp.get(index)[1];
            while (index < temp.size() - 1 && right >= temp.get(index + 1)[0]) {
                right = Math.max(right, temp.get(index + 1)[1]);
                index++;
            }
            res.add(new int[]{left, right});
            index++;
        }
        return res.toArray(new int[res.size()][]);
    }

    // O(nlogn)
    public int[][] insert1(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> res = new LinkedList<>();
        if (intervals == null || intervals.length == 0) {
            return res.toArray(new int[0][]);
        }
        List<int[]> temp = new LinkedList<>(Arrays.asList(intervals));
        temp.add(newInterval);
        temp.sort((a, b) -> a[0] - b[0]);

        for (int[] ints : temp) {
            if (res.isEmpty() || res.getLast()[1] < ints[0]) {
                res.add(ints);
            } else {
                res.getLast()[1] = Math.max(res.getLast()[1], ints[1]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    @Test
    public void test() {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};

        int[] newInterval = {4, 8};
        System.out.println(Arrays.toString(insert1(intervals, newInterval)));
    }
}
