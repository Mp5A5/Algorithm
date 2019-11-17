package com.mp5a5.array.interval;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，
 * 同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[0, 30],[5, 10],[15, 20]]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [[7,10],[2,4]]
 * 输出: 1
 */
public class MinMeetingRooms_253 {

    /**
     * 1.按照 开始时间对会议进行排序。
     * 2.初始化一个新的最小堆，将第一个会议的结束时间加入到堆中。我们只需要记录会议的结束时间，告诉我们什么时候房间会空。
     * 3.对每个会议，检查堆的最小元素（即堆顶部的房间）是否空闲。
     * 3.1 若房间空闲，则从堆顶拿出该元素，将其改为我们处理的会议的结束时间，加回到堆中。
     * 3.2 若房间不空闲。开新房间，并加入到堆中。
     * 4.处理完所有会议后，堆的大小即为开的房间数量。这就是容纳这些会议需要的最小房间数。
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        Queue<Integer> canMeetingQueue = new PriorityQueue<>(intervals.length, (a, b) -> a - b);
        canMeetingQueue.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= canMeetingQueue.element()) {
                canMeetingQueue.poll();
            }
            canMeetingQueue.add(intervals[i][1]);
        }
        return canMeetingQueue.size();
    }


    /**
     * 1.分别将开始时间和结束时间存进两个数组。
     * 2.分别对开始时间和结束时间进行排序。请注意，这将打乱开始时间和结束时间的原始对应关系。它们将被分别处理。
     * 3.考虑两个指针： s_ptr 和 e_ptr，分别代表开始指针和结束指针。开始指针遍历每个会议，结束指针帮助我们跟踪会议是否结束。
     * 4.当考虑 s_ptr 指向的特定会议时，检查该开始时间是否大于 e_ptr 指向的会议。若如此，则说明 s_ptr 开始时，已经有会议结束。于是我们可以重用房间。否则，我们就需要开新房间。
     * 5.若有会议结束，换而言之， start[s_ptr] >= end[e_ptr]，则自增 e_ptr。
     * 6.重复这一过程，直到 s_ptr 处理完所有会议。
     */
    public int minMeetingRooms1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        int[] startTime = new int[intervals.length];
        int[] endTime = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            startTime[i] = intervals[i][0];
            endTime[i] = intervals[i][1];
        }

        Arrays.sort(startTime);

        Arrays.sort(endTime);

        int start = 0;
        int end = 0;
        int rooms = 0;
        while (start < intervals.length) {
            if (startTime[start] >= endTime[end]) {
                rooms--;
                end++;
            }
            rooms++;
            start++;
        }
        return rooms;
    }
}
