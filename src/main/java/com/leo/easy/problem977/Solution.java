package com.leo.easy.problem977;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 * @author justZero
 * @see <a href="https://leetcode-cn.com/problems/squares-of-a-sorted-array/">问题详情</a>
 * @since 2019/2/11
 */
public class Solution {

    // 时间复杂度 O(NlogN)，空间复杂度 O(N)
    public int[] sortedSquares(int[] A) {
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            ans[i] = A[i] * A[i];
        }
        Arrays.sort(ans);
        return ans;
    }

    /**
     * 双指针解法：时间复杂度 O(N)，空间复杂度 O(N)
     * <p>
     * 因为数组 A 已经排好序了，所以可以说数组中的负数已经按照平方值降序排好了，数组中的非负数已经按照平方值升序排好了。
     * 我们的策略就是从前向后遍历数组中的非负数部分，并且反向遍历数组中的负数部分。
     * <p>
     * 我们可以使用两个指针分别读取数组的非负部分与负数部分 —— 指针 i 反向读取负数部分，指针 j 正向读取非负数部分。
     * <p>
     * 注意：JDK API 能不用就不用，对性能影响较大
     */
    public int[] sortedSquaresFast(int[] A) {
        int N = A.length;

        int j = 0;
        while (j < N && A[j] < 0) j++;
        int i = j - 1; // 最后一个负数的数组下标

        int[] ans = new int[N];
        int t = 0;

        while (i >= 0 && j < N) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[t++] = A[i] * A[i];
                i--;
            } else {
                ans[t++] = A[j] * A[j];
                j++;
            }
        }

        // 负数和非负数不一定正好各一半，况且还有相等的情况。
        // 以下两个循环处理剩余的数
        while (i >= 0) {
            ans[t++] = A[i] * A[i];
            i--;
        }
        while (j < N) {
            ans[t++] = A[j] * A[j];
            j++;
        }

        return ans;
    }

    @Test
    public void test() {
        int[] arr = {-4, -1, 0, 3, 10};
        assertEquals("[0, 1, 9, 16, 100]",
                Arrays.toString(sortedSquares(arr)));

        int[] arr2 = {-7, -3, 2, 3, 11};
        assertEquals("[4, 9, 9, 49, 121]",
                Arrays.toString(sortedSquares(arr2)));

        assertEquals("[0, 1, 9, 16, 100]",
                Arrays.toString(sortedSquaresFast(arr)));
        assertEquals("[4, 9, 9, 49, 121]",
                Arrays.toString(sortedSquaresFast(arr2)));
    }
}
