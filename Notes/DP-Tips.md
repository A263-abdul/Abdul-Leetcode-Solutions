# 🧠 Dynamic Programming — Tips & Patterns

> Master DP by recognizing patterns, not memorizing solutions.

---

## 📌 What is DP?

Dynamic Programming = **Recursion + Memoization** (or Tabulation)

It applies when a problem has:
1. **Overlapping Subproblems** — Same subproblems solved multiple times
2. **Optimal Substructure** — Optimal solution built from optimal sub-solutions

---

## 🔄 Two Forms of DP

### Top-Down (Memoization)
```java
int[] memo = new int[n + 1];
Arrays.fill(memo, -1);

int solve(int i) {
    if (base_case) return base_value;
    if (memo[i] != -1) return memo[i];
    memo[i] = /* recursive calls */;
    return memo[i];
}
```

### Bottom-Up (Tabulation) ⭐ Preferred
```java
int[] dp = new int[n + 1];
dp[0] = base_value;

for (int i = 1; i <= n; i++) {
    dp[i] = /* transition using dp[i-1], dp[i-2], etc. */;
}
return dp[n];
```

---

## 🎯 Classic DP Patterns

### 1️⃣ Linear DP (1D)
| Problem | Recurrence | Solved |
|---------|-----------|--------|
| Fibonacci | `dp[i] = dp[i-1] + dp[i-2]` | ✅ |
| House Robber | `dp[i] = max(dp[i-1], dp[i-2] + nums[i])` | ✅ |
| Climbing Stairs | `dp[i] = dp[i-1] + dp[i-2]` | ⏳ |
| Min Cost Climbing | `dp[i] = cost[i] + min(dp[i-1], dp[i-2])` | ⏳ |

### 2️⃣ Unbounded Knapsack
| Problem | Recurrence |
|---------|-----------|
| Coin Change | `dp[i] = min(dp[i], dp[i-coin] + 1)` |
| Coin Change II | `dp[i] += dp[i-coin]` |

### 3️⃣ 0/1 Knapsack
| Problem | Recurrence |
|---------|-----------|
| 0/1 Knapsack | `dp[i][w] = max(dp[i-1][w], dp[i-1][w-wt[i]] + val[i])` |
| Subset Sum | `dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]]` |

---

## ⚡ Optimization Tricks

### Space Optimization
When `dp[i]` only depends on `dp[i-1]` and `dp[i-2]`:
```java
// Replace O(n) array with O(1) variables:
int prev2 = 0, prev1 = 0;
for (int num : nums) {
    int curr = Math.max(prev1, prev2 + num);
    prev2 = prev1;
    prev1 = curr;
}
```

### Sentinel Values
- Use `amount + 1` instead of `Integer.MAX_VALUE` → avoid overflow on `+1`
- Use `-1` for "not computed yet" in memoization (if result can be 0)
- Use `-2` for "not computed" when -1 is a valid result

---

## 🚨 Common Mistakes

| Mistake | Fix |
|---------|-----|
| Using `Integer.MAX_VALUE + 1` | Use `amount + 1` as sentinel |
| Missing base case | Always define `dp[0]` before loop |
| Off-by-one in array size | `dp = new int[n + 1]` not `dp = new int[n]` |
| Recomputing states | Add memoization / check memo first |

---

## 🔗 Must-Solve DP Problems (Roadmap)

### Easy
- [ ] Climbing Stairs (509 pattern)
- [x] Fibonacci Number
- [ ] Min Cost Climbing Stairs

### Medium  
- [x] House Robber
- [x] Coin Change
- [ ] Longest Increasing Subsequence
- [ ] Unique Paths
- [ ] Jump Game
- [ ] Partition Equal Subset Sum

### Hard
- [ ] Edit Distance
- [ ] Burst Balloons
- [ ] Regular Expression Matching
