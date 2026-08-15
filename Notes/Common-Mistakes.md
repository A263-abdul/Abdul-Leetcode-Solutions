# ❌ Common Mistakes & Fixes

> Mistakes are the best teachers. Document every mistake so you never repeat it.

---

## 🐛 Bug Patterns

### 1. Integer Overflow
```java
// ❌ WRONG — Integer.MAX_VALUE + 1 overflows to negative
Arrays.fill(dp, Integer.MAX_VALUE);
dp[i] = Math.min(dp[i], dp[i - coin] + 1); // OVERFLOW!

// ✅ CORRECT — Use a safe sentinel
Arrays.fill(dp, amount + 1); // Any value > amount means "impossible"
```

### 2. Off-by-One in DP Array Size
```java
// ❌ WRONG — Array too small
int[] dp = new int[n]; // index n doesn't exist!

// ✅ CORRECT — Size n+1 to accommodate index 0 through n
int[] dp = new int[n + 1];
```

### 3. Same Index Used Twice in Two Sum
```java
// ❌ WRONG — nums[i] + nums[i] shouldn't count
if (map.containsKey(complement)) return new int[]{map.get(complement), i};

// ✅ CORRECT — check different index
if (map.containsKey(complement) && map.get(complement) != i)
```

### 4. Backtracking — Not Removing Last Element
```java
// ❌ WRONG — List keeps growing, no backtrack
current.add(cands[i]);
backtrack(...);
// forgot: current.remove(current.size() - 1);

// ✅ CORRECT
current.add(cands[i]);
backtrack(...);
current.remove(current.size() - 1); // UNDO the choice
```

### 5. Null Pointer in HashMap
```java
// ❌ Can throw NPE if value is null
if (map.get(key) != null && ...) // risky

// ✅ Use containsKey first
if (map.containsKey(key)) { int val = map.get(key); ... }
```

---

## 📌 Algorithm-Specific Mistakes

### Dynamic Programming
| Mistake | Fix |
|---------|-----|
| No base case | Always set `dp[0]` before loop |
| Wrong recurrence | Trace through small examples by hand |
| Memo array wrong size | `new int[n+1]`, not `new int[n]` |
| Integer overflow in min | Use `amount+1` sentinel, not MAX_VALUE |

### Backtracking
| Mistake | Fix |
|---------|-----|
| Not backtracking (removing last element) | Always add remove after recursive call |
| Generating duplicates | Use start index to avoid re-using earlier elements |
| Missing deep copy of list | Use `new ArrayList<>(current)` when adding to results |

### Binary Search
| Mistake | Fix |
|---------|-----|
| `mid = (lo + hi) / 2` can overflow | Use `mid = lo + (hi - lo) / 2` |
| Infinite loop with `lo <= hi` | Carefully set `lo = mid + 1` or `hi = mid - 1` |

### Two Pointers
| Mistake | Fix |
|---------|-----|
| Moving both pointers in wrong direction | Draw out the pointer movement first |
| Not handling duplicates | Skip duplicates explicitly with a while loop |

---

## 💡 Debug Checklist

Before submitting, always check:
- [ ] Base cases handled? (n=0, empty array, amount=0)
- [ ] Off-by-one errors? (array bounds, loop start/end)
- [ ] Integer overflow? (use `long` or safe sentinels)
- [ ] Null pointer? (check before accessing)
- [ ] Deep copy? (for lists in backtracking results)
- [ ] Edge cases? (single element, all same elements)
