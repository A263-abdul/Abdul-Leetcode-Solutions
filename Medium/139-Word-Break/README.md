# Word Break (LeetCode 139) - Quick Reference 📋

## Problem in 30 Seconds

**Question:** Can you split a string `s` into words that exist in a dictionary?  
**Input:** `s = "abcdef"`, `wordDict = ["ab", "abc", "cd", "def"]`  
**Output:** `true` (split as `"abc"` + `"def"`)

---

## Solution Comparison

| Approach | Time Complexity | Space Complexity | Best For |
|---|---|---|---|
| **Memoization** | $O(n^2)$ | $O(n)$ | Interviews, intuitive top-down |
| **Bottom-Up DP** | $O(n^2)$ | $O(n)$ | Clean, cache-friendly iteration |
| **Greedy** | $O(n)$ | $O(1)$ | ❌ **DOESN'T WORK** |

---

## Core Algorithm (Memoization)

```java
import java.util.*;

public class Solution {
    private Map<Integer, Boolean> memo = new HashMap<>();
    private Set<String> wordSet;

    public boolean wordBreak(String s, List<String> wordDict) {
        memo.clear();
        wordSet = new HashSet<>(wordDict); // Convert to Set for O(1) lookup
        return canForm(0, s);
    }

    private boolean canForm(int idx, String s) {
        if (idx == s.length()) return true; // Base case: reached the end
        if (memo.containsKey(idx)) return memo.get(idx);

        for (int end = idx + 1; end <= s.length(); end++) {
            String substring = s.substring(idx, end);
            if (wordSet.contains(substring) && canForm(end, s)) {
                memo.put(idx, true);
                return true;
            }
        }

        memo.put(idx, false);
        return false;
    }
}
```

---

## Key Insights

* **✅ DO:**
  * Convert `wordDict` to a `HashSet` to achieve $O(1)$ lookup times.
  * Use the empty string at the end of recursion (`index == len(s)`) as the successful base case.
  * Cache subproblem outcomes in a memoization map/array.
  * Try every possible valid split boundary at each step.
* **❌ DON'T:**
  * Don't use a greedy approach (taking the first matching word you find).
  * Don't forget to check the cache before starting recursion loops.
  * Don't forget the base case when `index == s.length()`.

---

## Complexity Analysis

* **TIME:** $O(n^2)$ (effectively, when optimized with a lookup set)
  * There are $n$ possible subproblems (index `0` to `n`).
  * For each subproblem, we check up to $n$ splits.
  * String slicing and Set check take $O(L)$ where $L$ is max word length.
* **SPACE:** $O(n)$
  * Memo cache stores up to $n$ elements: $O(n)$.
  * Recursion stack goes up to depth $n$: $O(n)$.

---

## Real Examples

### Example 1: Success ✅
* **Input:** `s = "abcdef"`, `wordDict = ["ab", "abc", "cd", "def", "abcd"]`

```
  abcdef
    /
   abc ✓
    |
   def ✓
    |
  empty ✅
```
* **Output:** `true`
* **Path:** `"abc"` + `"def"`

### Example 2: Failure ❌
* **Input:** `s = "skateboard"`, `wordDict = ["bo", "rd", "ate", "t", "ska", "sk", "boar"]`

**Possible starts:**
* `"ska"` $\rightarrow$ `"teboard"` (no word starts with t/e)
* `"sk"` $\rightarrow$ `"ateboard"` (no word starts with b after `"ate"`)

All decision branches are exhausted without reaching the end.
* **Output:** `false`

---

## Common Mistakes

### ❌ Mistake 1: Using Greedy
```java
// WRONG!
List<String> result = new ArrayList<>();
int i = 0;
while (i < s.length()) {
    boolean matched = false;
    for (String word : wordDict) {
        if (s.substring(i).startsWith(word)) {
            result.add(word);
            i += word.length();
            matched = true;
            break; // Greedily picking the first match
        }
    }
    if (!matched) break;
}
return i == s.length();
```
* **Why it fails:** Consider `s = "cars"`, `wordDict = ["car", "ca", "rs"]`. Greedy grabs `"car"` first, leaving `"s"`, which fails. The correct split is `"ca" + "rs"`.

### ❌ Mistake 2: Forgetting Memoization
```java
// SLOW! - O(2^n)
private boolean canForm(int idx, String s) {
    if (idx == s.length()) return true;
    for (int end = idx + 1; end <= s.length(); end++) {
        if (wordSet.contains(s.substring(idx, end)) && canForm(end, s)) { // ❌ Recomputes!
            return true;
        }
    }
    return false;
}
```

### ✅ Correct Version:
```java
// FAST! - O(n^2)
private boolean canForm(int idx, String s) {
    if (idx == s.length()) return true;
    if (memo.containsKey(idx)) return memo.get(idx); // ✅ Checked Cache!

    for (int end = idx + 1; end <= s.length(); end++) {
        if (wordSet.contains(s.substring(idx, end)) && canForm(end, s)) {
            memo.put(idx, true);
            return true;
        }
    }

    memo.put(idx, false);
    return false;
}
```

---

## Interview Tips 💡

### What Interviewers Look For:
* ✅ Can you identify that greedy doesn't work?
* ✅ Do you recognize this as a dynamic programming problem?
* ✅ Can you explain how memoization prevents exponential complexity?
* ✅ Do you optimize dictionary lookup time from $O(m)$ to $O(1)$?

### Follow-up Questions:
* *"Can you do it iteratively?"* $\rightarrow$ Yes, bottom-up DP.
* *"What's the space complexity?"* $\rightarrow$ $O(n)$ for the memo map + $O(n)$ recursion stack.
* *"Can you find the words used?"* $\rightarrow$ Yes, track the path indices during DP traversal.
* *"What if the dictionary is extremely large?"* $\rightarrow$ Use a Trie instead of a HashSet to prune mismatching prefixes early.

---

## Code Template (Java)

```java
import java.util.*;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // STEP 1: Convert to set for O(1) lookup
        Set<String> wordSet = new HashSet<>(wordDict);
        
        // STEP 2: Initialize memoization map
        Map<Integer, Boolean> memo = new HashMap<>();
        
        // STEP 3: Define helper method
        return dfs(0, s, wordSet, memo);
    }
    
    private boolean dfs(int index, String s, Set<String> wordSet, Map<Integer, Boolean> memo) {
        // BASE: Reached the end
        if (index == s.length()) {
            return true;
        }
        
        // MEMO: Return cached result
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        
        // RECURSION: Try all valid words
        for (int end = index + 1; end <= s.length(); end++) {
            String substring = s.substring(index, end);
            if (wordSet.contains(substring) && dfs(end, s, wordSet, memo)) {
                memo.put(index, true);
                return true;
            }
        }
        
        // NOT FOUND: Cache false
        memo.put(index, false);
        return false;
    }
}
```

---

## Alternative: Bottom-Up DP (Java)

```java
public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> wordSet = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true; // Base case: Empty string
    
    for (int i = 1; i <= s.length(); i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && wordSet.contains(s.substring(j, i))) {
                dp[i] = true;
                break; // Found valid split, stop checking previous indexes
            }
        }
    }
    
    return dp[s.length()];
}
```

### Advantages:
* No recursion limits (avoids stack overflow).
* Easier to understand iterative flow.
* Same time complexity but eliminates auxiliary call overhead.

---

## Test Your Understanding

* **Question 1: Why doesn't greedy work?**
  <details> <summary>Answer</summary> Taking the first matching word might consume character sequences that are required for a valid split of subsequent substrings. </details>

* **Question 2: What is the base case?**
  <details> <summary>Answer</summary> An empty string at index equal to the string length (`index == s.length()`), which means the entire word has been successfully matched. </details>

* **Question 3: Why convert wordDict to a Set?**
  <details> <summary>Answer</summary> Searching a List takes $O(m)$ time, whereas a HashSet lookup takes $O(1)$ time on average, saving a linear factor. </details>

* **Question 4: Can you do this in O(n) time?**
  <details> <summary>Answer</summary> No. Even with memoization, we must check all possible split boundaries, which yields $O(n^2)$ subproblems in the worst-case. </details>

---

## Variations & Related Problems

| Problem | Difference | Link |
|---|---|---|
| **Word Break II (140)** | Return all possible decompositions | Hard |
| **Word Break III** | Find the minimum number of words needed | Medium |
| **Partition to K Equal Sums** | Similar partition matching DP concept | Medium |

---

## Performance Tricks

### Trick 1: Early Termination via Maximum Word Length
```java
// Optimize by avoiding checking substrings longer than the longest word in the dict
int maxLen = 0;
for (String word : wordDict) {
    maxLen = Math.max(maxLen, word.length());
}

// Inner loop bounds:
for (int end = index + 1; end <= Math.min(s.length(), index + maxLen); end++) {
    ...
}
```

### Trick 2: Reverse Iteration
* Checking splits backwards from the end of the string first often terminates earlier in actual test inputs, though worst-case is still $O(n^2)$.

---

## Last-Minute Checklist ✓

- [x] Greedy won't work (know a counterexample, like `"cars"` with `["car", "ca", "rs"]`).
- [x] Need memoization to prevent $O(2^n)$ runtime.
- [x] Base case is the empty string (`index == s.length()`).
- [x] Convert list to a set.
- [x] Time complexity: $O(n^2)$ | Space complexity: $O(n)$.
- [x] Can write both Top-Down and Bottom-Up forms in under 3 minutes.

---

## 🎯 Remember

> **"Can I take ONE word from the start AND form the REST? If yes to both, then yes to the whole."**
> 
> This simple question is the entire algorithm! Everything else is just implementation. 🚀
