# 53. Maximum Subarray

**Difficulty:** Medium 🟡  
**LeetCode:** [Link](https://leetcode.com/problems/maximum-subarray/)  
**Category:** Divide and Conquer / Dynamic Programming  
**Date Solved:** Aug 20, 2026

---

## 📝 Problem Statement

Given an integer array `nums`, find the **subarray** with the largest sum, and return its sum.

### Examples:
```
Input: nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Output: 6
Explanation: [4, -1, 2, 1] has the largest sum = 6

Input: nums = [1]
Output: 1

Input: nums = [5, 4, -1, 7, 8]
Output: 23
```

### Constraints:
- `1 <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`

---

## 🎯 Solution Comparison

| Approach | Time | Space | Notes |
|---|---|---|---|
| **Divide & Conquer** | $O(n \log n)$ | $O(\log n)$ | ⭐ **Today's Approach** — elegant recursion |
| **Kadane's Algorithm** | $O(n)$ | $O(1)$ | ✅ Classic optimal — linear scan |
| **Brute Force** | $O(n^2)$ | $O(1)$ | ❌ TLE on large inputs |

---

## 🧠 Core Algorithm: Divide and Conquer

The key insight: any subarray falls into exactly one of three cases:

```
Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
                    ↑
                   mid = 4

Case 1: Entirely in LEFT  [-2, 1, -3, 4]
Case 2: Entirely in RIGHT [-1, 2, 1, -5, 4]
Case 3: CROSSES mid       [...4, -1, 2, 1...]  ← WINNER ✅
```

**For the crossing case** — the best crossing subarray = best left extension from mid + best right extension from mid+1:
```
Left from mid:   4 → (4+(-3)) → ... → best leftSum = 4
Right from mid+1: -1+2+1 = 2 → best rightSum = 2+1 = 2... → actually -1→1→3
                  rightSum = -1 → 1 → 3 → -2 → 2 → best = 3... 

Actually for [-2,1,-3,4,-1,2,1,-5,4], mid=4:
Left sum (from index 4 going left): -1 → -1+4=3 → 3-3=0 → 0+1=1 → 1-2=-1
                                      best leftSum = 3 (just nums[4]+nums[3] = -1+4)
Wait, indices 0-8, mid = (0+8)/2 = 4 → nums[4] = -1

Left from mid (i=4 to 0):  -1 → -1+4=3 → 3-3=0 → 0+1=1 → 1-2=-1  → leftSum = 3
Right from mid+1 (i=5 to 8): 2 → 2+1=3 → 3-5=-2 → -2+4=2          → rightSum = 3

midMax = 3 + 3 = 6 ✅
```

---

## 💻 Java Solution (Divide & Conquer)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        return divideConquer(nums, 0, nums.length - 1);
    }

    private int divideConquer(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int mid = left + (right - left) / 2;

        int leftMax  = divideConquer(nums, left, mid);
        int rightMax = divideConquer(nums, mid + 1, right);
        int midMax   = maxCrossingMid(nums, left, mid, right);

        return Math.max(Math.max(leftMax, rightMax), midMax);
    }

    private int maxCrossingMid(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }

        return leftSum + rightSum;
    }
}
```

---

## 🔍 Dry Run: `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`

```
divideConquer(0, 8)  mid = 4
├── divideConquer(0, 4)  mid = 2
│   ├── divideConquer(0, 2)  → returns 1   (subarray [1])
│   ├── divideConquer(3, 4)  → returns 3   (subarray [4,-1])
│   └── maxCrossingMid(0,2,4)→ returns 3   (subarray [4,-1])... = 3
│       → max(1, 3, 3) = 3
├── divideConquer(5, 8)  mid = 6
│   ├── divideConquer(5, 6)  → returns 3   (subarray [2,1])
│   ├── divideConquer(7, 8)  → returns 4   (subarray [4])
│   └── maxCrossingMid(5,6,8)→ returns 3   ([2,1] crosses mid 6)
│       → max(3, 4, 3) = 4
└── maxCrossingMid(0,4,8) → leftSum=3, rightSum=3 → midMax = 6  ✅
    → max(3, 4, 6) = 6 ✅
```

---

## 💡 Why `Integer.MIN_VALUE` not `0`?

We initialize `leftSum = Integer.MIN_VALUE` (not 0) because the array may contain **all negative numbers**.  
If we used 0, an all-negative array like `[-3, -1, -2]` would incorrectly return 0 instead of -1.

---

## ❌ Common Mistakes

1. **Mistake:** Using `sum = 0` as initial `leftSum`/`rightSum` in `maxCrossingMid`.  
   **Fix:** Use `Integer.MIN_VALUE` to handle all-negative arrays.

2. **Mistake:** Confusing `mid` (index) with median (value).  
   **Fix:** `mid = left + (right - left) / 2` — it's always an index.

3. **Mistake:** `maxCrossingMid` going right from `mid` instead of `mid+1`.  
   **Fix:** Left part is `[left..mid]`, right part is `[mid+1..right]` — no overlap.

---

## 🔗 Similar Problems

- [152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) — Kadane variant
- [918. Maximum Sum Circular Subarray](https://leetcode.com/problems/maximum-sum-circular-subarray/)
- [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) — same idea, max gain

---

**Status:** ✅ SOLVED | **Attempts:** 1 | **Best Approach (Today):** Divide & Conquer O(n log n)
