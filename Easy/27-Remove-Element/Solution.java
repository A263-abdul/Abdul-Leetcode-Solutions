/**
 * Problem: LeetCode 27 - Remove Element
 * LeetCode Link: https://leetcode.com/problems/remove-element/
 * Difficulty: Easy
 * Category: Array / Two Pointers
 *
 * Date Solved: Aug 21, 2026
 */
class Solution {

    // ========================================
    // APPROACH 1: Two Pointers (Reader & Writer) ⭐ BEST
    // Time: O(n) | Space: O(1)
    // ========================================
    // Pointer 'k' acts as the writer index for valid elements (nums[i] != val).
    // Iterate through the array; whenever a non-val element is found, write it at index k and increment k.
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

    // ========================================
    // APPROACH 2: Two Pointers (Opposite Ends - Optimized for Rare Occurrences)
    // Time: O(n) | Space: O(1)
    // ========================================
    // When elements to remove are rare (e.g. nums = [1, 2, 3, 5, 4], val = 5),
    // swapping with the last element reduces unnecessary array copies.
    public int removeElementOpposite(int[] nums, int val) {
        int i = 0;
        int n = nums.length;

        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // Reduce array size, don't increment i because swapped element must be inspected
                n--;
            } else {
                i++;
            }
        }

        return n;
    }
}
