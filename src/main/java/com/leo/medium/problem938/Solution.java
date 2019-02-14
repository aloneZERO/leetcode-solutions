package com.leo.medium.problem938;

import com.leo.common.TreeNode;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * 二叉搜索树的范围和
 * <p>
 * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
 * 即二叉搜索树中序遍历序列中 L 和 R（含）之间的所有结点的值的和。
 * <p>
 * 二叉搜索树保证具有唯一的值。
 *
 * @author justZero
 * @see <a href="https://leetcode-cn.com/problems/range-sum-of-bst/">问题详情</a>
 * @since 2019/2/14
 */
public class Solution {

    public int rangeSumBSTFast(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        } else if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        } else {
            return root.val + rangeSumBST(root.left, L, R)
                    + rangeSumBST(root.right, L, R);
        }
    }

    // 太慢
    public int rangeSumBST(TreeNode root, int L, int R) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode currentNode, tempNode;
        int ans = 0;

        // 中序遍历二叉搜索树
        currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if (!stack.isEmpty()) {
                tempNode = stack.pop();
                if (tempNode != null) {
                    if (tempNode.val >= L && tempNode.val <= R) {
                        ans += tempNode.val;
                    }
                    currentNode = tempNode.right;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        TreeNode root = TreeNode.buildByQueue(
                new Integer[]{10, 5, 15, 3, 7, null, 18});
        assertEquals(32, rangeSumBST(root, 7, 15));
        assertEquals(32, rangeSumBSTFast(root, 7, 15));

        root = TreeNode.buildByQueue(
                new Integer[]{10, 5, 15, 3, 7, 13, 18, 1, null, 6});
        assertEquals(23, rangeSumBST(root, 6, 10));
        assertEquals(23, rangeSumBSTFast(root, 6, 10));
    }
}
