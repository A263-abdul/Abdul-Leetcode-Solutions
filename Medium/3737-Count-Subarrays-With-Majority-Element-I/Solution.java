/**
 * Problem: LeetCode 3737 - Count Subarrays With Majority Element I
 * Difficulty: Medium
 * Category: Prefix Sum / Fenwick Tree (Binary Indexed Tree)
 *
 * Date Solved: Aug 18, 2026
 */
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
