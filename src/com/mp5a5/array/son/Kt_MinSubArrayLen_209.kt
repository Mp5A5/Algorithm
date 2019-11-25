package com.mp5a5.array.son

class Kt_MinSubArrayLen_209 {

    fun minSubArrayLen(s: Int, nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var left = 0
        var right = 0
        var len = Int.MAX_VALUE
        var sum = 0
        while (right < nums.size) {
            if (nums[right] + sum < s) {
                sum += nums[right++]
            } else {
                if (right - left < len) {
                    len = right - left + 1
                }
                sum -= nums[left++]
            }
        }
        return if (len == Int.MAX_VALUE) 0 else len
    }
}