# 2224. Convert Time by Adding or Subtracting Operations

**Difficulty:** Easy 🟢  
**LeetCode:** [Link](https://leetcode.com/problems/convert-time-by-adding-or-subtracting-operations/)  
**Category:** Greedy / Math  
**Date Solved:** Aug 15, 2026  
**Time Spent:** 20 mins

---

## 📝 Problem Statement

You are given two strings `current` and `correct` representing two 24-hour times. Return the minimum number of operations needed to convert `current` to `correct`. You can add or subtract `1`, `5`, `15`, or `60` minutes per operation.

### Example 1:
```
Input: current = "02:30", correct = "04:35"
Output: 3
Explanation: Add 60 min + 60 min + 5 min → 3 operations
```

### Example 2:
```
Input: current = "11:00", correct = "11:01"
Output: 1
Explanation: Add 1 minute → 1 operation
```

### Constraints:
- `current` and `correct` are in the format `"HH:MM"`
- `current <= correct`

---

## 🎯 Approaches

### Approach 1: Brute Force Simulation ❌
**Time:** O(S) — S = time diff in minutes | **Space:** O(1) | **Status:** ❌ Slow

**Idea:** Increment current time by 1 minute at a time until it matches correct.

**Why it fails:** Can take up to 1440 steps — not truly wrong, just not optimal.

---

### Approach 2: Greedy with Denominations ✅
**Time:** O(1) | **Space:** O(1) | **Status:** ✅ Accepted

**Idea:** Greedily use largest increments first: 60 → 15 → 5 → 1. Like making change with coins.

```java
int[] increments = {60, 15, 5, 1};
for (int inc : increments) { ops += diff / inc; diff %= inc; }
```

**Why it works:** Largest denomination first minimizes total count — greedy is provably optimal here.

---

### Approach 3: Greedy — Inline Clean Version ⭐ BEST
**Time:** O(1) | **Space:** O(1) | **Status:** ✅ Accepted (Most Readable)

```java
int cur  = Integer.parseInt(current.substring(0, 2)) * 60
         + Integer.parseInt(current.substring(3));
int corr = Integer.parseInt(correct.substring(0, 2)) * 60
         + Integer.parseInt(correct.substring(3));
int diff = corr - cur, ops = 0;
for (int inc : new int[]{60, 15, 5, 1}) { ops += diff / inc; diff %= inc; }
return ops;
```

**Why it's best:** Compact, elegant, O(1) — parses directly without `split`.

---

## 📊 Complexity Comparison

| Approach | Time | Space | Pros | Cons |
|----------|------|-------|------|------|
| Brute Force | O(S) | O(1) | Simple | Slow |
| Greedy with array | O(1) | O(1) | Fast, clear | Uses split() |
| Greedy Inline ⭐ | O(1) | O(1) | Fastest, cleanest | — |

---

## 💡 Key Learnings

- Greedy works when problem has the **greedy choice property** — locally optimal → globally optimal
- This is exactly the **coin change greedy** but for time increments
- Always convert `HH:MM` to total minutes first for arithmetic

---

## 🔗 Similar Problems

- [Coin Change](https://leetcode.com/problems/coin-change/) — Medium
- [Minimum Number of Operations](https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/) — Hard

---

**Status:** ✅ SOLVED | **Attempts:** 1 | **Best Approach:** Approach 3 — Greedy Inline
