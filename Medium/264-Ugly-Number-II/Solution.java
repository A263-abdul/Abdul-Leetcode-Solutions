/**
 * Problem: Ugly Number II
 * LeetCode Link: https://leetcode.com/problems/ugly-number-ii/
 * Difficulty: Medium
 * Category: Dynamic Programming / Math / Three Pointers
 *
 * Date Solved: Aug 15, 2026
 * Time Taken: 35 mins
 *
 * Key Insight:
 *   Every ugly number = 2^a * 3^b * 5^c (a,b,c >= 0)
 *   If x is ugly → 2x, 3x, 5x are also ugly.
 *   So we can BUILD ugly numbers from previous ugly numbers.
 */

class Solution {

    // ========================================
    // APPROACH 1: Brute Force — Check Each Integer
    // Time: O(n * log k) per check, very slow overall
    // Space: O(1)
    // Status: ❌ TLE for large n
    // ========================================

    public int nthUglyNumberBrute(int n) {
        int count = 0;
        int num = 0;
        while (count < n) {
            num++;
            if (isUgly(num)) count++;
        }
        return num;
    }

    private boolean isUgly(int num) {
        if (num <= 0) return false;
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
        return num == 1; // if reduced to 1 → all prime factors were 2,3,5
    }


    // ========================================
    // APPROACH 2: DP + 3 Pointers ⭐ BEST
    // Time: O(n) | Space: O(n)
    // Status: ✅ Accepted (Optimal)
    //
    // Core idea:
    //   Maintain 3 pointers i2, i3, i5.
    //   Next ugly = min(arr[i2]*2, arr[i3]*3, arr[i5]*5)
    //   Increment ALL pointers whose product equals the chosen min.
    //   (Using separate `if` — NOT `else if` — to handle duplicates like 6 = 2×3 = 3×2)
    // ========================================

    public int nthUglyNumber(int n) {

        int[] arr = new int[n + 1];
        // arr[i] = i-th ugly number, arr[n] = answer

        int i2 = 1, i3 = 1, i5 = 1;

        arr[1] = 1; // 1st ugly number is always 1

        for (int i = 2; i <= n; i++) {

            int next2 = arr[i2] * 2; // next candidate from ×2 family
            int next3 = arr[i3] * 3; // next candidate from ×3 family
            int next5 = arr[i5] * 5; // next candidate from ×5 family

            int minUgly = Math.min(next2, Math.min(next3, next5));

            arr[i] = minUgly;

            // ⚠️ Use separate `if` (not else-if) to handle duplicates:
            // e.g. 6 = arr[i2]*2 AND arr[i3]*3 → both pointers must advance
            if (minUgly == next2) i2++;
            if (minUgly == next3) i3++;
            if (minUgly == next5) i5++;
        }

        return arr[n];
    }
}
