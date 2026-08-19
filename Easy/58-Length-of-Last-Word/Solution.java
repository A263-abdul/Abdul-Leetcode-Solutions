/**
 * Problem: LeetCode 58 - Length of Last Word
 * LeetCode Link: https://leetcode.com/problems/length-of-last-word/
 * Difficulty: Easy
 * Category: String / Two Pointers
 *
 * Date Solved: Aug 19, 2026
 */
class Solution {

    // ========================================
    // APPROACH 1: Reverse Iteration ⭐ BEST
    // Time: O(n) | Space: O(1)
    // ========================================
    // Walk backwards: skip trailing spaces, then count letters until next space.
    // No extra memory needed — pure pointer arithmetic.
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;

        // Skip trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Count characters of the last word
        int wordLength = 0;
        while (i >= 0 && s.charAt(i) != ' ') {
            wordLength++;
            i--;
        }

        return wordLength;
    }


    // ========================================
    // APPROACH 2: Two Pointers (start/end)
    // Time: O(n) | Space: O(1)
    // ========================================
    // Use two indices: end marks last char of word, start walks left to space.
    // Return end - start directly.
    public int lengthOfLastWordTwoPointers(String s) {
        int end = s.length() - 1;

        // Skip trailing spaces
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }

        // Find start of last word
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }

        return end - start;
    }


    // ========================================
    // APPROACH 3: Split + Trim (Simple)
    // Time: O(n) | Space: O(n)
    // ========================================
    // trim() removes leading/trailing spaces.
    // split("\\s+") splits on one or more whitespace characters.
    // Pick the last token.
    public int lengthOfLastWordSplit(String s) {
        String[] words = s.trim().split("\\s+");
        return words[words.length - 1].length();
    }


    // ========================================
    // APPROACH 4: Regex Split (No trim needed)
    // Time: O(n) | Space: O(n)
    // ========================================
    // split("\\s+") alone can leave an empty string at index 0 if there
    // are leading spaces — but trailing spaces mean the last token is always
    // a real word when used with trim, or use Approach 3 for safety.
    public int lengthOfLastWordRegex(String s) {
        String[] words = s.split("\\s+");
        return words[words.length - 1].length();
    }
}
