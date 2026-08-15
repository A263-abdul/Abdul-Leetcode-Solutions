# 264. Ugly Number II

**Difficulty:** Medium 🟡  
**LeetCode:** [Link](https://leetcode.com/problems/ugly-number-ii/)  
**Category:** Dynamic Programming / Math / Three Pointers  
**Date Solved:** Aug 15, 2026  
**Time Spent:** 35 mins

---

## 📝 Problem Statement

An **ugly number** is a positive integer whose prime factors are limited to `2`, `3`, and `5`.

Given an integer `n`, return the `n`-th ugly number.

> Every ugly number = $2^a \cdot 3^b \cdot 5^c$ where $a, b, c \ge 0$

### Example 1:
```
Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] — the 10th ugly number is 12
```

### Example 2:
```
Input: n = 1
Output: 1
Explanation: 1 is always the first ugly number (1 = 2⁰ × 3⁰ × 5⁰)
```

### Constraints:
- `1 <= n <= 1690`

---

## 🎯 Approaches

### Approach 1: Brute Force ❌ (Check Every Integer)
**Time:** O(n × log k) | **Space:** O(1) | **Status:** ❌ TLE for large n

**Idea:** For every integer starting from 1, check if it's ugly by dividing out 2s, 3s, and 5s. If it reduces to 1 → it's ugly. Count until we reach the n-th one.

```java
private boolean isUgly(int num) {
    if (num <= 0) return false;
    while (num % 2 == 0) num /= 2;
    while (num % 3 == 0) num /= 3;
    while (num % 5 == 0) num /= 5;
    return num == 1;
}
```

**Why it fails:**
- To reach the 1690th ugly number you'd check integers far past 2 billion
- Each check costs O(log k) divisions
- Overall far worse than O(n) in practice

---

### Approach 2: DP + 3 Pointers ⭐ BEST
**Time:** O(n) | **Space:** O(n) | **Status:** ✅ Accepted (Optimal)

**Core Insight:**

> If `x` is ugly → `2x`, `3x`, `5x` are also ugly.  
> So we can **generate** ugly numbers from previous ones!

**Idea:** Maintain a DP array and three pointers `i2`, `i3`, `i5`. Each pointer tracks which ugly number to multiply next by 2, 3, or 5. At each step, pick the minimum of all three candidates:

$$\text{next ugly} = \min(arr[i2] \times 2,\ arr[i3] \times 3,\ arr[i5] \times 5)$$

Then advance **all** pointers that equal the chosen minimum (prevents duplicates).

```java
int[] arr = new int[n + 1];
int i2 = 1, i3 = 1, i5 = 1;
arr[1] = 1;

for (int i = 2; i <= n; i++) {
    int next2 = arr[i2] * 2;
    int next3 = arr[i3] * 3;
    int next5 = arr[i5] * 5;

    int minUgly = Math.min(next2, Math.min(next3, next5));
    arr[i] = minUgly;

    if (minUgly == next2) i2++;  // ⚠️ separate `if` — NOT `else if`
    if (minUgly == next3) i3++;
    if (minUgly == next5) i5++;
}
return arr[n];
```

**Why it works:**
- We only ever generate valid ugly numbers (multiplying ugly × prime factor = ugly)
- Picking the minimum ensures sorted order
- The 3-pointer trick avoids duplicates: `6 = 2×3 = 3×2` → both `i2` and `i3` advance

---

## 🔍 Dry Run — First 10 Ugly Numbers

| Step | arr so far | next2 | next3 | next5 | min | i2 | i3 | i5 |
|------|-----------|-------|-------|-------|-----|----|----|----|
| 1 | [1] | 2 | 3 | 5 | **2** | 2→ | 1 | 1 |
| 2 | [1,2] | 4 | 3 | 5 | **3** | 2 | 2→ | 1 |
| 3 | [1,2,3] | 4 | 6 | 5 | **4** | 3→ | 2 | 1 |
| 4 | [1,2,3,4] | 6 | 6 | 5 | **5** | 3 | 2 | 2→ |
| 5 | [1,2,3,4,5] | 6 | 6 | 10 | **6** | 4→ | 3→ | 2 |
| 6 | [1,2,3,4,5,6] | 8 | 9 | 10 | **8** | 5→ | 3 | 2 |
| 7 | [1,2,3,4,5,6,8] | 10 | 9 | 10 | **9** | 5 | 4→ | 2 |
| 8 | [1,2,3,4,5,6,8,9] | 10 | 12 | 10 | **10** | 6→ | 4 | 3→ |
| 9 | [1,2,3,4,5,6,8,9,10] | 12 | 12 | 15 | **12** | 7→ | 5→ | 3 |

> ⭐ **Step 5** — min is 6. Both `next2=6` and `next3=6`, so **both** `i2` and `i3` advance.  
> That's why we use separate `if` — not `else if`!

---

## 📊 Complexity Comparison

| Approach | Time | Space | Pros | Cons |
|----------|------|-------|------|------|
| Brute Force | O(n × log k) | O(1) | No extra space | Extremely slow for large n |
| DP + 3 Pointers ⭐ | O(n) | O(n) | Linear, elegant, fast | Needs O(n) array |

---

## 💡 Key Learnings

- **Build, don't search** — generating answers from previously computed answers (DP) beats checking every candidate (brute force)
- **3-pointer merge** — this is the "merge 3 sorted streams" pattern; each pointer walks one stream (×2, ×3, ×5 multiples)
- **Why separate `if` not `else if`?** — Duplicates like `6` appear in both the ×2 stream and ×3 stream. Using `else if` would only advance one pointer, leaving a duplicate in the next round
- **Ugly Number = Coin Change cousin** — both build answers using previously found values

---

## ❌ Mistakes I Made

1. **Mistake:** Used `else if` instead of separate `if` for pointer increments  
   **Fix:** Always use three separate `if` blocks so duplicates (e.g. 6, 12, 30) are skipped correctly

2. **Mistake:** Initialized array as `new int[n]` instead of `new int[n+1]`  
   **Fix:** Use 1-indexed array (`arr[1]` to `arr[n]`), so size must be `n+1`

---

## 🔗 Similar Problems

- [263. Ugly Number](https://leetcode.com/problems/ugly-number/) — Easy (the `isUgly` check)
- [313. Super Ugly Number](https://leetcode.com/problems/super-ugly-number/) — Medium (generalize to k primes)
- [1201. Ugly Number III](https://leetcode.com/problems/ugly-number-iii/) — Medium (LCM approach)
- [23. Merge K Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) — Hard (same multi-pointer merge idea)

---

## ✍️ Notes

The 3-pointer approach is a special case of the **"merge k sorted streams"** pattern.  
Think of it as merging three infinite sorted lists:
- Stream A: `2, 4, 6, 8, 10, 12, ...` (ugly × 2)
- Stream B: `3, 6, 9, 12, 15, ...` (ugly × 3)  
- Stream C: `5, 10, 15, 20, ...` (ugly × 5)

We merge them in sorted order — but each element in these streams depends on earlier ugly numbers, so we compute them lazily with the DP array.

---

**Status:** ✅ SOLVED | **Attempts:** 2 | **Best Approach:** Approach 2 — DP + 3 Pointers
