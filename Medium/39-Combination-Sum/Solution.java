import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Combination Sum
 * LeetCode Link: https://leetcode.com/problems/combination-sum/
 * Difficulty: Medium
 * Category: Backtracking
 *
 * Date Solved: Aug 15, 2026
 * Time Taken: 40 mins
 */

class Solution {

    // ========================================
    // APPROACH 1: Backtracking (Start from Index 0 each time)
    // Time: O(N^(T/M)) | Space: O(T/M) — T=target, M=min candidate
    // Status: ❌ Produces duplicates
    // ========================================

    public List<List<Integer>> combinationSumNaive(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackNaive(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrackNaive(int[] cands, int rem, int start,
                                 List<Integer> current, List<List<Integer>> result) {
        if (rem == 0) { result.add(new ArrayList<>(current)); return; }
        for (int i = 0; i < cands.length; i++) {
            if (cands[i] <= rem) {
                current.add(cands[i]);
                backtrackNaive(cands, rem - cands[i], i, current, result);
                current.remove(current.size() - 1);
            }
        }
    }


    // ========================================
    // APPROACH 2: Backtracking with Start Index ✅
    // Time: O(N^(T/M)) | Space: O(T/M)
    // Status: ✅ Accepted
    // ========================================

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] cands, int rem, int start,
                            List<Integer> current, List<List<Integer>> result) {
        if (rem == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < cands.length; i++) {
            if (cands[i] <= rem) {
                current.add(cands[i]);
                backtrack(cands, rem - cands[i], i, current, result); // i not i+1 → reuse
                current.remove(current.size() - 1);
            }
        }
    }


    // ========================================
    // APPROACH 3: Backtracking + Early Termination ⭐ BEST
    // Time: O(N^(T/M)) | Space: O(T/M)
    // Status: ✅ Accepted (Pruned — fastest in practice)
    // ========================================

    public List<List<Integer>> combinationSumOpt(int[] candidates, int target) {
        java.util.Arrays.sort(candidates); // sort enables pruning
        List<List<Integer>> result = new ArrayList<>();
        backtrackOpt(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrackOpt(int[] cands, int rem, int start,
                               List<Integer> current, List<List<Integer>> result) {
        if (rem == 0) { result.add(new ArrayList<>(current)); return; }
        for (int i = start; i < cands.length; i++) {
            if (cands[i] > rem) break; // ⭐ prune: sorted so no point continuing
            current.add(cands[i]);
            backtrackOpt(cands, rem - cands[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }
}
