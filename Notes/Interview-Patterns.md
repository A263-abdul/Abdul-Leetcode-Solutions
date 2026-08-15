# 🔥 Interview Patterns — Cheat Sheet

> Recognize the pattern → Know the approach → Solve it fast.

---

## 🗺️ Pattern → Algorithm Map

| Pattern Signal | Algorithm | Example Problems |
|---------------|-----------|-----------------|
| "Find two numbers that..." | Two Pointers / HashMap | Two Sum, 3Sum |
| "Subarray with max/min..." | Sliding Window | Max Subarray, Longest Substring |
| "All combinations/subsets..." | Backtracking | Combination Sum, Subsets |
| "Min/max ways to reach..." | Dynamic Programming | Coin Change, Climbing Stairs |
| "Sorted array, find element..." | Binary Search | Search in Rotated Array |
| "Tree traversal / path sum..." | DFS / BFS | Path Sum, Level Order |
| "Shortest path in graph..." | BFS / Dijkstra | Word Ladder, Network Delay |
| "Top K elements..." | Heap / QuickSelect | Kth Largest, Top K Frequent |
| "Intervals merge/overlap..." | Sort + Sweep | Merge Intervals |
| "Repeated pattern in string..." | KMP / Sliding Window | Repeated Substring |

---

## ⚡ Quick Pattern Recognition

### Array Problems
```
- Pair / Two elements → Two Pointers or HashMap
- Subarray sum/length → Sliding Window or Prefix Sum
- Sorted + Binary Search vibes → Binary Search
- All permutations/combinations → Backtracking
```

### String Problems
```
- Anagram / Character count → HashMap / Array of 26
- Palindrome → Two Pointers from center
- Pattern match → KMP or Sliding Window
```

### Tree/Graph Problems
```
- Level-by-level → BFS (Queue)
- Path from root to leaf → DFS (Stack/Recursion)
- Connected components → Union-Find or DFS
- Shortest path → BFS (unweighted) / Dijkstra (weighted)
```

---

## 🔑 Key Templates

### Two Pointers
```java
int left = 0, right = nums.length - 1;
while (left < right) {
    if (condition) { /* do something */ left++; }
    else right--;
}
```

### Sliding Window (Fixed Size)
```java
int windowSum = 0;
for (int i = 0; i < k; i++) windowSum += nums[i];
int maxSum = windowSum;
for (int i = k; i < nums.length; i++) {
    windowSum += nums[i] - nums[i - k];
    maxSum = Math.max(maxSum, windowSum);
}
```

### Sliding Window (Variable Size)
```java
int left = 0, result = 0;
for (int right = 0; right < s.length(); right++) {
    // expand window by including s[right]
    while (/* window invalid */) { left++; } // shrink
    result = Math.max(result, right - left + 1);
}
```

### Binary Search
```java
int lo = 0, hi = nums.length - 1;
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2; // avoids overflow
    if (nums[mid] == target) return mid;
    else if (nums[mid] < target) lo = mid + 1;
    else hi = mid - 1;
}
return -1;
```

### BFS Template
```java
Queue<Integer> queue = new LinkedList<>();
Set<Integer> visited = new HashSet<>();
queue.offer(start);
visited.add(start);
while (!queue.isEmpty()) {
    int node = queue.poll();
    for (int neighbor : graph.get(node)) {
        if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            queue.offer(neighbor);
        }
    }
}
```

### DFS / Backtracking Template
```java
void backtrack(/* state */, List<Integer> current, List<List<Integer>> result) {
    if (/* goal reached */) { result.add(new ArrayList<>(current)); return; }
    for (/* choices */) {
        current.add(choice);       // make choice
        backtrack(/* new state */);// explore
        current.remove(current.size() - 1); // undo choice
    }
}
```

---

## 📅 Study Roadmap

### Week 1-2: Arrays & Strings
- [ ] Two Sum variants
- [ ] Sliding Window problems
- [ ] Prefix Sum

### Week 3-4: Dynamic Programming
- [x] Fibonacci, Climbing Stairs
- [x] House Robber, Coin Change
- [ ] LCS, LIS, Edit Distance

### Week 5-6: Trees & Graphs
- [ ] BST operations
- [ ] BFS/DFS traversals
- [ ] Graph problems

### Week 7-8: Advanced
- [ ] Heaps / Priority Queues
- [ ] Tries
- [ ] Union-Find

---

> 💬 **"You don't rise to the level of your goals. You fall to the level of your systems."** — James Clear
