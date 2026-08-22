# 3622. Check Divisibility by Digit Sum and Product

**Difficulty:** Easy 🟢  
**LeetCode:** [Link](https://leetcode.com/problems/check-divisibility-by-digit-sum-and-product/)  
**Category:** Math / Simulation  
**Date Solved:** Aug 22, 2026

---

## 📝 Problem Statement

You are given a positive integer `n`. Determine whether `n` is divisible by the sum of the following two values:
- The **digit sum** of `n` (the sum of its digits).
- The **digit product** of `n` (the product of its digits).

Return `true` if `n` is divisible by this sum; otherwise, return `false`.

### Examples:
```
Input: n = 99
Output: true
Explanation:
- Digit sum: 9 + 9 = 18
- Digit product: 9 * 9 = 81
- Total divisor: 18 + 81 = 99
- 99 % 99 == 0 -> true

Input: n = 23
Output: false
Explanation:
- Digit sum: 2 + 3 = 5
- Digit product: 2 * 3 = 6
- Total divisor: 5 + 6 = 11
- 23 % 11 != 0 -> false
```

### Constraints:
- `1 <= n <= 10^9`

---

## 🎯 Solution Comparison

| Approach | Time | Space | Status | Notes |
|---|---|---|---|---|
| **Digit Extraction & Modulo** | $O(\log_{10} n)$ | $O(1)$ | ⭐ **BEST** | One-pass digit extraction, zero extra memory |

---

## 🧠 Core Algorithm

1. Store `original = n` before modifying `n`.
2. Initialize `sum = 0` and `product = 1`.
3. Loop while `n > 0`:
   - Extract last digit: `digit = n % 10`
   - Add to sum: `sum += digit`
   - Multiply to product: `product *= digit`
   - Truncate last digit: `n /= 10`
4. Return `original % (sum + product) == 0`.

```
n = 99, original = 99, sum = 0, product = 1

Iteration 1: digit = 9 -> sum = 9, product = 9, n = 9
Iteration 2: digit = 9 -> sum = 18, product = 81, n = 0

totalDivisor = 18 + 81 = 99
99 % 99 == 0 -> true ✅
```

---

## 💻 Java Solution

```java
class Solution {
    public boolean checkDivisibility(int n) {
        int original = n;
        int sum = 0;
        int product = 1;

        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            product *= digit;
            n /= 10;
        }

        return original % (sum + product) == 0;
    }
}
```

---

## 🔍 Dry Run

**Input:** `n = 23`

| Step | `n` | `digit` | `sum` | `product` | Action |
|---|---|---|---|---|---|
| Start | 23 | — | 0 | 1 | Store `original = 23` |
| 1 | 23 | 3 | $0 + 3 = 3$ | $1 \times 3 = 3$ | `n = 2` |
| 2 | 2 | 2 | $3 + 2 = 5$ | $3 \times 2 = 6$ | `n = 0` |
| End | 0 | — | 5 | 6 | Divisor = $5 + 6 = 11$ |

Check `23 % 11 == 0` $\rightarrow$ `false` ✅

---

## ❌ Common Mistakes

1. **Overwriting `n` without copying:** Because the loop reduces `n` to `0`, forgetting to save `original = n` makes it impossible to perform `original % divisor`.
2. **Initializing product to 0:** Product must start at `1`, not `0`, otherwise `product * digit` would always stay `0`.

---

## 🔗 Similar Problems

- [1281. Subtract the Product and Sum of Digits of an Integer](https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/)
- [2544. Alternating Digit Sum](https://leetcode.com/problems/alternating-digit-sum/)
- [2520. Count the Digits That Divide a Number](https://leetcode.com/problems/count-the-digits-that-divide-a-number/)

---

**Status:** ✅ SOLVED | **Attempts:** 1 | **Best Approach:** Digit Extraction $O(\log_{10} n)$ Time, $O(1)$ Space
