package com.mp5a5.array.interval;

import java.util.Arrays;

/**
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，请你判断一个人是否能够参加这里面的全部会议。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[0,30],[5,10],[15,20]]
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: [[7,10],[2,4]]
 * 输出: true
 */
public class CanAttendMeetings_252 {
    public boolean canAttendMeetings(int[][] intervals) {

        if (intervals == null || intervals.length == 0) {
            return false;
        }
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] >= intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }
}
