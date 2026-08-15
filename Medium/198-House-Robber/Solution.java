import java.util.Arrays;

/**
 * Problem: House Robber
 * LeetCode Link: https://leetcode.com/problems/house-robber/
 * Difficulty: Medium
 * Category: Dynamic Programming
 *
 * Date Solved: Aug 15, 2026
 * Time Taken: 35 mins
 */

class Solution {

    // ========================================
    // APPROACH 1: Pure Recursion
    // Time: O(2^n) | Space: O(n) recursive stack
    // Status: ❌ Time Limit Exceeded
    // ========================================

    public int robRecursive(int[] nums) {
        return robHelper(nums, nums.length - 1);
    }

    private int robHelper(int[] nums, int i) {
        if (i < 0) return 0;
        return Math.max(robHelper(nums, i - 1),
                        robHelper(nums, i - 2) + nums[i]);
    }


    // ========================================
    // APPROACH 2: Top-Down DP (Memoization)
    // Time: O(n) | Space: O(n)
    // Status: ✅ Accepted
    // ========================================

    int[] memo;

    public int robMemo(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return robMemoHelper(nums, nums.length - 1);
    }

    private int robMemoHelper(int[] nums, int i) {
        if (i < 0) return 0;
        if (memo[i] >= 0) return memo[i];
        memo[i] = Math.max(robMemoHelper(nums, i - 1),
                           robMemoHelper(nums, i - 2) + nums[i]);
        return memo[i];
    }


    // ========================================
    // APPROACH 3: Bottom-Up DP (Space Optimized) ⭐ BEST
    // Time: O(n) | Space: O(1)
    // Status: ✅ Accepted (Most Efficient)
    // ========================================

    public int rob(int[] nums) {
        int prev2 = 0; // dp[i-2]
        int prev1 = 0; // dp[i-1]

        for (int num : nums) {
            int current = Math.max(prev1, prev2 + num);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}
