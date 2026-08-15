import java.util.HashMap;

/**
 * Problem: Two Sum
 * LeetCode Link: https://leetcode.com/problems/two-sum/
 * Difficulty: Easy
 * Category: Array / Hash Map
 *
 * Date Solved: Aug 15, 2026
 * Time Taken: 15 mins
 */

class Solution {

    // ========================================
    // APPROACH 1: Brute Force (Nested Loops)
    // Time: O(n²) | Space: O(1)
    // Status: ❌ TLE for large inputs
    // ========================================

    public int[] twoSumBrute(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }


    // ========================================
    // APPROACH 2: Two-Pass HashMap
    // Time: O(n) | Space: O(n)
    // Status: ✅ Accepted
    // ========================================

    public int[] twoSumTwoPass(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // First pass: store all values with their indices
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        // Second pass: check for complement
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[]{};
    }


    // ========================================
    // APPROACH 3: One-Pass HashMap ⭐ BEST
    // Time: O(n) | Space: O(n)
    // Status: ✅ Accepted (Most Efficient)
    // ========================================

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
