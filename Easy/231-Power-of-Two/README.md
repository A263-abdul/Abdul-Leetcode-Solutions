# 231. Power of Two

**Difficulty:** Easy 🟢  
**LeetCode:** [Link](https://leetcode.com/problems/power-of-two/)  
**Category:** Math / Bit Manipulation / Recursion  
**Date Solved:** Aug 22, 2026

---

## 📝 Problem Statement

Given an integer `n`, return `true` if it is a **power of two**. Otherwise, return `false`.

An integer `n` is a power of two if there exists an integer `x` such that `n == 2^x`.

### Examples:
```
Input: n = 1
Output: true
Explanation: 2^0 = 1

Input: n = 16
Output: true
Explanation: 2^4 = 16

Input: n = 3
Output: false
```

### Constraints:
- `-2^31 <= n <= 2^31 - 1`

---

## 🎯 Solution Comparison

| Approach | Time | Space | Status | Notes |
|---|---|---|---|---|
| **Bit Manipulation `n & (n - 1)`** | $O(1)$ | $O(1)$ | ⭐ **BEST** | One-line bitwise trick |
| **Iterative Division** | $O(\log n)$ | $O(1)$ | ✅ Solved | Loop dividing by 2 |
| **Bit Count `Integer.bitCount(n)`** | $O(1)$ | $O(1)$ | ✅ Clean | Checks for single set bit |
| **Max Integer Divisibility** | $O(1)$ | $O(1)$ | ✅ Math | `2^30 % n == 0` |

---

## 🧠 Core Algorithm: Bit Manipulation `n & (n - 1)`

Every positive power of two in binary has **exactly one bit set to 1**:
```
 1 = 0000 0001
 2 = 0000 0010
 4 = 0000 0100
 8 = 0000 1000
16 = 0001 0000
```

When we subtract `1` from a power of two `n`, the set bit becomes `0` and all lower bits become `1`:
```
 8 = 0000 1000
 7 = 0000 0111
 8 & 7 = 0000 0000 (0)
```

For any non-power of two with multiple set bits:
```
 6 = 0000 0110
 5 = 0000 0101
 6 & 5 = 0000 0100 != 0
```

Therefore:
$$\text{isPowerOfTwo}(n) \iff n > 0 \land (n \ \& \ (n - 1) == 0)$$

---

## 💻 Java Solutions

### Approach 1: Iterative Division (Today's Submission)
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;

        while (n % 2 == 0) {
            n = n / 2;
        }

        return n == 1;
    }
}
```

### Approach 2: Bit Manipulation Trick ⭐
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

---

## 🔍 Dry Run

**Input:** `n = 16`

| Step | `n` | `n % 2 == 0` | Action | New `n` |
|---|---|---|---|---|
| 1 | 16 | True | `16 / 2` | 8 |
| 2 | 8 | True | `8 / 2` | 4 |
| 3 | 4 | True | `4 / 2` | 2 |
| 4 | 2 | True | `2 / 2` | 1 |
| 5 | 1 | False | Exit loop | 1 |

`n == 1` $\rightarrow$ **`true`** ✅

---

## ❌ Common Mistakes

1. **Negative numbers and zero:** `0` and negative numbers are not powers of two. For example, `Integer.MIN_VALUE` (`-2147483648`) has a single bit set in two's complement, so checking `(n & (n - 1)) == 0` without `n > 0` causes an integer overflow bug!
2. **Integer Overflow:** Using multiplication (`power *= 2`) inside a loop can overflow `int` if not careful with boundary checks.

---

## 🔗 Similar Problems

- [326. Power of Three](https://leetcode.com/problems/power-of-three/)
- [342. Power of Four](https://leetcode.com/problems/power-of-four/)
- [191. Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/)

---

**Status:** ✅ SOLVED | **Attempts:** 1 | **Best Approach:** Bit Manipulation $O(1)$ Time, $O(1)$ Space
