import java.util.*;

/**
 * Problem: Perfect Squares
 * LeetCode Link: https://leetcode.com/problems/perfect-squares/
 * Difficulty: Medium
 * Category: Dynamic Programming / BFS / Math
 *
 * Date Solved: Aug 15, 2026
 * Time Taken: 40 mins
 *
 * Key Insight:
 *   Why Greedy FAILS:
 *   - n=12 → Greedy picks 9+1+1+1 = 4 squares
 *            Optimal is 4+4+4   = 3 squares ❌
 *   - n=18 → Greedy picks 16+1+1 = 3 squares
 *            Optimal is 9+9     = 2 squares ❌
 *   Greedy blocks better global solutions by being locally optimal.
 *   → Must use DP.
 *
 *   DP Recurrence:
 *   dp[n] = 1 + min(dp[n - i*i])  for all i where i*i <= n
 */

class Solution {

    // ========================================
    // APPROACH 1: Greedy (WRONG — for understanding only)
    // Time: O(sqrt(n) * log n) | Space: O(1)
    // Status: ❌ INCORRECT — fails on n=12, n=18, etc.
    // ========================================

    public int numSquaresGreedy(int n) {
        int count = 0;
        while (n > 0) {
            int largest = (int) Math.sqrt(n); // largest perfect square ≤ n
            n -= largest * largest;
            count++;
        }
        return count;
        // ❌ n=12: picks 9→3→1→1→1 = 4, but 4+4+4 = 3 is better
    }


    // ========================================
    // APPROACH 2: Top-Down DP (Memoization with HashMap)
    // Time: O(n * sqrt(n)) | Space: O(n)
    // Status: ✅ Accepted
    //
    // Recurrence: helper(n) = 1 + min(helper(n - i*i)) for each i*i <= n
    // ========================================

    private Map<Integer, Integer> memo = new HashMap<>();

    public int numSquaresMemo(int n) {
        memo.clear();
        return helper(n);
    }

    private int helper(int n) {
        if (n == 0) return 0;                        // base: 0 needs 0 squares
        if (memo.containsKey(n)) return memo.get(n); // cached

        int minCount = Integer.MAX_VALUE;

        for (int i = 1; i * i <= n; i++) {
            int result = 1 + helper(n - i * i);      // pick square i*i, recurse
            minCount = Math.min(minCount, result);
        }

        memo.put(n, minCount);
        return minCount;
    }


    // ========================================
    // APPROACH 3: Bottom-Up DP (Tabulation) ⭐ BEST
    // Time: O(n * sqrt(n)) | Space: O(n)
    // Status: ✅ Accepted (No recursion stack overhead)
    //
    // dp[i] = minimum number of perfect squares that sum to i
    // dp[0] = 0 (base case)
    // dp[i] = min(dp[i - j*j] + 1) for all j where j*j <= i
    // ========================================

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 0 needs 0 squares

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (dp[i - j * j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
        }

        return dp[n];
    }
}
