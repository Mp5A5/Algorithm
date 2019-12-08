package com.mp5a5.array.rotate;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * <p>
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 */
public class Search_81 {

    /**
     * 本题是需要使用二分查找，怎么分是关键，举个例子：
     * <p>
     * 第一类
     * 10111和11101 这种。此种情况下 nums[left] == nums[mid]==nums[right]，分不清到底是前面有序还是后面有序，此时 left++,right-- 即可。相当于去掉一个重复的干扰项。
     * 第二类
     * 2 3 4 5 6 7 1 这种，也就是 nums[left] < nums[mid]。此例子中就是 2 < 5；
     * 这种情况下，前半部分有序。因此如果 nums[left] < target < nums[mid]，则在前半部分找，否则去后半部分找。
     * 第三类
     * 6 7 1 2 3 4 5 这种，也就是 nums[left] > nums[mid]。此例子中就是 6 > 2；
     * 这种情况下，后半部分有序。因此如果 nums[mid] < target <=nums[right]。则在后半部分找，否则去前半部分找。
     */
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (mid == target) return true;
            // 想不33题只是做了去重操作
            if (nums[left] == nums[mid] && nums[right] == nums[mid]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) { //前半部分有序
                if (nums[left] <= target && target < nums[mid]) {  //target在前半部分
                    right = mid - 1;
                } else {//否则，去后半部分找
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {//target在后半部分
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}
