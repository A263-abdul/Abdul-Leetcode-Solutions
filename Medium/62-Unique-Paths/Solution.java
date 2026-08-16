import java.util.List;
import java.util.Arrays;

/**
 * Problem: LeetCode 62 - Unique Paths (with Custom Grid & Obstacle Variations)
 * LeetCode Link: https://leetcode.com/problems/unique-paths/
 * Difficulty: Medium
 * Category: Dynamic Programming / Grid DFS
 *
 * Date Solved: Aug 16, 2026
 */

class Solution {

    // =========================================================================
    // VARIATION 1: Standard LeetCode 62 (No Obstacles)
    // =========================================================================

    // APPROACH 1.1: Top-Down DP (Recursion + Memoization)
    // Time Complexity: O(m * n) | Space Complexity: O(m * n)
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return helper(0, 0, m, n, dp);
    }

    private int helper(int r, int c, int m, int n, int[][] dp) {
        // Out of bounds
        if (r == m || c == n) {
            return 0;
        }
        // Reached destination
        if (r == m - 1 && c == n - 1) {
            return 1;
        }
        // Memoization check
        if (dp[r][c] != -1) {
            return dp[r][c];
        }
        // Result = Downward moves + Rightward moves
        dp[r][c] = helper(r + 1, c, m, n, dp) + helper(r, c + 1, m, n, dp);
        return dp[r][c];
    }

    // APPROACH 1.2: Bottom-Up DP (Tabulation) ⭐ BEST
    // Time Complexity: O(m * n) | Space Complexity: O(m * n)
    public int uniquePathsTab(int m, int n) {
        int[][] dp = new int[m][n];
        
        // Base case: Start position has exactly 1 way
        dp[0][0] = 1;
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) continue;
                int fromUp = (r > 0) ? dp[r - 1][c] : 0;
                int fromLeft = (c > 0) ? dp[r][c - 1] : 0;
                dp[r][c] = fromUp + fromLeft;
            }
        }
        return dp[m - 1][n - 1];
    }

    // APPROACH 1.3: Space-Optimized DP
    // Time Complexity: O(m * n) | Space Complexity: O(n)
    public int uniquePathsSpaceOptimized(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Top row cells all have exactly 1 path (always moving right)
        
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[c] = dp[c] + dp[c - 1];
            }
        }
        return dp[n - 1];
    }


    // =========================================================================
    // VARIATION 2: Custom Grid with Obstacles ("X" represents blocked cell)
    // =========================================================================

    // APPROACH 2.1: Custom Grid Recursion + Memoization (Top-Down)
    // Time Complexity: O(R * C) | Space Complexity: O(R * C)
    public static int countPath(List<List<String>> grid) {
        if (grid == null || grid.isEmpty() || grid.get(0).isEmpty()) return 0;
        int m = grid.size();
        int n = grid.get(0).size();
        
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return countPathMemo(0, 0, grid, dp);
    }

    private static int countPathMemo(int r, int c, List<List<String>> grid, int[][] dp) {
        // 1. Out of Bounds Check
        if (r == grid.size() || c == grid.get(0).size()) {
            return 0;
        }

        // 2. Obstacle / Blocked Cell Check
        if (grid.get(r).get(c).equals("X")) {
            return 0;
        }

        // 3. Base Case: Reached Destination
        if (r == grid.size() - 1 && c == grid.get(0).size() - 1) {
            return 1;
        }

        // 4. Memoization Check
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        // 5. Recursive Exploration & Cache
        dp[r][c] = countPathMemo(r + 1, c, grid, dp) + countPathMemo(r, c + 1, grid, dp);
        return dp[r][c];
    }
}
