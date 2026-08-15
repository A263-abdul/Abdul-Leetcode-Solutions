/**
 * Problem: Convert Time
 * LeetCode Link: https://leetcode.com/problems/convert-the-temperature/
 * Difficulty: Easy
 * Category: Greedy / Math
 *
 * Date Solved: Aug 15, 2026
 * Time Taken: 20 mins
 */

class Solution {

    // ========================================
    // APPROACH 1: Simulation / Brute Counting
    // Time: O(S) | Space: O(1)
    // Status: ❌ Works but very slow for large time gaps
    // ========================================

    public int convertTimeBrute(String current, String correct) {
        String[] c = current.split(":");
        String[] g = correct.split(":");
        int curMin = Integer.parseInt(c[0]) * 60 + Integer.parseInt(c[1]);
        int corMin = Integer.parseInt(g[0]) * 60 + Integer.parseInt(g[1]);
        int ops = 0;
        while (curMin < corMin) {
            curMin++;
            ops++;
        }
        return ops;
    }


    // ========================================
    // APPROACH 2: Greedy with Denominations
    // Time: O(1) | Space: O(1)
    // Status: ✅ Accepted
    // ========================================

    public int convertTimeGreedy(String current, String correct) {
        String[] c = current.split(":");
        String[] g = correct.split(":");
        int diff = (Integer.parseInt(g[0]) - Integer.parseInt(c[0])) * 60
                 + (Integer.parseInt(g[1]) - Integer.parseInt(c[1]));
        int ops = 0;
        int[] increments = {60, 15, 5, 1};
        for (int inc : increments) {
            ops += diff / inc;
            diff %= inc;
        }
        return ops;
    }


    // ========================================
    // APPROACH 3: Greedy (Inline, Cleanest) ⭐ BEST
    // Time: O(1) | Space: O(1)
    // Status: ✅ Accepted (Most Readable & Efficient)
    // ========================================

    public int convertTime(String current, String correct) {
        int cur  = Integer.parseInt(current.substring(0, 2)) * 60
                 + Integer.parseInt(current.substring(3));
        int corr = Integer.parseInt(correct.substring(0, 2)) * 60
                 + Integer.parseInt(correct.substring(3));
        int diff = corr - cur, ops = 0;
        for (int inc : new int[]{60, 15, 5, 1}) {
            ops += diff / inc;
            diff %= inc;
        }
        return ops;
    }
}
