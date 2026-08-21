# 27. Remove Element

**Difficulty:** Easy 🟢  
**LeetCode:** [Link](https://leetcode.com/problems/remove-element/)  
**Category:** Array / Two Pointers  
**Date Solved:** Aug 21, 2026

---

## 📝 Problem Statement

Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in `nums` **in-place**. The order of the elements may be changed. Then return the number of elements in `nums` which are not equal to `val`.

Consider the number of elements in `nums` which are not equal to `val` be `k`. To get accepted, you need to:
1. Change the array `nums` such that the first `k` elements of `nums` contain the elements which are not equal to `val`.
2. Return `k`.

### Examples:
```
Input: nums = [3, 2, 2, 3], val = 3
Output: 2, nums = [2, 2, _, _]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.

Input: nums = [0, 1, 2, 2, 3, 0, 4, 2], val = 2
Output: 5, nums = [0, 1, 4, 0, 3, _, _, _]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4 in any order.
```

### Constraints:
- `0 <= nums.length <= 100`
- `0 <= nums[i] <= 50`
- `0 <= val <= 100`

---

## 🎯 Solution Comparison

| Approach | Time | Space | Status | Notes |
|---|---|---|---|---|
| **Two Pointers (Reader/Writer)** | $O(n)$ | $O(1)$ | ⭐ **BEST** | Simple, elegant $O(n)$ one-pass algorithm |
| **Two Pointers (Opposite Ends)** | $O(n)$ | $O(1)$ | ✅ Optimal when `val` is rare | Minimizes unnecessary array writes |

---

## 🧠 Core Algorithm: Two Pointers (Reader / Writer)

Maintain a writer pointer `k` initialized to `0`.  
Iterate over array `nums` using a reader loop (or enhanced `for` loop):
1. If the current element `num != val`, write `nums[k] = num` and increment `k++`.
2. If `num == val`, simply skip it.
3. At the end, `k` is the count of non-`val` elements, and the first `k` elements contain the result.

```
nums = [0, 1, 2, 2, 3, 0, 4, 2], val = 2

num = 0 != 2 -> nums[0] = 0, k = 1
num = 1 != 2 -> nums[1] = 1, k = 2
num = 2 == 2 -> skip
num = 2 == 2 -> skip
num = 3 != 2 -> nums[2] = 3, k = 3
num = 0 != 2 -> nums[3] = 0, k = 4
num = 4 != 2 -> nums[4] = 4, k = 5
num = 2 == 2 -> skip

Result: k = 5, nums[:5] = [0, 1, 3, 0, 4]
```

---

## 💻 Java Solution

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0;

        for (int num : nums) {
            if (num != val) {
                nums[k] = num;
                k++;
            }
        }

        return k;
    }
}
```

---

## 🔍 Dry Run

**Input:** `nums = [3, 2, 2, 3]`, `val = 3`

| Step | `num` | `num != val`? | Action | `nums` State | `k` |
|---|---|---|---|---|---|
| Start | — | — | Initial state | `[3, 2, 2, 3]` | `0` |
| 1 | `3` | `false` | Skip | `[3, 2, 2, 3]` | `0` |
| 2 | `2` | `true` | `nums[0] = 2, k++` | `[2, 2, 2, 3]` | `1` |
| 3 | `2` | `true` | `nums[1] = 2, k++` | `[2, 2, 2, 3]` | `2` |
| 4 | `3` | `false` | Skip | `[2, 2, 2, 3]` | `2` |

**Return:** `k = 2`, first 2 elements are `[2, 2]`. ✅

---

## ❌ Common Mistakes

1. **Mistake:** Creating a new array to store filtered elements.  
   **Fix:** Problem requires **in-place** modification ($O(1)$ extra space).
2. **Mistake:** Forgetting to return `k` or returning `nums.length`.  
   **Fix:** Return the writer index `k` which represents the new valid length.

---

## 🔗 Similar Problems

- [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) — Same reader-writer pointer pattern
- [283. Move Zeroes](https://leetcode.com/problems/move-zeroes/) — Move target element to end
- [203. Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements/) — Linked List variant

---

**Status:** ✅ SOLVED | **Attempts:** 1 | **Best Approach:** Two Pointers O(n) Time, O(1) Space
