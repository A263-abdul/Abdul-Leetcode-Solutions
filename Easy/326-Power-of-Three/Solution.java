/**
 * Problem: LeetCode 326 - Power of Three
 * LeetCode Link: https://leetcode.com/problems/power-of-three/
 * Difficulty: Easy
 * Category: Math / Recursion
 *
 * Date Solved: Aug 22, 2026
 */
class Solution {

    // ========================================
    // APPROACH 1: Iterative Division (User's Solution) ✅
    // Time: O(log3 n) | Space: O(1)
    // ========================================
    // Repeatedly divide n by 3 as long as n is divisible by 3.
    // If n is a power of 3, it must eventually reach 1.
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 3 == 0) {
            n = n / 3;
        }

        return n == 1;
    }


    // ========================================
    // APPROACH 2: Max Power of 3 Divisibility ⭐ BEST (O(1))
    // Time: O(1) | Space: O(1)
    // ========================================
    // The maximum power of 3 that fits inside a signed 32-bit integer is 3^19 = 1162261467.
    // Since 3 is a prime number, the only positive divisors of 3^19 are powers of 3.
    // Therefore, n is a power of 3 if and only if 1162261467 is divisible by n.
    public boolean isPowerOfThreeMaxInt(int n) {
        return n > 0 && 1162261467 % n == 0;
    }


    // ========================================
    // APPROACH 3: Recursive Division
    // Time: O(log3 n) | Space: O(log3 n)
    // ========================================
    public boolean isPowerOfThreeRecursive(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (n % 3 != 0) return false;
        return isPowerOfThreeRecursive(n / 3);
    }


    // ========================================
    // APPROACH 4: Logarithm with Epsilon Check
    // Time: O(1) | Space: O(1)
    // ========================================
    // Check if log3(n) is an integer using Math.log10 to avoid precision issues.
    public boolean isPowerOfThreeLog(int n) {
        if (n <= 0) return false;
        double logVal = Math.log10(n) / Math.log10(3);
        return (logVal - (int) logVal) < 1e-10;
    }
}
