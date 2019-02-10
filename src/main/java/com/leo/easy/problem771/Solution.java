package com.leo.easy.problem771;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。
 * S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * <p>
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 * @author justZero
 * @see <a href="https://leetcode-cn.com/problems/jewels-and-stones/">问题详情</a>
 * @since 2019/2/10
 */
public class Solution {
    public int numJewelsInStones(String J, String S) {
        if (J == null || J.length() == 0 ||
                S == null || S.length() == 0) {
            return 0;
        }

        int count = 0;
        char[] myStones = S.toCharArray();

        // OJ 慎用 foreach 循环，和普通循环性能差距较大；
        // 项目中为了代码的简练和可读性，建议使用 foreach 循环。
        for (int i = 0; i < myStones.length; i++) {
            if (J.contains(String.valueOf(myStones[i]))) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void test() {
        assertEquals(0, numJewelsInStones("", "abcdd"));
        assertEquals(3, numJewelsInStones("aA", "aAAbbbb"));
        assertEquals(0, numJewelsInStones("z", "ZZ"));
    }
}
