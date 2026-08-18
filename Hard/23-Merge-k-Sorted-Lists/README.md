# 23. Merge k Sorted Lists

**Difficulty:** Hard 🔴  
**LeetCode:** [Link](https://leetcode.com/problems/merge-k-sorted-lists/)  
**Category:** Heap (Priority Queue) / Divide and Conquer / Merge Sort  
**Date Solved:** Aug 18, 2026

---

## 📝 Problem Statement

You are given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order.  
Merge all the linked-lists into one sorted linked-list and return it.

### Example 1:
```
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
```

### Constraints:
- `k == lists.length`
- `0 <= k <= 10^4`
- `0 <= lists[i].length <= 500`
- `-10^4 <= lists[i][j] <= 10^4`

---

## 🎯 Solution Comparison

| Approach | Time Complexity | Space Complexity | Status | Pros/Cons |
|---|---|---|---|---|
| **Brute Force** | $O(N \log N)$ | $O(N)$ | ❌ Slow | Copy all nodes to list, sort, and rebuild. |
| **Compare One by One** | $O(k \cdot N)$ | $O(1)$ | ❌ TLE | Scan first nodes of all $k$ lists at each step. |
| **Divide and Conquer** | $O(N \log k)$ | $O(\log k)$ | ✅ Optimal | Merge lists pairwise recursively. |
| **Min-Heap (Priority Queue)** | $O(N \log k)$ | $O(k)$ | ⭐ **BEST** | Reusable priority queue, easy streaming. |

---

## 🧠 Core Algorithm: Min-Heap

The central idea is:
1. Maintain a **Min-Heap** of size $k$, containing the current head node of each of the $k$ lists.
2. At each step, poll the smallest node from the Heap. This node is added to our merged result list.
3. If the polled node has a `next` node in its original list, insert that `next` node into the Heap.
4. Repeat until the Heap is empty.

```
       MIN-HEAP (Tracks smallest active node from each list)
             ┌───────┐
             │ Heap  │ ───► Smallest: 1 (from List A)
             └───────┘
            /    |    \
        [HeadA] [HeadB] [HeadC]
```

---

## 🔍 Dry Run Example

**Input:** `[[1,4,5], [1,3,4], [2,6]]`

1. **Initialize Heap:** Add head of each list $\rightarrow$ Heap has `{1(A), 1(B), 2(C)}`
2. **Step-by-Step Traversal:**

| Step | Polled Node | New Node Offered | Current Heap | Result List |
|---|---|---|---|---|
| **Start** | — | — | `{1(A), 1(B), 2(C)}` | `Dummy` |
| **1** | `1` (from A) | `4` (from A) | `{1(B), 2(C), 4(A)}` | `Dummy -> 1(A)` |
| **2** | `1` (from B) | `3` (from B) | `{2(C), 3(B), 4(A)}` | `Dummy -> 1(A) -> 1(B)` |
| **3** | `2` (from C) | `6` (from C) | `{3(B), 4(A), 6(C)}` | `Dummy -> 1(A) -> 1(B) -> 2(C)` |
| **4** | `3` (from B) | `4` (from B) | `{4(A), 4(B), 6(C)}` | `Dummy -> 1(A) -> 1(B) -> ... -> 3(B)` |
| **5** | `4` (from A) | `5` (from A) | `{4(B), 5(A), 6(C)}` | `... -> 3(B) -> 4(A)` |
| **6** | `4` (from B) | null | `{5(A), 6(C)}` | `... -> 4(A) -> 4(B)` |
| **7** | `5` (from A) | null | `{6(C)}` | `... -> 4(B) -> 5(A)` |
| **8** | `6` (from C) | null | `{}` | `... -> 5(A) -> 6(C) ✅` |

---

## 💻 Java Solution

```java
import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-heap ordered by node value.
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.val, b.val)
        );

        // Add the first node of every non-empty list.
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!minHeap.isEmpty()) {
            // Get the smallest currently available node.
            ListNode smallest = minHeap.poll();

            // Append it to the result list.
            tail.next = smallest;
            tail = tail.next;

            // The next node in the same list may be the next smallest node.
            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
    }
}
```

---

## 💡 Key Learnings

- **K-Way Merge:** This is the base algorithm for external sorting (sorting files larger than memory).
- **Priority Queue Custom Comparator:** `(a, b) -> Integer.compare(a.val, b.val)` is safe from integer subtraction overflow bugs.
- **Dummy Node Utility:** Using a dummy node avoids handling the edge cases of initializing the head of the output list.

---

## ❌ Mistakes I Made

1. **Mistake:** Offering `null` nodes to the PriorityQueue.  
   **Fix:** Always perform a `head != null` check before adding to the heap.
2. **Mistake:** Forgetting to advance the `tail` pointer inside the loop.  
   **Fix:** Make sure to do `tail = tail.next` after attaching the polled node.

---

## 🔗 Similar Problems

- [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) — Easy
- [264. Ugly Number II](https://leetcode.com/problems/ugly-number-ii/) — Medium (merging 3 sorted factor streams)
- [378. Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) — Medium

---

## ✍️ Notes

If $k$ is extremely large (e.g., $10^6$ lists), divide-and-conquer may be preferred over heaps to minimize memory footprints, since the recursion stack only takes $O(\log k)$ while the heap takes $O(k)$ memory.

---

**Status:** ✅ SOLVED | **Attempts:** 1 | **Best Approach:** Approach 4 — Min-Heap
