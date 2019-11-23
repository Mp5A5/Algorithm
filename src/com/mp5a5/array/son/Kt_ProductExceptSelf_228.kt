package com.mp5a5.array.son

import org.junit.Test
import java.util.*

class Kt_ProductExceptSelf_228 {

    fun productExceptSelf(nums: IntArray): IntArray {

        if (nums.isEmpty()) return IntArray(0) { 0 }
        val res = IntArray(nums.size) { 1 }
        var left = 1
        var right = 1
        for (i in 0 until nums.size) {
            res[i] *= left
            left *= nums[i]
            res[nums.size - 1 - i] *= right
            right *= nums[nums.size - 1 - i];
        }
        return res
    }

    @Test
    fun test() {
        print(Arrays.toString(productExceptSelf(intArrayOf(1, 2, 3, 4))))
    }
}