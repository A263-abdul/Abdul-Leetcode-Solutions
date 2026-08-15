/**
 * Problem: Fibonacci Number
 * LeetCode Link: https://leetcode.com/problems/fibonacci-number/
 * Difficulty: Easy
 * Category: Recursion / Dynamic Programming / Math
 *
 * Date Solved: Aug 15, 2026
 * Time Taken: 20 mins
 */

class Solution {

    // ========================================
    // APPROACH 1: Pure Recursion
    // Time: O(2^n) | Space: O(n)
    // Status: ❌ TLE for large n
    // ========================================

    public int fibRecursive(int n) {
        if (n <= 1) return n;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }


    // ========================================
    // APPROACH 2: DP Memoization (Top-Down)
    // Time: O(n) | Space: O(n)
    // Status: ✅ Accepted
    // ========================================

    int[] memo;

    public int fibMemo(int n) {
        memo = new int[n + 1];
        java.util.Arrays.fill(memo, -1);
        return dpMemo(n);
    }

    private int dpMemo(int n) {
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n];
        memo[n] = dpMemo(n - 1) + dpMemo(n - 2);
        return memo[n];
    }


    // ========================================
    // APPROACH 3: Space-Optimized Iteration ⭐ BEST
    // Time: O(n) | Space: O(1)
    // Status: ✅ Accepted (Most Efficient)
    // ========================================

    public int fib(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}
