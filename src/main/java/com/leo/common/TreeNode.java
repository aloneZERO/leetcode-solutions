package com.leo.common;

import java.util.*;

/**
 * 二叉树（仅 LeetCode 适用）
 *
 * @author justZero
 * @since 2019/2/9
 */
public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(Integer v) {
        this.val = v;
    }

    /**
     * 根据层次序列构建二叉树（递归实现）
     *
     * @param tree 树结点的数值数组
     * @return 二叉树根结点
     */
    public static TreeNode buildByRecursion(Integer[] tree) {
        return build(tree, 0);
    }
    private static TreeNode build(Integer[] tree, int n) {
        if (tree==null || tree.length==0) {
            return null;
        }
        if (n < tree.length) {
            int left = n*2 + 1;
            int right = n*2 + 2;
            if (tree[n] != null) {
                TreeNode root = new TreeNode(tree[n]);
                root.left = build(tree, left);
                root.right = build(tree, right);
                return root;
            }
        }
        return null;
    }

    /**
     * 根据层次序列构建二叉树（非递归实现，借助队列）
     *
     * @param tree 树结点的数值数组
     * @return 二叉树根结点
     */
    public static TreeNode buildByQueue(Integer[] tree) {
        if (tree==null || tree.length==0) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(tree[0]);
        queue.offer(root); // 入队

        int n = 1;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll(); // 出队
            if (n < tree.length) {
                current.left = (tree[n]!=null ? new TreeNode(tree[n]) : null);
                queue.offer(current.left);
                n++;
            }
            if (n < tree.length) {
                current.right = (tree[n]!=null ? new TreeNode(tree[n]) : null);
                queue.offer(current.right);
                n++;
            }
        }
        return root;
    }

    public static void print(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> printQueue = new LinkedList<>();
        TreeNode current;
        int currentLvCount = 1, nextLvCount = 0;

        printQueue.offer(root); // 入队
        while (!printQueue.isEmpty()) {
            current = printQueue.poll(); // 出队
            if (current == null) {
                System.out.print("# ");
            } else {
                System.out.print(current.val + " ");
            }
            currentLvCount--;

            if (current!=null && current.left!=null) {
                printQueue.offer(current.left);
                nextLvCount++;
            }
            if (current!=null && current.right!=null) {
                printQueue.offer(current.right);
                nextLvCount++;
            }

            if (currentLvCount == 0) {
                System.out.println();
                currentLvCount = nextLvCount;
                nextLvCount = 0;
            }
        }

    }
}
