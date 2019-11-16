package com.mp5a5.array.k_sum;

import java.util.Arrays;

/**
 * 给定一个长度为 n 的整数数组和一个目标值 target，寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成立的三元组  i, j, k 个数（0 <= i < j < k < n）。
 * <p>
 * 示例：
 * <p>
 * 输入: nums = [-2,0,1,3], target = 2
 * 输出: 2
 * 解释: 因为一共有两个三元组满足累加和小于 2:
 *      [-2,0,1]
 * [-2,0,3]
 */
public class ThreeSumSmaller_259 {


    /**
     *
     * 在方法二中，我们首先解决了一个简化版的问题，随后在外部套上一层循环，就可以解决当前的问题。如果我们能找出更好的解决简化版的问题的方法，就能在更低的时间复杂度内解决当前的问题。
     *
     * 我们首先仍然对数组进行排序，例如 nums=[3,5,2,8,1] 排序后变成[1,2,3,5,8]。target 的值为 7。
     *
     * [1, 2, 3, 5, 8]
     *  ↑           ↑
     * left       right
     * 我们用两个指针left 和 right 分别指向数组中的第一个和最后一个元素，它们的和为1+8=9>target，这说明right不能出现在二元组中
     * （因为left只向左移动，如果此时二者的和已经大于target，那么在left 移动的过程中，二者的和就不可能小于target 了），因此需要将right 向左移动一位。
     *
     * [1, 2, 3, 5, 8]
     *  ↑        ↑
     * left    right
     * 现在二者的和为 1+5=6<target，那么对于当前的left，应当有right−left=3 对二元组满足要求，它们分别为 (1,2), (1,3), (1,5)(1,2),(1,3),(1,5)。在这之后，我们将left 向右移动一位。
     *
     */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < target) {
                    res += r - l;
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}
