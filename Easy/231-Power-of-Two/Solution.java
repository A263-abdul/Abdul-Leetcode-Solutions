/**
 * Problem: LeetCode 231 - Power of Two
 * LeetCode Link: https://leetcode.com/problems/power-of-two/
 * Difficulty: Easy
 * Category: Math / Bit Manipulation / Recursion
 *
 * Date Solved: Aug 22, 2026
 */
class Solution {

    // ========================================
    // APPROACH 1: Iterative Division (User's Solution) ✅
    // Time: O(log n) | Space: O(1)
    // ========================================
    // Repeatedly divide n by 2 as long as n is even.
    // If n is a power of 2, it will eventually reduce to 1.
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 2 == 0) {
            n = n / 2;
        }

        return n == 1;
    }


    // ========================================
    // APPROACH 2: Bit Manipulation (n & (n - 1)) ⭐ BEST
    // Time: O(1) | Space: O(1)
    // ========================================
    // A power of two in binary has exactly ONE set bit (e.g., 8 = 1000).
    // Subtracting 1 flips all bits up to that set bit (e.g., 7 = 0111).
    // Therefore, n & (n - 1) is always 0 for any power of two.
    public boolean isPowerOfTwoBitwise(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }


    // ========================================
    // APPROACH 3: Bit Count Method
    // Time: O(1) | Space: O(1)
    // ========================================
    // Since powers of two have exactly one '1' in their binary representation,
    // Integer.bitCount(n) must equal 1.
    public boolean isPowerOfTwoBitCount(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }


    // ========================================
    // APPROACH 4: Max Integer Power of Two Divisibility
    // Time: O(1) | Space: O(1)
    // ========================================
    // The largest 32-bit signed power of 2 is 2^30 = 1073741824.
    // Since 2 is prime, all powers of 2 divide 2^30 evenly.
    public boolean isPowerOfTwoMaxInt(int n) {
        return n > 0 && (1 << 30) % n == 0;
    }
}
