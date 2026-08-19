# 217. Contains Duplicate

**Difficulty:** Easy 🟢  
**LeetCode:** [Link](https://leetcode.com/problems/contains-duplicate/)  
**Category:** Array / Hashing  
**Date Solved:** Aug 19, 2026

---

## 📝 Problem Statement

Given an integer array `nums`, return `true` if any value appears **at least twice** in the array, and return `false` if every element is distinct.

### Examples:
```
Input: nums = [1, 2, 3, 1]
Output: true

Input: nums = [1, 2, 3, 4]
Output: false

Input: nums = [1, 1, 1, 3, 3, 4, 3, 2, 4, 2]
Output: true
```

### Constraints:
- `1 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`

---

## 🎯 Solution Comparison

| Approach | Time | Space | Status | Notes |
|---|---|---|---|---|
| **HashSet** | $O(n)$ | $O(n)$ | ⭐ **BEST** | One pass, instant lookup |
| **Brute Force** | $O(n^2)$ | $O(1)$ | ❌ TLE | Compares every pair |
| **Sort First** | $O(n \log n)$ | $O(\log n)$ | ✅ Accepted | Adjacent check after sort |

---

## 🧠 Core Algorithm: HashSet One-Pass

The central idea is:
> If a number has **already been inserted**, it's a duplicate.

A `HashSet` gives us $O(1)$ average lookup and insert. Walk through the array once, check if the current number is in the set, add it if not.

```
nums = [1, 2, 3, 1]

Step 1: set = {}       → 1 not found → add → set = {1}
Step 2: set = {1}      → 2 not found → add → set = {1, 2}
Step 3: set = {1, 2}   → 3 not found → add → set = {1, 2, 3}
Step 4: set = {1,2,3}  → 1 FOUND ✅ → return true!
```

---

## 💻 Java Solution (Best Approach)

```java
import java.util.HashSet;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }

        return false;
    }
}
```

> [!TIP]
> You can also shorten this to `set.add(num)` returning `false` when already present, but the explicit `.contains()` check is clearer for interviews.

---

## 🔍 Why HashSet works in O(1)?

A `HashSet` uses hashing internally:
- `num` is hashed to a bucket index → $O(1)$ lookup
- If the bucket is empty, insert → no collision
- If the bucket already has `num` → duplicate!

In the worst case (many hash collisions), lookup degrades to $O(n)$, but for integer arrays this is extremely rare in practice.

---

## 📊 Approach Deep Dive

### Brute Force $O(n^2)$:
```java
for (int i = 0; i < nums.length; i++) {
    for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] == nums[j]) return true;
    }
}
```
Fails on arrays of size $10^5$ (tries $5 \times 10^9$ comparisons).

### Sort + Adjacent Check $O(n \log n)$:
```java
Arrays.sort(nums);
for (int i = 1; i < nums.length; i++) {
    if (nums[i] == nums[i - 1]) return true;
}
```
Works, but **modifies the input array** and is slower than HashSet. Only use if you can't allocate extra space.

---

## 🔗 Similar Problems

- [1. Two Sum](https://leetcode.com/problems/two-sum/) — HashMap for O(1) lookup
- [219. Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) — Sliding Window + HashSet
- [220. Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/) — TreeSet / Bucket Sort

---

**Status:** ✅ SOLVED | **Attempts:** 1 | **Best Approach:** HashSet One-Pass O(n)
