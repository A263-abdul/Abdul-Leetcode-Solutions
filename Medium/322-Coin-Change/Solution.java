import java.util.Arrays;

/**
 * Problem: Coin Change
 * LeetCode Link: https://leetcode.com/problems/coin-change/
 * Difficulty: Medium
 * Category: Dynamic Programming
 *
 * Date Solved: Aug 15, 2026
 * Time Taken: 45 mins
 */

class Solution {

    // ========================================
    // APPROACH 1: Pure Recursion (Brute Force)
    // Time: O(S^n) | Space: O(n) recursive stack
    // Status: ❌ Time Limit Exceeded
    // ========================================

    public int coinChangeRecursive(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = coinChangeRecursive(coins, amount - coin);
            if (result >= 0 && result < minCoins) {
                minCoins = result + 1;
            }
        }
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }


    // ========================================
    // APPROACH 2: Top-Down DP (Memoization)
    // Time: O(S * n) | Space: O(S)
    // Status: ✅ Accepted
    // ========================================

    int[] memo;

    public int coinChangeMemo(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo, -2); // -2 = unvisited
        return dpMemo(coins, amount);
    }

    private int dpMemo(int[] coins, int rem) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (memo[rem] != -2) return memo[rem];
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = dpMemo(coins, rem - coin);
            if (result >= 0 && result < minCoins) {
                minCoins = result + 1;
            }
        }
        memo[rem] = (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
        return memo[rem];
    }


    // ========================================
    // APPROACH 3: Bottom-Up DP (Tabulation) ⭐ BEST
    // Time: O(S * n) | Space: O(S)
    // Status: ✅ Accepted (Most Efficient — no recursion overhead)
    // ========================================

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // fill with impossible value
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
