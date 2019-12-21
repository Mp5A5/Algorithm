package com.mp5a5.array.sort;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 */
public class SortColors_75 {

    /**
     * 荷兰三色旗问题解
     */
    public void sortColors(int[] nums) {
        // 对于所有 idx < i : nums[idx < i] = 0
        // j是当前考虑元素的下标
        int left = 0;
        int cur = 0;
        int right = nums.length - 1;
        int temp;
        while (cur <= left) {
            if (nums[cur] == 0) {
                // 交换第 left个和第curr个元素
                // left++，cur++
                temp = nums[left];
                nums[left++] = nums[cur];
                nums[cur++] = temp;
            } else if (nums[cur] == 2) {
                // 交换第right个和第curr个元素
                // right--
                temp = nums[cur];
                //因为curr左边的值已经扫描过了，所以curr要++继续扫描下一位，而与p2交换的值，curr未扫描，要停下来扫描一下，所以curr不用++
                nums[cur] = nums[right];
                nums[right--] = temp;
            } else {
                cur++;
            }
        }
    }
}
