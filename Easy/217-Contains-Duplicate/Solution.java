import java.util.HashSet;

/**
 * Problem: LeetCode 217 - Contains Duplicate
 * LeetCode Link: https://leetcode.com/problems/contains-duplicate/
 * Difficulty: Easy
 * Category: Array / Hashing
 *
 * Date Solved: Aug 19, 2026
 */
class Solution {

    // ========================================
    // APPROACH 1: HashSet ⭐ BEST
    // Time: O(n) | Space: O(n)
    // ========================================
    // Insert each element into a HashSet.
    // If it's already there → duplicate found.
    // HashSet.contains() and add() are both O(1) average.
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            // If num is already present, it is a duplicate
            if (set.contains(num)) {
                return true;
            }
            // Otherwise, add it to the set
            set.add(num);
        }

        // No duplicate found
        return false;
    }


    // ========================================
    // APPROACH 2: Brute Force
    // Time: O(n²) | Space: O(1)
    // ========================================
    // Compare every pair — simple but very slow for large inputs.
    public boolean containsDuplicateBrute(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }


    // ========================================
    // APPROACH 3: Sort First
    // Time: O(n log n) | Space: O(1) or O(log n) for sort stack
    // ========================================
    // After sorting, any duplicates will be adjacent — O(n) scan catches them.
    // Modifies the input array, which may not always be acceptable.
    public boolean containsDuplicateSort(int[] nums) {
        java.util.Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
}
