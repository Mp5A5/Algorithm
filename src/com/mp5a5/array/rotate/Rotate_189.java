package com.mp5a5.array.rotate;

import org.junit.Test;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * <p>
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 */
public class Rotate_189 {

    // 暴力算法
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return;
        for (int i = 0; i < k; i++) {
            int temp = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }

    // 额外数组
    public void rotate1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 精髓算法
            res[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = res[i];
        }
    }

    // 使用环状替换

    /**
     * 如果我们直接把每一个数字放到它最后的位置，但这样的后果是遗失原来的元素。因此，我们需要把被替换的数字保存在变量 temp 里面。然后，我们将被替换数字（temp）放到它正确的位置，并继续这个过程 n 次， n 是数组的长度。
     * 这是因为我们需要将数组里所有的元素都移动。但是，这种方法可能会有个问题，如果 n\%k==0，其中 k=k%n （因为如果 k 大于 n ，移动 k 次实际上相当于移动 k%n 次）。这种情况下，我们会发现在没有遍历所有数字的情况下回到出发数字。
     * 此时，我们应该从下一个数字开始再重复相同的过程。
     * <p>
     * 现在，我们看看上面方法的证明。假设，数组里我们有 n 个元素并且 k 是要求移动的次数。更进一步，假设 n%k=0 。第一轮中，所有移动数字的下标 i 满足 i%k==0 。这是因为我们每跳 k 步，我们只会到达相距为 k 个位置下标的数。
     * 每一轮，我们都会移动 n/k个元素。下一轮中，我们会移动满足 i%k==1 的位置的数。这样的轮次会一直持续到我们再次遇到i%k==0 的地方为止，此时 i=k 。此时在正确位置上的数字共有 n/(n×k)个。因此所有数字都在正确位置上。
     */

    /**
     * 环状替代的思路:把元素看做同学，把下标看做座位，大家换座位。第一个同学离开座位去第k+1个座位，第k+1个座位的同学被挤出去了，他就去坐他后k个座位，
     * 如此反复。但是会出现一种情况，就是其中一个同学被挤开之后，坐到了第一个同学的位置（空位置，没人被挤出来），但是此时还有人没有调换位置，
     * 这样就顺着让第二个同学换位置。 那么什么时候就可以保证每个同学都换完了呢？n个同学，换n次，所以用一个count来计数即可。
     */

    // 环状替换确实比较难理解。如果把数组的数据放在正多边形上，以走跳棋的思路替换数据，画个图就好理解了。 假如 n=5, k=2, 数字1-5依次放在五边形顶点，数字替换的轨迹是1-3-5-2-4-1，回到原点，count = n， 结束。
    // 轨迹画出来刚好是个五角星。 假如 n=6, k=2, 数字1-6依次放在六边形顶点，数字替换的轨迹是1-3-5-1，回到原点了，count < n, start++, 接着 2-4-6-2，回到原点，count = n, 结束。轨迹是六边形的2个内嵌正三角形。
    //其它多边形类似，隔k个点往前走，总能走回原点，如果中间有漏的，旋转一个角的方位重复进行上述步骤就能走完所有的顶点了。
    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return;
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int pre = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    // 反转法

    /**
     * 算法
     * <p>
     * 这个方法基于这个事实：当我们旋转数组 k 次，k%n 个尾部元素会被移动到头部，剩下的元素会被向后移动。
     * <p>
     * 在这个方法中，我们首先将所有元素反转。然后反转前 k 个元素，再反转后面 n−k 个元素，就能得到想要的结果。
     * <p>
     * 假设 n=7 且 k=3 。
     * <p>
     * 原始数组                  : 1 2 3 4 5 6 7
     * 反转所有数字后             : 7 6 5 4 3 2 1
     * 反转前 k 个数字后          : 5 6 7 4 3 2 1
     * 反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果
     */
    public void rotate3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return;
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


    @Test
    public void test() {
        System.out.println(1 / 4);
        System.out.println(1 % 4);
    }

}
