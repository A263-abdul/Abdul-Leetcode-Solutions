# 279. Perfect Squares

**Difficulty:** Medium 🟡  
**LeetCode:** [Link](https://leetcode.com/problems/perfect-squares/)  
**Category:** Dynamic Programming / BFS / Math  
**Date Solved:** Aug 15, 2026  
**Time Spent:** 40 mins

---

## 📝 Problem Statement

Given an integer `n`, return the **least number of perfect square numbers** that sum to `n`.

A **perfect square** is an integer that is the square of an integer (e.g. `1, 4, 9, 16, ...`).

### Example 1:
```
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4
```

### Example 2:
```
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9
```

### Constraints:
- `1 <= n <= 10^4`

---

## ❌ Why Greedy Doesn't Work

**Greedy idea:** Always pick the largest perfect square ≤ n, subtract it, repeat.

This fails because a locally optimal choice can block a better global solution.

### Counterexample 1 — n = 12

| Strategy | Squares Used | Count |
|----------|-------------|-------|
| ❌ Greedy | 9 + 1 + 1 + 1 | **4** |
| ✅ Optimal | 4 + 4 + 4 | **3** |

Greedy greedily grabs `9`, but that leaves `3` which needs three `1`s. Picking `4` three times is better.

### Counterexample 2 — n = 18

| Strategy | Squares Used | Count |
|----------|-------------|-------|
| ❌ Greedy | 16 + 1 + 1 | **3** |
| ✅ Optimal | 9 + 9 | **2** |

**Conclusion:** Greedy fails because it doesn't consider future consequences → We must use **DP**.

---

## 🎯 Approaches

### Approach 1: Greedy ❌ (WRONG — for understanding only)
**Time:** O(√n × log n) | **Space:** O(1) | **Status:** ❌ Incorrect

```java
while (n > 0) {
    int largest = (int) Math.sqrt(n);
    n -= largest * largest;
    count++;
}
```

**Why it's wrong:** Locally optimal ≠ globally optimal. Fails on n=12, n=18, and many others.

---

### Approach 2: Top-Down DP (Memoization) ✅
**Time:** O(n × √n) | **Space:** O(n) | **Status:** ✅ Accepted

**Recurrence:**
$$dp(n) = 1 + \min_{i \geq 1,\ i^2 \leq n} dp(n - i^2)$$

**Idea:** For every perfect square `i²` ≤ n, recursively find the answer for `n - i²` and take the minimum. Cache results in a `HashMap` to avoid recomputation.

```java
private int helper(int n) {
    if (n == 0) return 0;
    if (memo.containsKey(n)) return memo.get(n);

    int minCount = Integer.MAX_VALUE;
    for (int i = 1; i * i <= n; i++) {
        int result = 1 + helper(n - i * i);
        minCount = Math.min(minCount, result);
    }

    memo.put(n, minCount);
    return minCount;
}
```

**Why it works:** Each unique state `n` is computed exactly once → O(n × √n) total.

---

### Approach 3: Bottom-Up DP (Tabulation) ⭐ BEST
**Time:** O(n × √n) | **Space:** O(n) | **Status:** ✅ Accepted (No recursion overhead)

**Idea:** Build dp table from 0 to n. `dp[i]` = minimum squares summing to `i`.

```java
int[] dp = new int[n + 1];
Arrays.fill(dp, Integer.MAX_VALUE);
dp[0] = 0;

for (int i = 1; i <= n; i++) {
    for (int j = 1; j * j <= i; j++) {
        dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
    }
}
return dp[n];
```

**Why it's best:** Iterative — no recursion stack, no HashMap overhead, cache-friendly.

---

## 🔍 Dry Run — n = 12

| i | Perfect squares ≤ i | dp[i] |
|---|-------------------|-------|
| 0 | — | **0** |
| 1 | 1 | **1** (1) |
| 2 | 1 | **2** (1+1) |
| 3 | 1 | **3** (1+1+1) |
| 4 | 1, 4 | **1** (4) ⭐ |
| 5 | 1, 4 | **2** (4+1) |
| 6 | 1, 4 | **3** (4+1+1) |
| 7 | 1, 4 | **4** (4+1+1+1) |
| 8 | 1, 4 | **2** (4+4) ⭐ |
| 9 | 1, 4, 9 | **1** (9) ⭐ |
| 10 | 1, 4, 9 | **2** (9+1) |
| 11 | 1, 4, 9 | **3** (9+1+1) |
| 12 | 1, 4, 9 | **3** (4+4+4) ⭐ |

> dp[12] = **3** ✅ — greedy would have given 4!

---

## 📊 Complexity Comparison

| Approach | Time | Space | Correct? | Notes |
|----------|------|-------|---------|-------|
| Greedy | O(√n × log n) | O(1) | ❌ Wrong | Counterexamples exist |
| Memoization | O(n × √n) | O(n) | ✅ Yes | HashMap overhead |
| Tabulation ⭐ | O(n × √n) | O(n) | ✅ Yes | Fastest, no recursion |

---

## 💡 Key Learnings

- **Greedy intuition is tempting but wrong** — always test with small counterexamples (n=12, n=18) before committing to greedy
- This problem is **Coin Change in disguise** — coins = {1, 4, 9, 16, 25, ...}, amount = n
- The DP recurrence `dp[i] = min(dp[i - j*j] + 1)` is identical in structure to Coin Change
- **Lagrange's Four-Square Theorem** (math fun fact): every positive integer can be expressed as the sum of at most **4** perfect squares — so the answer is always 1, 2, 3, or 4

---

## ❌ Mistakes I Made

1. **Mistake:** Tried greedy first — thought "biggest square first" would always win  
   **Fix:** Always test counterexamples. n=12 breaks greedy immediately.

2. **Mistake:** Filled dp with `Integer.MAX_VALUE` and did `dp[i-j*j] + 1` → potential overflow  
   **Fix:** Add a guard: `if (dp[i - j*j] != Integer.MAX_VALUE)` before adding 1

---

## 🔗 Similar Problems

- [322. Coin Change](https://leetcode.com/problems/coin-change/) — Medium (exact same DP pattern!)
- [343. Integer Break](https://leetcode.com/problems/integer-break/) — Medium (DP on integer decomposition)
- [91. Decode Ways](https://leetcode.com/problems/decode-ways/) — Medium (DP)

---

## ✍️ Notes

> This problem is a direct cousin of **Coin Change (#322)**:
> - Coin Change: coins = `[1, 5, 10, 25]`, find min coins summing to `amount`
> - Perfect Squares: coins = `[1, 4, 9, 16, ...]`, find min squares summing to `n`
>
> Same recurrence. Same approach. **Master one → master both.**

---

**Status:** ✅ SOLVED | **Attempts:** 2 | **Best Approach:** Approach 3 — Bottom-Up DP Tabulation
