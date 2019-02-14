package com.leo.easy.problem104;

import com.leo.common.TreeNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：给定二叉树 [3,9,20,null,null,15,7] 层次遍历序列
 * <pre>
 *      3
 *     / \
 *    9  20
 *      / \
 *     15  7</pre>
 * 返回它的最大深度 3
 *
 * @author justZero
 * @see <a href="https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/">问题详情</a>
 * @since 2019/2/9
 */
public class Solution {

    private int maxDepth(TreeNode root) {
        return root == null ? 0
                : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    @Test
    public void test() {
        Integer[] treeData = {3, 9, 20, null, null, 15, 7};
        TreeNode testTree = TreeNode.buildByQueue(treeData);
        TreeNode.print(testTree);
        assertEquals(3, maxDepth(testTree));
    }
}
