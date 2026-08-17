/**
 * Problem: LeetCode 912 - Sort an Array (Merge Sort Implementation)
 * LeetCode Link: https://leetcode.com/problems/sort-an-array/
 * Difficulty: Medium
 * Category: Divide and Conquer / Sorting
 *
 * Date Solved: Aug 17, 2026
 */
class Solution {
    
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        // One temporary array allocated once to avoid garbage collection overhead
        int[] temporary = new int[nums.length];
        mergeSort(nums, temporary, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int[] temporary, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        
        // Divide and conquer: sort both halves first.
        mergeSort(nums, temporary, left, mid);
        mergeSort(nums, temporary, mid + 1, right);
        
        // Optional optimization: if already ordered, no merge is needed.
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }
        merge(nums, temporary, left, mid, right);
    }

    private void merge(int[] nums, int[] temporary, int left, int mid, int right) {
        int leftPointer = left;
        int rightPointer = mid + 1;
        int writePointer = left;

        // Compare the front value of each sorted half.
        while (leftPointer <= mid && rightPointer <= right) {
            if (nums[leftPointer] <= nums[rightPointer]) {
                temporary[writePointer++] = nums[leftPointer++];
            } else {
                temporary[writePointer++] = nums[rightPointer++];
            }
        }

        // Copy any remaining values from the left half.
        while (leftPointer <= mid) {
            temporary[writePointer++] = nums[leftPointer++];
        }

        // Copy any remaining values from the right half.
        while (rightPointer <= right) {
            temporary[writePointer++] = nums[rightPointer++];
        }

        // Write the merged sorted range back into nums.
        for (int index = left; index <= right; index++) {
            nums[index] = temporary[index];
        }
    }
}
