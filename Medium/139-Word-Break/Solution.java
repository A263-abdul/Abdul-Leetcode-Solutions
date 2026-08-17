import java.util.*;

/**
 * Problem: LeetCode 139 - Word Break
 * LeetCode Link: https://leetcode.com/problems/word-break/
 * Difficulty: Medium
 * Category: Dynamic Programming
 *
 * Date Solved: Aug 17, 2026
 */
class Solution {

    // ========================================
    // APPROACH 1: Greedy (Wrong / Suboptimal Demo)
    // Time: O(n * m) | Space: O(1)
    // Status: ❌ INCORRECT
    // Why it fails: "catsanddog" with ["cats", "dog", "sand", "and", "cat"]
    // Greedy will match "cats", leaving "anddog". Then it matches "and", leaving "dog".
    // Wait, that works. But what about s = "cars", dict = ["car", "ca", "rs"]?
    // Greedy picks "car" first, leaving "s" (not in dict).
    // The optimal split is "ca" + "rs". Greedy fails.
    // ========================================
    public boolean wordBreakGreedy(String s, List<String> wordDict) {
        int i = 0;
        while (i < s.length()) {
            boolean matched = false;
            for (String word : wordDict) {
                if (s.substring(i).startsWith(word)) {
                    i += word.length();
                    matched = true;
                    break;
                }
            }
            if (!matched) return false;
        }
        return i == s.length();
    }


    // ========================================
    // APPROACH 2: Top-Down DP (Memoization) ⭐ BEST FOR INTERVIEWS
    // Time Complexity: O(n^2 * m) | Space Complexity: O(n)
    // Status: ✅ Accepted
    // ========================================
    private Map<Integer, Boolean> memo = new HashMap<>();
    private Set<String> wordSet;
    private int maxWordLength = 0;

    public boolean wordBreak(String s, List<String> wordDict) {
        memo.clear();
        wordSet = new HashSet<>(wordDict);
        
        // Performance optimization: get max word length to prune search space
        maxWordLength = 0;
        for (String word : wordDict) {
            maxWordLength = Math.max(maxWordLength, word.length());
        }
        
        return dfs(0, s);
    }

    private boolean dfs(int index, String s) {
        // BASE: Reached end of string
        if (index == s.length()) {
            return true;
        }

        // MEMO: Return cached result
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        // RECURSION: Try all split points up to max word length
        int endLimit = Math.min(s.length(), index + maxWordLength);
        for (int end = index + 1; end <= endLimit; end++) {
            String word = s.substring(index, end);
            if (wordSet.contains(word) && dfs(end, s)) {
                memo.put(index, true);
                return true;
            }
        }

        memo.put(index, false);
        return false;
    }


    // ========================================
    // APPROACH 3: Bottom-Up DP (Tabulation) ⭐ BEST PERFORMANCE
    // Time Complexity: O(n^2) | Space Complexity: O(n)
    // Status: ✅ Accepted
    // ========================================
    public boolean wordBreakTab(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // Base case: Empty string

        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        for (int i = 1; i <= s.length(); i++) {
            // Prune inner loop: substring cannot exceed maxLen
            int startLimit = Math.max(0, i - maxLen);
            for (int j = i - 1; j >= startLimit; j--) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // Found a valid split, move to next index
                }
            }
        }

        return dp[s.length()];
    }
}
