package com.leo.easy.problem104;

import java.util.*;

/**
 * @author justZero
 * @since 2019/2/9
 */
public class ThreeNode {
    Integer val;
    ThreeNode left;
    ThreeNode right;

    public ThreeNode(Integer v) {
        this.val = v;
    }

    public static ThreeNode buildByRecursive(Integer[] tree) {
        return build(tree, 0);
    }
    private static ThreeNode build(Integer[] tree, int n) {
        if (tree==null || tree.length==0) {
            return null;
        }
        if (n < tree.length) {
            int left = n*2 + 1;
            int right = n*2 + 2;
            ThreeNode root = new ThreeNode(tree[n]);
            root.left = build(tree, left);
            root.right = build(tree, right);
            return root;
        }
        return null;
    }

    public static ThreeNode buildByQueue(Integer[] tree) {
        if (tree==null || tree.length==0) {
            return null;
        }
        LinkedList<ThreeNode> queue = new LinkedList<>();
        ThreeNode root = new ThreeNode(tree[0]);
        queue.offer(root);

        int n = 1;
        while (!queue.isEmpty()) {
            ThreeNode current = queue.poll();
            if (n < tree.length) {
                current.left = new ThreeNode(tree[n]);
                queue.offer(current.left);
                n++;
            }
            if (n < tree.length) {
                current.right = new ThreeNode(tree[n]);
                queue.offer(current.right);
                n++;
            }
        }
        return root;

    }

    public static void print(ThreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<ThreeNode> printQueue = new LinkedList<>();
        ThreeNode current;
        int currentLvCount = 1, nextLvCount = 0;

        printQueue.offer(root); // 入队
        while (!printQueue.isEmpty()) {
            current = printQueue.poll(); // 出队
            if (current.val == null) {
                System.out.print("# ");
            } else {
                System.out.print(current.val + " ");
            }
            currentLvCount--;

            if (current.left != null) {
                printQueue.offer(current.left);
                nextLvCount++;
            }
            if (current.right != null) {
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
