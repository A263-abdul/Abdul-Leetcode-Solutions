# 58. Length of Last Word

**Difficulty:** Easy 🟢  
**LeetCode:** [Link](https://leetcode.com/problems/length-of-last-word/)  
**Category:** String / Two Pointers  
**Date Solved:** Aug 19, 2026

---

## 📝 Problem Statement

Given a string `s` consisting of words and spaces, return the **length of the last word** in the string.

A word is a maximal substring consisting of non-space characters only.

### Examples:
```
Input: s = "Hello World"
Output: 5

Input: s = "   fly me   to   the moon  "
Output: 4

Input: s = "luffy is still joyboy"
Output: 6
```

### Constraints:
- `1 <= s.length <= 10^4`
- `s` consists of English letters and spaces `' '`
- There will be at least one word in `s`

---

## 🎯 Solution Comparison

| Approach | Time | Space | Status | Notes |
|---|---|---|---|---|
| **Reverse Iteration** | $O(n)$ | $O(1)$ | ⭐ **BEST** | No extra memory, skips trailing spaces elegantly |
| **Two Pointers** | $O(n)$ | $O(1)$ | ✅ Good | Uses `end - start` formula, clean |
| **Split + Trim** | $O(n)$ | $O(n)$ | ✅ Readable | Pythonic style — good for readability |
| **Regex Split** | $O(n)$ | $O(n)$ | ✅ Works | Slightly risky with leading spaces only |

---

## 🧠 Core Algorithm: Reverse Iteration

Walk backwards from the end of the string:
1. **Skip trailing spaces** → move `i` left while `s.charAt(i) == ' '`
2. **Count the word** → count chars while `s.charAt(i) != ' '`

```
"   fly me   to   the moon  "
                           ↑
                       Start here (i)
                           ↓
Skip trailing spaces → i lands on 'n'
                           ↓
Count backwards → m-o-o-n → wordLength = 4
```

---

## 💻 Java Solution (Best Approach)

```java
class Solution {
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
}
```

---

## 🔍 Dry Run

**Input:** `"   fly me   to   the moon  "`

| Phase | Action | i value | wordLength |
|---|---|---|---|
| Start | i = 27 (last index) | 27 | 0 |
| Skip spaces | `s[27]=' '` → `i--` | 26 | 0 |
| Skip spaces | `s[26]=' '` → `i--` | 25 | 0 |
| Count word | `s[25]='n'` → count++ | 24 | 1 |
| Count word | `s[24]='o'` → count++ | 23 | 2 |
| Count word | `s[23]='o'` → count++ | 22 | 3 |
| Count word | `s[22]='m'` → count++ | 21 | 4 |
| Stop | `s[21]=' '` → break | — | **4** ✅ |

---

## ❌ Common Mistakes

1. **Mistake:** Not skipping trailing spaces before counting.  
   **Fix:** Always run the first `while` loop to skip spaces.
2. **Mistake:** Using `split(" ")` instead of `split("\\s+")`.  
   **Fix:** `split(" ")` creates empty strings for consecutive spaces. Use `split("\\s+")` or `trim().split("\\s+")` for safety.

---

**Status:** ✅ SOLVED | **Attempts:** 1 | **Best Approach:** Reverse Iteration O(1) Space
