# 322. Coin Change

**Difficulty:** Medium 🟡  
**LeetCode:** [Link](https://leetcode.com/problems/coin-change/)  
**Category:** Dynamic Programming  
**Date Solved:** Aug 15, 2026  
**Time Spent:** 45 mins

---

## 📝 Problem Statement

You are given an integer array `coins` representing coins of different denominations and an integer `amount` representing a total amount of money. Return the fewest number of coins needed to make up that amount. If that amount of money cannot be made up by any combination of the coins, return `-1`.

### Example 1:
```
Input: coins = [1,5,10], amount = 11
Output: 2
Explanation: 10 + 1 = 11 → 2 coins
```

### Example 2:
```
Input: coins = [2], amount = 3
Output: -1
Explanation: Cannot form 3 with only coin 2
```

### Constraints:
- `1 <= coins.length <= 12`
- `1 <= coins[i] <= 2^31 - 1`
- `0 <= amount <= 10^4`

---

## 🎯 Approaches

### Approach 1: Pure Recursion ❌ (Brute Force)
**Time:** O(S^n) | **Space:** O(n) | **Status:** ❌ Time Limit Exceeded

**Idea:** Try every possible combination recursively. At each step subtract each coin from amount.

```java
public int coinChangeRecursive(int[] coins, int amount) {
    if (amount == 0) return 0;
    if (amount < 0) return -1;
    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
        int result = coinChangeRecursive(coins, amount - coin);
        if (result >= 0 && result < min) min = result + 1;
    }
    return min == Integer.MAX_VALUE ? -1 : min;
}
```

**Why it fails:** Exponential time — computes same subproblems over and over.

---

### Approach 2: Top-Down DP (Memoization) ✅
**Time:** O(S × n) | **Space:** O(S) | **Status:** ✅ Accepted

**Idea:** Same as recursion but cache results in a `memo[]` array to avoid recomputation.

```java
int[] memo = new int[amount + 1];
Arrays.fill(memo, -2); // -2 = unvisited
// On each call: if memo[rem] != -2 → return cached result
```

**Why it works:** Eliminates repeated subproblems — reduces to linear time.

---

### Approach 3: Bottom-Up DP (Tabulation) ⭐ BEST
**Time:** O(S × n) | **Space:** O(S) | **Status:** ✅ Accepted (No recursion overhead)

**Idea:** Build a dp array from 0 to amount. `dp[i]` = min coins to make amount `i`.

```java
int[] dp = new int[amount + 1];
Arrays.fill(dp, amount + 1); // fill with "impossible" sentinel
dp[0] = 0;
for (int i = 1; i <= amount; i++) {
    for (int coin : coins) {
        if (coin <= i) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
    }
}
return dp[amount] > amount ? -1 : dp[amount];
```

**Why it's best:** Iterative → no stack overflow risk. Same complexity as memoization but faster in practice.

---

## 📊 Complexity Comparison

| Approach | Time | Space | Pros | Cons |
|----------|------|-------|------|------|
| Recursion | O(S^n) | O(n) | Simple to write | Exponential — TLE |
| Memoization | O(S×n) | O(S) | Easy top-down | Recursion overhead |
| Tabulation ⭐ | O(S×n) | O(S) | Iterative, fast | Slightly more complex |

---

## 💡 Key Learnings

- **DP Pattern:** `dp[i] = min(dp[i], dp[i - coin] + 1)` for each coin
- Fill dp array with `amount + 1` as "impossible" sentinel (not Integer.MAX_VALUE — overflow!)
- Bottom-up is almost always preferred over memoization for DP

---

## ❌ Mistakes I Made

1. **Mistake:** Filled dp array with `Integer.MAX_VALUE` then did `+1` → integer overflow!  
   **Fix:** Use `amount + 1` as the impossible sentinel value

2. **Mistake:** Forgot `dp[0] = 0` base case  
   **Fix:** Always initialize the base case first

---

## 🔗 Similar Problems

- [Perfect Squares](https://leetcode.com/problems/perfect-squares/) — Medium
- [Minimum Cost For Tickets](https://leetcode.com/problems/minimum-cost-for-tickets/) — Medium
- [Coin Change II](https://leetcode.com/problems/coin-change-ii/) — Medium

---

**Status:** ✅ SOLVED | **Attempts:** 3 | **Best Approach:** Approach 3 — Bottom-Up Tabulation
