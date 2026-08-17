# 912. Merge Sort Using Divide and Conquer

**Difficulty:** Medium 🟡  
**LeetCode:** [Link](https://leetcode.com/problems/sort-an-array/)  
**Category:** Divide and Conquer / Sorting  
**Date Solved:** Aug 17, 2026

---

## 📝 Concept Overview

Merge Sort is a stable, comparison-based sorting algorithm built directly on the divide-and-conquer paradigm. It divides an array into smaller ranges until every range contains at most one element, recursively treats those ranges as sorted, and then combines them by merging two sorted ranges at a time.

The central idea is:
1. **Divide** $\rightarrow$ split the range around the midpoint
2. **Conquer** $\rightarrow$ recursively sort the left and right halves
3. **Combine** $\rightarrow$ merge the two sorted halves into one sorted range

---

## 1. Midpoint Index Selection

The midpoint is calculated as:
```java
int mid = left + (right - left) / 2;
```
This is a **midpoint index**. It divides the index interval approximately in half. It does **not** calculate the mathematical median of the values.

For the range with `left = 0` and `right = 5`:
$$\text{mid} = 0 + \frac{5 - 0}{2} = 2$$
Therefore, the array is divided into:
* **Left half:** indices 0 through 2 $\rightarrow$ `[8, 5, 9]`
* **Right half:** indices 3 through 5 $\rightarrow$ `[1, 6, 7]`

> [!TIP]
> A safe midpoint formula `left + (right - left) / 2` is preferred over `(left + right) / 2` because `left + right` can overflow for very large integer indices.

---

## 2. Complete Recursive Division

Starting with `[8, 5, 9, 1, 6, 7]`, Merge Sort repeatedly divides the current index range.

```
       [8, 5, 9, 1, 6, 7]
           /        \
       [8, 5, 9]   [1, 6, 7]
        /     \     /     \
     [8, 5]   [9] [1, 6]  [7]
     /    \       /    \
   [8]    [5]   [1]    [6]
```

The recursion stops when `left >= right`. A range with zero or one element is already sorted by definition, so no more division is necessary.

---

## 3. Recursive Function Structure

The standard top-down recursive skeleton is:
```java
int mid = left + (right - left) / 2;
mergeSort(array, left, mid);
mergeSort(array, mid + 1, right);
merge(array, left, mid, right);
```

The two recursive calls must finish before `merge` is called. This ordering is essential because `merge` assumes that both of its input ranges are already sorted.

For the initial split, the call sequence is conceptually:
1. `mergeSort(0, 5)`
2. `mergeSort(0, 2)`
3. `mergeSort(0, 1)`
4. `mergeSort(0, 0)` $\rightarrow$ returns (base case)
5. `mergeSort(1, 1)` $\rightarrow$ returns (base case)
6. `merge(0, 0, 1)`
7. `mergeSort(2, 2)` $\rightarrow$ returns (base case)
8. `merge(0, 1, 2)`
9. `mergeSort(3, 5)` ...
10. `merge(0, 2, 5)`

---

## 4. The Merge Operation: Parallel Comparison

Suppose the recursive calls have produced these two sorted ranges:
* **Left range:** `[5, 8, 9]` (from index `left` to `mid`)
* **Right range:** `[1, 6, 7]` (from index `mid + 1` to `right`)

We use three pointers:
* `leftPointer` $\rightarrow$ current scanning index in the left range
* `rightPointer` $\rightarrow$ current scanning index in the right range
* `writePointer` $\rightarrow$ next writing position in the temporary array

Initially:
* `leftPointer = 0` (value = `5`)
* `rightPointer = 0` (value = `1`)
* `result = [_, _, _, _, _, _]`

### Step-by-Step Merge Progression:

| Left vs Right | Smaller Selected | Temporary Array | Pointer Advanced |
|:---:|:---:|:---:|:---:|
| **5** vs **1** | `1` | `[1, _, _, _, _, _]` | `rightPointer` |
| **5** vs **6** | `5` | `[1, 5, _, _, _, _]` | `leftPointer` |
| **8** vs **6** | `6` | `[1, 5, 6, _, _, _]` | `rightPointer` |
| **8** vs **7** | `7` | `[1, 5, 6, 7, _, _]` | `rightPointer` |
| **8** vs *exhausted* | `8` | `[1, 5, 6, 7, 8, _]` | `leftPointer` |
| **9** vs *exhausted* | `9` | `[1, 5, 6, 7, 8, 9]` | `leftPointer` |

> [!NOTE]
> We only compare the front, unprocessed elements of each sorted half. We do not compare every left element with every right element. Each element is examined and copied at most once during that merge.

---

## 5. Leftover Copying

During merging, one range may become exhausted before the other. For example:
* Left range remaining: `[8, 9]`
* Right range remaining: `[]`

Because the left range was sorted prior to merging, the remaining values `[8, 9]` are already in the correct order. Since the right range is exhausted, there is no value left that could be smaller than `8` or `9`. Therefore, we copy them directly.

```java
while (leftPointer <= mid) {
    temporary[writePointer++] = nums[leftPointer++];
}
while (rightPointer <= right) {
    temporary[writePointer++] = nums[rightPointer++];
}
```

---

## 6. Copying Back to the Original Array

The temporary array is not the final destination. After the two halves have been merged, the sorted values must be copied back into the original array positions from `left` through `right`.

```java
for (int index = left; index <= right; index++) {
    nums[index] = temporary[index];
}
```

This step updates the input array in place, making the sorted result available to parent recursive calls.

---

## 7. Bottom-Up View of the Process

1. **Base ranges:** `[8]  [5]  [9]  [1]  [6]  [7]`
2. **Merge 1-element ranges:** `[5, 8]  [9]  [1, 6]  [7]`
3. **Merge larger ranges:** `[5, 8, 9]  [1, 6, 7]`
4. **Final merge:** `[1, 5, 6, 7, 8, 9]`

---

## 8. Proof of Correctness

1. **Base Case:** A range with zero or one element is sorted by definition.
2. **Merge Invariant:** Assume the left and right ranges are sorted. At the start of any merge loop, `leftPointer` and `rightPointer` identify the smallest unprocessed values in their respective halves. The smaller of the two must be the smallest unprocessed value overall.
3. **Inductive Conclusion:** Since the recursive calls sort both halves and the merge operation combines them correctly, the overall array is sorted.

---

## 9. Complexity Analysis

Let $T(n)$ be the time required to sort $n$ elements. Each level divides the problem into two halves and performs a linear merge:

$$T(n) = 2T(n/2) + O(n)$$

The recursion tree has approximately $\log_2(n)$ levels. At every level, all $n$ elements are processed collectively during merging. Therefore:

$$\text{Time Complexity} = O(n \log n)$$

Unlike Quick Sort, Merge Sort maintains the same time complexity for already sorted, reverse-sorted, random, and duplicate-heavy inputs.

| Case | Time Complexity | Reason |
|---|---|---|
| **Best Case** | $O(n \log n)$ | Array is still divided into $\log n$ levels and merged. |
| **Average Case** | $O(n \log n)$ | Every level processes all elements. |
| **Worst Case** | $O(n \log n)$ | Midpoint split remains balanced regardless of input order. |

* **Space Complexity:** $O(n)$ auxiliary space for the temporary storage array + $O(\log n)$ recursion stack space.

---

## 10. Stability

Merge Sort is **stable** because when equal values are encountered, we prioritize copying from the left range first:

```java
if (nums[leftPointer] <= nums[rightPointer]) { // '<=' preserves relative order
    temporary[writePointer++] = nums[leftPointer++];
}
```

Stability is useful when sorting objects by one key while preserving their previous order by another key.

---

## 11. Code Template (Java)

```java
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int[] temporary = new int[nums.length];
        mergeSort(nums, temporary, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int[] temporary, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        
        mergeSort(nums, temporary, left, mid);
        mergeSort(nums, temporary, mid + 1, right);
        
        // Optional optimization: skip merging if already sorted
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }
        merge(nums, temporary, left, mid, right);
    }

    private void merge(int[] nums, int[] temporary, int left, int mid, int right) {
        int leftPointer = left;
        int rightPointer = mid + 1;
        int writePointer = left;

        while (leftPointer <= mid && rightPointer <= right) {
            if (nums[leftPointer] <= nums[rightPointer]) {
                temporary[writePointer++] = nums[leftPointer++];
            } else {
                temporary[writePointer++] = nums[rightPointer++];
            }
        }

        while (leftPointer <= mid) {
            temporary[writePointer++] = nums[leftPointer++];
        }
        while (rightPointer <= right) {
            temporary[writePointer++] = nums[rightPointer++];
        }

        for (int index = left; index <= right; index++) {
            nums[index] = temporary[index];
        }
    }
}
```

---

## 12. Line-by-Line Algorithm Map

| Code Section | Algorithmic Role |
|---|---|
| `if (left >= right)` | Base case: zero- or one-element range is already sorted. |
| `mid = left + (right - left) / 2` | Divides the current range into two nearly equal sub-ranges. |
| First `mergeSort` call | Recursively sorts the left half. |
| Second `mergeSort` call | Recursively sorts the right half. |
| `leftPointer = left` | Starts scanning at the first value of the left sorted half. |
| `rightPointer = mid + 1` | Starts scanning at the first value of the right sorted half. |
| Comparison loop | Repeatedly selects the smaller front value. |
| Leftover loops | Copies the already-sorted remainder of an exhausted half. |
| Copy-back loop | Writes the combined sorted results back to the original array. |

---

## 13. Merge Sort vs Quick Sort

| Property | Merge Sort | Quick Sort |
|---|---|---|
| **How it divides** | Fixed midpoint | Pivot-based partition |
| **When ordering happens** | Mainly during merging | Mainly during partitioning |
| **Combination step** | Required and explicit | No separate merge is required |
| **Worst-case time** | $O(n \log n)$ | $O(n^2)$ with poor pivot selections |
| **Auxiliary space** | $O(n)$ | Usually $O(\log n)$ call stack space |
| **Stability** | Stable | Usually unstable |
| **Strength** | Predictable performance | In-place sorting, excellent cache locality |

---

## 14. Common Mistakes

1. **Using `mid` as the right boundary of the second half:**
   * ❌ `mergeSort(nums, temporary, mid, right)`
   * ✅ `mergeSort(nums, temporary, mid + 1, right)`
2. **Merging before recursive sorting finishes:**
   * You must sort both halves *before* trying to merge them.
3. **Forgetting leftover values:**
   * Make sure to copy remaining elements from whichever half was not fully traversed.
4. **Forgetting to copy back to source array:**
   * Remember to write contents from the temporary array back into `nums`.
5. **Using `<` instead of `<=` for comparisons:**
   * Standard comparison must be `<=` to maintain sorting stability.

---

## 🎯 Remember

> **Merge Sort = Divide at midpoint + Recursively sort both halves + Compare two sorted fronts in parallel + Copy the smaller front into a temporary range + Copy the merged range back**

Once each half is sorted, only the two smallest unprocessed values can compete for the next position. This limits merge cost to linear time ($O(n)$), guaranteeing the $O(n \log n)$ overall runtime! 🚀

---

### References
* [Merge sort — Wikipedia](https://en.wikipedia.org/wiki/Merge_sort)
* [LeetCode — Sort an Array](https://leetcode.com/problems/sort-an-array/)
* [Divide-and-conquer algorithm — Wikipedia](https://en.wikipedia.org/wiki/Divide-and-conquer_algorithm)
