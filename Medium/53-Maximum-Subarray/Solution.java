/**
 * Problem: LeetCode 53 - Maximum Subarray
 * LeetCode Link: https://leetcode.com/problems/maximum-subarray/
 * Difficulty: Medium
 * Category: Divide and Conquer / Dynamic Programming
 *
 * Date Solved: Aug 20, 2026
 */
class Solution {

    // ========================================
    // APPROACH 1: Divide and Conquer ⭐ BEST (Today's Approach)
    // Time: O(n log n) | Space: O(log n) — recursion stack
    // ========================================
    // Split the array at the midpoint.
    // The answer is one of three cases:
    //   1. Entirely in the LEFT half   → recurse left
    //   2. Entirely in the RIGHT half  → recurse right
    //   3. CROSSES the midpoint        → compute with maxCrossingMid()
    // Take the max of all three.
    public int maxSubArray(int[] nums) {
        return divideConquer(nums, 0, nums.length - 1);
    }

    private int divideConquer(int[] nums, int left, int right) {
        // Base case: single element
        if (left == right) return nums[left];

        int mid = left + (right - left) / 2;

        int leftMax  = divideConquer(nums, left, mid);
        int rightMax = divideConquer(nums, mid + 1, right);
        int midMax   = maxCrossingMid(nums, left, mid, right);

        return Math.max(Math.max(leftMax, rightMax), midMax);
    }

    // Finds the maximum sum of a subarray that MUST cross the midpoint.
    // Expand left from mid, expand right from mid+1 — combine the best of each side.
    private int maxCrossingMid(int[] nums, int left, int mid, int right) {
        // Expand LEFT from mid → go leftward
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }

        // Expand RIGHT from mid+1 → go rightward
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }

        return leftSum + rightSum;
    }


    // ========================================
    // APPROACH 2: Kadane's Algorithm ✅ (Classic Optimal)
    // Time: O(n) | Space: O(1)
    // ========================================
    // At each index, decide: extend current subarray or start fresh from here?
    // Keep track of the running max.
    public int maxSubArrayKadane(int[] nums) {
        int currentSum = nums[0];
        int maxSum     = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Start fresh from nums[i] if current subarray is dragging us down
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }


    // ========================================
    // APPROACH 3: Brute Force
    // Time: O(n²) | Space: O(1)
    // ========================================
    // Try every subarray starting at i, extending to j.
    // TLE on large inputs.
    public int maxSubArrayBrute(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}
