# 3737. Count Subarrays With Majority Element I

**Difficulty:** Medium 🟡  
**Category:** Prefix Sum / Fenwick Tree (Binary Indexed Tree)  
**Date Solved:** Aug 18, 2026

---

## 📝 Problem Statement

Given an array `nums` and an integer `target`, count the number of subarrays where `target` is the **majority element** (i.e., its frequency is strictly greater than half the length of the subarray).

### Example 1:
```
Input: nums = [1, 2, 2, 3], target = 2
Output: 5
Explanation: 
Transforming the array relative to target 2:
- target (2) becomes +1
- other elements become -1
Transformed: [-1, +1, +1, -1]

Valid subarrays with positive sum:
1. nums[1..1] -> [2] (sum = +1)
2. nums[1..2] -> [2, 2] (sum = +2)
3. nums[2..2] -> [2] (sum = +1)
4. nums[2..3] -> [2, 3] (sum = 0 - invalid)
5. nums[0..2] -> [1, 2, 2] (sum = +1)
6. nums[1..3] -> [2, 2, 3] (sum = +1)
Total valid = 5
```

---

## 🎯 Solution Comparison

| Approach | Time Complexity | Space Complexity | Status | Pros/Cons |
|---|---|---|---|---|
| **Brute Force** | $O(n^2)$ | $O(1)$ | ❌ TLE | Try all possible subarrays and count. |
| **Prefix Sum + Hash Map (Equality)** | $O(n)$ | $O(n)$ | ❌ N/A | Only works for exact sum matching, not inequalities. |
| **Prefix Sum + Fenwick Tree** | $O(n \log n)$ | $O(n)$ | ⭐ **BEST** | Handles inequality queries efficiently via index offset. |

---

## 🧠 Core Algorithm: Prefix Sum Inequality & Fenwick Tree

1. **Array Transformation:**  
   Map every element $x$ in `nums`:
   $$x = \begin{cases} +1 & \text{if } x = \text{target} \\ -1 & \text{otherwise} \end{cases}$$
   A subarray `nums[j..i]` has `target` as the majority element if and only if:
   $$\sum_{k=j}^{i} \text{transformed}[k] > 0$$

2. **Prefix Sum Formulation:**  
   Let $P[i]$ be the prefix sum of the transformed array. The subarray sum is:
   $$P[i] - P[j-1] > 0 \implies P[j-1] < P[i]$$
   Thus, at each index $i$, we need to count how many previous prefix sums $P[j-1]$ (for $j \le i$) are **strictly smaller** than the current prefix sum $P[i]$.

3. **Fenwick Tree (Binary Indexed Tree):**  
   Since prefix sums can range from $-n$ to $+n$, we offset them by $+ (n + 1)$ to keep indices positive. We query the tree for counts up to `prefix - 1`, and then insert the current `prefix` value into the tree.

---

## 🔍 Detailed Dry Run Example

**Input:** `nums = [1, 2, 2, 3]`, `target = 2` ($n = 4$, $\text{offset} = 5$)

* **Initial State:** `bit.add(5, 1)` (representing prefix sum 0 at index 5).

| Step | Element | Transformed | Prefix Sum | Offset Index | Query Range ($< \text{Index}$) | Query Result | Add to BIT | Cumulative Ans |
|---|---|---|---|---|---|---|---|---|
| **0 (Init)** | — | — | 0 | 5 | — | — | `bit.add(5, 1)` | 0 |
| **1** | `1` | `-1` | `-1` | 4 | $< 4$ | 0 | `bit.add(4, 1)` | 0 |
| **2** | `2` | `+1` | `0` | 5 | $< 5$ | 1 (index 4) | `bit.add(5, 1)` | 1 |
| **3** | `2` | `+1` | `+1` | 6 | $< 6$ | 3 (indices 4, 5) | `bit.add(6, 1)` | 4 |
| **4** | `3` | `-1` | `0` | 5 | $< 5$ | 1 (index 4) | `bit.add(5, 1)` | **5** ✅ |

---

## 💻 Java Solution

```java
class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        // Prefix sums range from -n to n.
        int offset = n + 1;
        FenwickTree bit = new FenwickTree(2 * n + 3);

        int prefix = 0;
        long answer = 0;

        // Prefix sum 0 exists before processing any element.
        bit.add(offset, 1);

        for (int num : nums) {
            prefix += (num == target) ? 1 : -1;

            int currentIndex = prefix + offset;

            // Count previous prefix sums strictly smaller than current prefix.
            answer += bit.query(currentIndex - 1);

            // Store the current prefix sum.
            bit.add(currentIndex, 1);
        }

        return answer;
    }

    static class FenwickTree {
        private final int[] tree;

        FenwickTree(int size) {
            tree = new int[size + 1];
        }

        void add(int index, int value) {
            while (index < tree.length) {
                tree[index] += value;
                index += index & -index;
            }
        }

        int query(int index) {
            int sum = 0;

            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }

            return sum;
        }
    }
}
```

---

## 💡 Key Learnings

- **Fenwick Tree for Range Queries:** Binary Indexed Trees are perfect for count-inversions and prefix sum inequality checks.
- **Index Offsets:** When dealing with negative indices in trees/arrays, adding a constant offset ($n+1$) shifts all coordinates to positive.
- **Transformed Score Matching:** Changing binary condition problems into `+1` and `-1` turns subarray count problems into prefix sum comparison problems.

---

## ❌ Mistakes I Made

1. **Mistake:** Querying `currentIndex` instead of `currentIndex - 1`.  
   **Fix:** The problem specifies **strictly greater** target counts, which means we only count $P[j-1] < P[i]$.
2. **Mistake:** Not using `long` for the answer.  
   **Fix:** The maximum answer for $n = 10^5$ can be $O(n^2)$, which exceeds the 32-bit Integer limit.

---

**Status:** ✅ SOLVED | **Attempts:** 1 | **Best Approach:** Fenwick Tree $O(n \log n)$
