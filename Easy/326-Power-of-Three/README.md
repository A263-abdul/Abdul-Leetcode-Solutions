# 326. Power of Three

**Difficulty:** Easy 🟢  
**LeetCode:** [Link](https://leetcode.com/problems/power-of-three/)  
**Category:** Math / Recursion  
**Date Solved:** Aug 22, 2026

---

## 📝 Problem Statement

Given an integer `n`, return `true` if it is a **power of three**. Otherwise, return `false`.

An integer `n` is a power of three if there exists an integer `x` such that `n == 3^x`.

### Examples:
```
Input: n = 27
Output: true
Explanation: 27 = 3^3

Input: n = 0
Output: false
Explanation: There is no x where 3^x = 0

Input: n = -1
Output: false
Explanation: There is no x where 3^x = -1
```

### Constraints:
- `-2^31 <= n <= 2^31 - 1`

---

## 🎯 Solution Comparison

| Approach | Time | Space | Status | Notes |
|---|---|---|---|---|
| **Max Power of 3 Divisibility** | $O(1)$ | $O(1)$ | ⭐ **BEST** | $3^{19} = 1162261467$, pure $O(1)$ integer math |
| **Iterative Division** | $O(\log_3 n)$ | $O(1)$ | ✅ Solved | Classic loop, simple & intuitive |
| **Recursion** | $O(\log_3 n)$ | $O(\log_3 n)$ | ✅ Works | Call stack overhead |
| **Logarithm with Epsilon** | $O(1)$ | $O(1)$ | ⚠️ Floating Point | Requires epsilon tolerance handling |

---

## 🧠 Core Algorithm: Max Power of 3 Divisibility $O(1)$

In Java, `int` is a signed 32-bit integer with max value:
$$\text{Integer.MAX\_VALUE} = 2^{31} - 1 = 2,147,483,647$$

The maximum integer power of 3 that fits within this range is:
$$3^{19} = 1,162,261,467 \quad (3^{20} = 3,486,784,401 > \text{Integer.MAX\_VALUE})$$

Because **3 is a prime number**, the only positive divisors of $3^{19}$ are powers of 3 ($3^0, 3^1, 3^2, \dots, 3^{19}$).

Therefore, for any positive integer $n$:
$$\text{isPowerOfThree}(n) \iff n > 0 \land (1162261467 \ \% \ n == 0)$$

```
3^19 = 1162261467

1162261467 % 27 == 0  -> TRUE  (27 = 3^3)
1162261467 % 9  == 0  -> TRUE  (9  = 3^2)
1162261467 % 6  == 3  -> FALSE (6 is divisible by 3, but not a power of 3)
```

---

## 💻 Java Solutions

### Approach 1: Iterative Division (Today's Submission)
```java
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;

        while (n % 3 == 0) {
            n = n / 3;
        }

        return n == 1;
    }
}
```

### Approach 2: Max Int Divisibility ⭐
```java
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
```

---

## 🔍 Dry Run

**Input:** `n = 27`

| Step | `n` | `n % 3 == 0` | Action | New `n` |
|---|---|---|---|---|
| 1 | 27 | True | `27 / 3` | 9 |
| 2 | 9 | True | `9 / 3` | 3 |
| 3 | 3 | True | `3 / 3` | 1 |
| 4 | 1 | False | Exit loop | 1 |

`n == 1` $\rightarrow$ **`true`** ✅

---

## ❌ Common Mistakes

1. **Handling $n \le 0$:** Powers of three are strictly positive. Negative numbers and 0 must immediately return `false`.
2. **Dividing by non-prime base:** The max divisibility trick works for $3$ (and $2$) because $3$ is prime. It **does NOT** work for $4$ (since divisors of $4^k$ include non-powers of 4 like 2).
3. **Floating Point Precision:** `Math.log(n) / Math.log(3)` can suffer from rounding errors in floating-point representations (e.g., $243$ may yield `4.999999999999999`).

---

## 🔗 Similar Problems

- [231. Power of Two](https://leetcode.com/problems/power-of-two/)
- [342. Power of Four](https://leetcode.com/problems/power-of-four/)

---

**Status:** ✅ SOLVED | **Attempts:** 1 | **Best Approach:** Max Divisibility $O(1)$ Time, $O(1)$ Space
