package com.mp5a5.array.sort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [3,1,3,4,2]
 * 输出: 3
 */
public class FindDuplicate_287 {

    public int findDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                int count = map.get(num);
                map.put(num, count + 1);
            }
        }

        int temp = 0;
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                temp = entry.getKey();
            }
        }
        return temp;
    }

    // 使用循环链表

    /**
     * 使用环形链表II的方法解题（142.环形链表II）,使用142题的思想来解决此题的关键是要理解如何将输入的数组看作为链表。
     * 首先明确前提,整数的数组 nums中的数字范围是[1,n]。考虑一下两种情况：
     * <p>
     * 如果数组中没有重复的数，以数组[1,3,4,2]为例，我们将数组下标n和数nums[n]建立一个映射关系f(n)，
     * 其映射关系n->f(n)为：
     * 0->1
     * 1->3
     * 2->4
     * 3->2
     * 我们从下标为0出发，根据f(n)计算出一个值，以这个值为新的下标，再用这个函数计算，以此类推，直到下标超界。这样可以产生一个类似链表一样的序列。
     * 0->1->3->2->4->null
     * <p>
     * 如果数组中有重复的数，以数组[1,3,4,2,2]为例,我们将数组下标n和数nums[n]建立一个映射关系f(n)，
     * 其映射关系n->f(n)为：
     * 0->1
     * 1->3
     * 2->4
     * 3->2
     * 4->2
     * 同样的，我们从下标为0出发，根据f(n)计算出一个值，以这个值为新的下标，再用这个函数计算，以此类推产生一个类似链表一样的序列。
     * 0->1->3->2->4->2->4->2->……
     */
    public int findDuplicate1(int[] nums) {
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int pre1 = 0;
        int pre2 = slow;
        while (pre1 != pre2) {
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre2;
    }


    public int findDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int value : nums) {
            if (set.contains(value)) {
                return value;
            }
            set.add(value);
        }
        return -1;
    }
}
