M
1531595945
tags: Array
time: O(n)
space: O(1)

给一串数字, output rst[n], 每个index是 除了nums[i]以外 所有itemd的乘积.

#### Array
- 分析普通做法, 了结到用从左到右一遍O(n), 从右到左一遍 O(n) 就可以
- 注意carry的维护
- 看第二个解答, 进一步简化了代码

```
/*
Given an array nums of n integers where n > 1,  
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? 
(The output array does not count as extra space for the purpose of space complexity analysis.)
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length <= 1) return new int[]{};

        // check input for len = 1
        int n = nums.length;
        int[] rst = new int[n];
        
        // Bulid from left
        // for (int i = 0; i < n; i++) rst[i] = 1; // init removed for simplification
        rst[0] = 1;
        int carry = nums[0];
        for (int i = 1; i < n; i++) {
            rst[i] = carry;
            carry *= nums[i];
        }

        // Build from right
        carry = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rst[i] *= carry;
            carry *= nums[i];
        }
        
        return rst;
    }
}

// Modulize and Simplification
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length <= 1) return new int[]{};

        // check input for len = 1
        int n = nums.length;
        int[] rst = new int[n];
        
        for (int i = 0; i < n; i++) rst[i] = 1; // init

        int carry = nums[0]; // Bulid from left
        for (int i = 1; i < n; i++) carry = multiply(rst, nums, i, carry);

        carry = nums[n - 1]; // Build from right
        for (int i = n - 2; i >= 0; i--) carry = multiply(rst, nums, i, carry);
        return rst;
    }

    private int multiply(int[] rst, int nums[], int i, int carry) {
        rst[i] *= carry;
        return carry * nums[i];
    }
}
```