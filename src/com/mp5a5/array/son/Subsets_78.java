package com.mp5a5.array.son;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */

public class Subsets_78 {
    /*
     * 对于[1,2,3]，可用三位二进制表示是否选择对应下标的数组元素。则有8种组合方式。
     * <p>
     * 二进制
     * 对应数字	1	2	3	对应子集
     *      0	0	0	0	[]
     *      1	0	0	1	[3]
     *      2	0	1	0	[2]
     *      3	0	1	1	[1,2]
     *      4	1	0	0	[1]
     *      5	1	0	1	[1,3]
     *      6	1	1	0	[2,3]
     *      7	1	1	1	[1,2,3]
     *
     * <p>初始化数组长度n，最终结果的长度res_len=1<< n，此处位运算表示的是2^n
     *
     * <p>对于每种结果，对于i在遍历区间[0,res_len)中:
     *
     * <p><p>初始化中间结果cur=[]
     * <p><p>从数组第一位到最后一位进行遍历，对于j在遍历区间[0,n)中：
     * <p><p><p>若满足条件i >> j &1，表示第j位是否为1，若满足，则将该位元素加入中间结果cur中
     * <p><p>将cur加入res
     * <p>返回res
     *
     * 复杂度分析
     * 时间复杂度：O(2^n*n)
     *
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null) return res;

        int total = 1 << nums.length;
        for (int cont = 0; cont < total; cont++) {
            List<Integer> sublist = new LinkedList<>();
            for (int index = 0; index < nums.length; index++) {
                // 获取每一位二进制数（选择状态）
                int status = (cont >> index) & 1;
                if (status == 1) {
                    sublist.add(nums[index]);
                }
            }
            res.add(sublist);
        }
        return res;
    }

    // 回溯法
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;
        helper(nums, 0, res, new LinkedList<Integer>());
        return res;
    }

    private void helper(int[] nums, int index, List<List<Integer>> res, LinkedList<Integer> cur) {
        res.add(new LinkedList<Integer>(cur));
        for (int i = index; i < nums.length; i++) {
            cur.add(nums[i]);
            helper(nums, i + 1, res, cur);
            cur.remove(cur.size() - 1);
        }
    }
}
