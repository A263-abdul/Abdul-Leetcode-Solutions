#!/bin/bash
# =====================================================
# 🚀 Daily LeetCode Workflow Automation Script
# Author: Abdul Siddiqi (A263-abdul)
# Usage: bash daily-leetcode.sh
# =====================================================

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
PURPLE='\033[0;35m'
CYAN='\033[0;36m'
BOLD='\033[1m'
NC='\033[0m' # No Color

REPO_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

echo ""
echo -e "${PURPLE}${BOLD}╔══════════════════════════════════════════╗${NC}"
echo -e "${PURPLE}${BOLD}║   🔥 Abdul's Daily LeetCode Workflow 🔥   ║${NC}"
echo -e "${PURPLE}${BOLD}╚══════════════════════════════════════════╝${NC}"
echo ""

# ─── Collect Problem Info ────────────────────────────
echo -e "${CYAN}${BOLD}📋 Enter Problem Details${NC}"
echo -e "${YELLOW}────────────────────────────────────${NC}"

echo -e "${BLUE}Problem Number (e.g. 322):${NC} "
read -r PROBLEM_NUM

echo -e "${BLUE}Problem Name (with-hyphens, e.g. Coin-Change):${NC} "
read -r PROBLEM_NAME

echo -e "${BLUE}Difficulty (Easy/Medium/Hard):${NC} "
read -r DIFFICULTY

echo -e "${BLUE}Category (e.g. DP, Array, String, Tree):${NC} "
read -r CATEGORY

echo -e "${BLUE}LeetCode slug (e.g. coin-change):${NC} "
read -r SLUG

DATE=$(date "+%b %d, %Y")
FOLDER="${DIFFICULTY}/${PROBLEM_NUM}-${PROBLEM_NAME}"

echo ""
echo -e "${YELLOW}Creating: ${CYAN}${FOLDER}${NC}"
echo ""

# ─── Create Folder ───────────────────────────────────
mkdir -p "${REPO_DIR}/${FOLDER}"
cd "${REPO_DIR}/${FOLDER}" || exit 1

# ─── Create Solution.java ─────────────────────────────
cat > Solution.java << EOF
/**
 * Problem: ${PROBLEM_NAME//-/ }
 * LeetCode Link: https://leetcode.com/problems/${SLUG}/
 * Difficulty: ${DIFFICULTY}
 * Category: ${CATEGORY}
 *
 * Date Solved: ${DATE}
 * Time Taken: __ mins
 */

class Solution {

    // ========================================
    // APPROACH 1: [Brute Force / Naive]
    // Time: O() | Space: O()
    // Status: ❌ TLE / Suboptimal
    // ========================================

    public int approach1() {
        // TODO: implement approach 1
        return 0;
    }


    // ========================================
    // APPROACH 2: [Optimized]
    // Time: O() | Space: O()
    // Status: ✅ Accepted
    // ========================================

    public int approach2() {
        // TODO: implement approach 2
        return 0;
    }


    // ========================================
    // APPROACH 3: [Best / Most Efficient] ⭐
    // Time: O() | Space: O()
    // Status: ✅ Accepted (Most Efficient)
    // ========================================

    public int approach3() {
        // TODO: implement approach 3 — BEST
        return 0;
    }
}
EOF

# ─── Create README.md ─────────────────────────────────
PROBLEM_TITLE="${PROBLEM_NAME//-/ }"
cat > README.md << EOF
# ${PROBLEM_NUM}. ${PROBLEM_TITLE}

**Difficulty:** ${DIFFICULTY} 🟢🟡🔴
**LeetCode:** [Link](https://leetcode.com/problems/${SLUG}/)
**Category:** ${CATEGORY}
**Date Solved:** ${DATE}
**Time Spent:** __ mins

---

## 📝 Problem Statement

> Copy problem description from LeetCode here.

### Example 1:
\`\`\`
Input: 
Output: 
Explanation: 
\`\`\`

### Example 2:
\`\`\`
Input: 
Output: 
\`\`\`

### Constraints:
- constraint 1
- constraint 2

---

## 🎯 Approaches

### Approach 1: [NAME] ❌ (Brute Force)
**Time:** O() | **Space:** O() | **Status:** ❌ TLE

**Idea:** 

\`\`\`java
// code here
\`\`\`

**Why it fails:** 

---

### Approach 2: [NAME] ✅
**Time:** O() | **Space:** O() | **Status:** ✅ Accepted

**Idea:** 

\`\`\`java
// code here
\`\`\`

---

### Approach 3: [NAME] ⭐ BEST
**Time:** O() | **Space:** O() | **Status:** ✅ Accepted (Most Efficient)

**Idea:** 

\`\`\`java
// code here
\`\`\`

**Why it's best:** 

---

## 📊 Complexity Comparison

| Approach | Time | Space | Status |
|----------|------|-------|--------|
| Brute Force | O() | O() | ❌ TLE |
| Optimized | O() | O() | ✅ Accepted |
| Best | O() | O() | ✅ Optimal |

---

## 💡 Key Learnings

- Learning 1
- Learning 2

---

## ❌ Mistakes I Made

1. **Mistake:** ...  
   **Fix:** ...

---

## 🔗 Similar Problems

- [Problem Name](https://leetcode.com/problems/...)

---

**Status:** ✅ SOLVED | **Attempts:** 1 | **Best Approach:** Approach 3
EOF

echo -e "${GREEN}✅ Files created successfully!${NC}"
echo ""
echo -e "${CYAN}📁 Location: ${YELLOW}${FOLDER}/${NC}"
echo -e "${CYAN}   ├── ${GREEN}Solution.java${NC}"
echo -e "${CYAN}   └── ${GREEN}README.md${NC}"
echo ""

# ─── Git Status ──────────────────────────────────────
cd "${REPO_DIR}" || exit 1

echo -e "${YELLOW}${BOLD}📊 Git Status:${NC}"
git status --short
echo ""

# ─── Ask to Commit ───────────────────────────────────
echo -e "${BLUE}Commit message (leave blank to skip git):${NC}"
echo -e "  ${YELLOW}Example: Aug 15: Coin Change - Recursive, Memoization, Tabulation${NC}"
read -r COMMIT_MSG

if [ -n "$COMMIT_MSG" ]; then
    git add .
    git commit -m "$COMMIT_MSG"
    echo ""
    echo -e "${GREEN}✅ Committed!${NC}"

    echo -e "${BLUE}Push to GitHub? (y/n):${NC} "
    read -r PUSH
    if [ "$PUSH" = "y" ] || [ "$PUSH" = "Y" ]; then
        git push origin main
        echo -e "${GREEN}✅ Pushed to GitHub!${NC}"
    fi
fi

echo ""
echo -e "${PURPLE}${BOLD}╔══════════════════════════════════════════╗${NC}"
echo -e "${PURPLE}${BOLD}║       ✅ Daily Workflow Complete! 🎉       ║${NC}"
echo -e "${PURPLE}${BOLD}╚══════════════════════════════════════════╝${NC}"
echo ""
echo -e "${CYAN}📋 Don't forget your checklist:${NC}"
echo -e "  ${GREEN}✅ Solve LeetCode problem${NC}"
echo -e "  ☐  Code all approaches in Solution.java"
echo -e "  ☐  Create README.md with explanation"
echo -e "  ☐  Calculate Time & Space complexity"
echo -e "  ☐  Document learnings"
echo -e "  ☐  Update main README.md stats"
echo -e "  ${GREEN}✅ git add . && git commit && git push${NC}"
echo ""
