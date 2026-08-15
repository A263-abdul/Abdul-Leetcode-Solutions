# 1. Two Sum

**Difficulty:** Easy 🟢  
**LeetCode:** [Link](https://leetcode.com/problems/two-sum/)  
**Category:** Array / Hash Map  
**Date Solved:** Aug 15, 2026  
**Time Spent:** 15 mins

---

## 📝 Problem Statement

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to target. You may assume that each input would have exactly one solution, and you may not use the same element twice.

### Example 1:
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```

### Example 2:
```
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

### Constraints:
- `2 <= nums.length <= 10^4`
- `-10^9 <= nums[i] <= 10^9`
- `-10^9 <= target <= 10^9`
- Only one valid answer exists.

---

## 🎯 Approaches

### Approach 1: Brute Force ❌
**Time:** O(n²) | **Space:** O(1) | **Status:** ❌ TLE

**Idea:** Check every pair of numbers to see if they sum to target.

```java
for (int i = 0; i < n; i++)
    for (int j = i + 1; j < n; j++)
        if (nums[i] + nums[j] == target) return new int[]{i, j};
```

**Why it fails:** Too slow for large arrays — checks every pair.

---

### Approach 2: Two-Pass HashMap ✅
**Time:** O(n) | **Space:** O(n) | **Status:** ✅ Accepted

**Idea:** First pass stores all elements in a HashMap, second pass looks for complement.

```java
HashMap<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < nums.length; i++) map.put(nums[i], i);
for (int i = 0; i < nums.length; i++) {
    int complement = target - nums[i];
    if (map.containsKey(complement) && map.get(complement) != i)
        return new int[]{i, map.get(complement)};
}
```

**Why it works:** HashMap gives O(1) lookup — reduces overall to O(n).

---

### Approach 3: One-Pass HashMap ⭐ BEST
**Time:** O(n) | **Space:** O(n) | **Status:** ✅ Accepted (Most Efficient)

**Idea:** While iterating, check if complement already exists in map. If yes → answer found. If no → add current element.

```java
HashMap<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int complement = target - nums[i];
    if (map.containsKey(complement)) return new int[]{map.get(complement), i};
    map.put(nums[i], i);
}
```

**Why it's best:** Single pass — half the work of two-pass. Elegant and clean.

---

## 📊 Complexity Comparison

| Approach | Time | Space | Pros | Cons |
|----------|------|-------|------|------|
| Brute Force | O(n²) | O(1) | No extra space | Very slow |
| Two-Pass HashMap | O(n) | O(n) | Fast lookup | Two iterations |
| One-Pass HashMap ⭐ | O(n) | O(n) | Fastest, cleanest | Needs extra space |

---

## 💡 Key Learnings

- HashMap is the go-to tool for O(1) lookups in array problems
- Think "complement" — instead of finding two numbers, find one number's pair
- One-pass is better than two-pass whenever possible

---

## ❌ Mistakes I Made

1. **Mistake:** Using same index twice (`map.get(complement) != i` check)  
   **Fix:** Always validate the found index is different from current

---

## 🔗 Similar Problems

- [3Sum](https://leetcode.com/problems/3sum/) — Medium
- [Two Sum II](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) — Medium
- [4Sum](https://leetcode.com/problems/4sum/) — Medium

---

**Status:** ✅ SOLVED | **Attempts:** 2 | **Best Approach:** Approach 3 — One-Pass HashMap
