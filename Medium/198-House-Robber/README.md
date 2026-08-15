# 198. House Robber

**Difficulty:** Medium 🟡  
**LeetCode:** [Link](https://leetcode.com/problems/house-robber/)  
**Category:** Dynamic Programming  
**Date Solved:** Aug 15, 2026  
**Time Spent:** 35 mins

---

## 📝 Problem Statement

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. Adjacent houses have security systems connected — if two adjacent houses are broken into on the same night, it will alert the police. Given an integer array `nums` representing the amount of money at each house, return the maximum amount you can rob tonight without alerting the police.

### Example 1:
```
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) then rob house 3 (money = 3). Total = 1 + 3 = 4.
```

### Example 2:
```
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (2), 3 (9), 5 (1). Total = 12.
```

### Constraints:
- `1 <= nums.length <= 100`
- `0 <= nums[i] <= 400`

---

## 🎯 Approaches

### Approach 1: Pure Recursion ❌
**Time:** O(2^n) | **Space:** O(n) | **Status:** ❌ TLE

**Idea:** At each house, decide to rob or skip. Take max of both options recursively.

```java
private int robHelper(int[] nums, int i) {
    if (i < 0) return 0;
    return Math.max(robHelper(nums, i - 1), robHelper(nums, i - 2) + nums[i]);
}
```

**Why it fails:** Re-calculates the same states exponentially many times.

---

### Approach 2: DP Memoization ✅
**Time:** O(n) | **Space:** O(n) | **Status:** ✅ Accepted

**Idea:** Cache subproblem results to avoid recalculation.

```java
int[] memo = new int[nums.length];
Arrays.fill(memo, -1);
// If memo[i] != -1, return cached result directly
```

**Why it works:** Each state only computed once — O(n) total.

---

### Approach 3: Space-Optimized DP ⭐ BEST
**Time:** O(n) | **Space:** O(1) | **Status:** ✅ Accepted (Most Efficient)

**Idea:** Only need the two previous values at any point, so use two variables instead of an array.

```java
int prev2 = 0, prev1 = 0;
for (int num : nums) {
    int current = Math.max(prev1, prev2 + num);
    prev2 = prev1;
    prev1 = current;
}
return prev1;
```

**Why it's best:** O(1) space — no array needed. Same logic, just uses rolling variables.

---

## 📊 Complexity Comparison

| Approach | Time | Space | Pros | Cons |
|----------|------|-------|------|------|
| Recursion | O(2^n) | O(n) | Intuitive | Exponential — TLE |
| Memoization | O(n) | O(n) | Easy top-down | Uses O(n) space |
| Space-Optimized ⭐ | O(n) | O(1) | Optimal in every way | Slightly less obvious |

---

## 💡 Key Learnings

- **DP Recurrence:** `dp[i] = max(dp[i-1], dp[i-2] + nums[i])`
- Many DP problems only need the last 2 values → use variables, not array
- Think "what decisions do I have at each step?" → guides recurrence

---

## ❌ Mistakes I Made

1. **Mistake:** Forgot to handle the case when `i < 0` in recursion  
   **Fix:** Always add your base cases first before diving into logic

---

## 🔗 Similar Problems

- [House Robber II](https://leetcode.com/problems/house-robber-ii/) — Medium
- [House Robber III](https://leetcode.com/problems/house-robber-iii/) — Medium
- [Delete and Earn](https://leetcode.com/problems/delete-and-earn/) — Medium

---

**Status:** ✅ SOLVED | **Attempts:** 2 | **Best Approach:** Approach 3 — Space-Optimized DP
