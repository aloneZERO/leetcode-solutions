package com.leo.medium.problem535;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Repeatable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * TinyURL是一种URL简化服务， 比如：当你输入一个URL
 * https://leetcode.com/problems/design-tinyurl 时，
 * 它将返回一个简化的URL http://tinyurl.com/4e9iAk.
 * <p>
 * 设计一个 TinyURL 的加密 encode 和解密 decode 的方法。
 * 你的加密和解密算法如何设计和运作是没有限制的，你只需要保证一个URL可以被加密成一个TinyURL，
 * 并且这个TinyURL可以用解密方法恢复成原本的URL。
 *
 * @author justZero
 * @see <a href="https://leetcode-cn.com/problems/encode-and-decode-tinyurl/">问题详情</a>
 * @since 2019/2/10
 */
public class TinyURL {

    private static final Random RANDOM = new Random();

    private static final char[] ALPHABETS = ("ABCDEFGHIJKLMNOPQRSTUVWXYX" +
            "abcdefghijklmnopqrstuvwxyz0123456789").toCharArray();

    // 原链接到短链接、短链接到原链接的双向映射存储
    private static final Map<String, String> URL_MAP = new HashMap<>();

    private static final String TINY_URL_PREFIX = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public static String encode(String longUrl) {
        String shortUrl = URL_MAP.get(longUrl);
        if (shortUrl == null) {
            shortUrl = TINY_URL_PREFIX + genRandomStr(6);
            URL_MAP.put(longUrl, shortUrl);
            URL_MAP.put(shortUrl, longUrl);
        }
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public static String decode(String shortUrl) {
        return URL_MAP.getOrDefault(shortUrl, shortUrl);
    }

    private static String genRandomStr(int len) {
        int n = ALPHABETS.length;
        StringBuilder result = new StringBuilder();
        for (int i=0; i< len; i++) {
            int index = RANDOM.nextInt(n); // 随机整型数，范围 [0, n)
            result.append(ALPHABETS[index]);
        }
        return result.toString();
    }

    @Test
    public void test() {
        String testUrl = "https://leetcode.com/problems/design-tinyurl";
        String tinyUrl = TinyURL.encode(testUrl);
        System.out.println(tinyUrl);
        Assert.assertEquals(testUrl, TinyURL.decode(tinyUrl));
    }

}
