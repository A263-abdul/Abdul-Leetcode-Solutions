/**
 * Problem: LeetCode 3622 - Check Divisibility by Digit Sum and Product
 * LeetCode Link: https://leetcode.com/problems/check-divisibility-by-digit-sum-and-product/
 * Difficulty: Easy
 * Category: Math / Simulation
 *
 * Date Solved: Aug 22, 2026
 */
class Solution {

    // ========================================
    // APPROACH 1: Digit Extraction & Simulation ⭐ BEST
    // Time: O(log10 n) | Space: O(1)
    // ========================================
    // 1. Preserve the original value of n.
    // 2. Extract digits one-by-one using modulo 10 (n % 10) and integer division (n / 10).
    // 3. Accumulate the sum and product of all digits.
    // 4. Check if original is divisible by (sum + product).
    public boolean checkDivisibility(int n) {
        int original = n;
        int sum = 0;
        int product = 1;

        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            product *= digit;
            n /= 10;
        }

        int totalDivisor = sum + product;
        return original % totalDivisor == 0;
    }
}
