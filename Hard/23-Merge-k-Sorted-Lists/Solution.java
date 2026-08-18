import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * Problem: LeetCode 23 - Merge k Sorted Lists
 * LeetCode Link: https://leetcode.com/problems/merge-k-sorted-lists/
 * Difficulty: Hard
 * Category: Heap / Divide and Conquer / Merge Sort
 *
 * Date Solved: Aug 18, 2026
 */
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
