package com.yx.lecode;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


class SlidingWindowTest {

    private static Semaphore s1 = new Semaphore(0, false);
    private static Semaphore s2 = new Semaphore(0, false);

    @Test
    void testTread1() {
        Thread ta = new Thread(() -> {
            System.out.println("我是a");
            s1.release(2);
        });
        Thread tb = new Thread(() -> {
            try {
                s1.acquire();
                System.out.println("我是b");
                s2.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread tc = new Thread(() -> {
            try {
                s1.acquire();
                System.out.println("我是c");
                s2.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread td = new Thread(() -> {
            try {
                s2.acquire(2);
                System.out.println("我是d");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        //启动线程
        ta.start();
        tb.start();
        tc.start();
        td.start();
    }

    private static CountDownLatch c1 = new CountDownLatch(1);
    private static CountDownLatch c2 = new CountDownLatch(2);

    @Test
    void testTread2() {
        Thread ta = new Thread(() -> {
            System.out.println("我是a");
            c1.countDown();
        });
        Thread tb = new Thread(() -> {
            try {
                c1.await();
                System.out.println("我是b");
                c2.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread tc = new Thread(() -> {
            try {
                c1.await();
                System.out.println("我是c");
                c2.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread td = new Thread(() -> {
            try {
                c2.await();
                System.out.println("我是d");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        //启动线程
        ta.start();
        tb.start();
        tc.start();
        td.start();
    }

    @Test
    void test1() {
        int res = lengthOfLongestSubstring2("abcabcbb");
        System.out.println(res);
    }

    /**
     * 计算字符串中最长无重复字符的子串的长度
     * 通过维护一个滑动窗口，使用数组last记录每个字符最后一次出现的位置，从而在遇到重复字符时，能够快速调整窗口起始位置
     *
     * @param s 输入的字符串
     * @return 最长无重复字符子串的长度
     */
    public int lengthOfLongestSubstring(String s) {
        // 记录字符上一次出现的位置，初始化为-1，表示还未出现
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        // n为字符串长度
        int n = s.length();

        // 初始化结果为0，即最长子串的长度
        int res = 0;
        // start变量记录滑动窗口的起始位置
        int start = 0;
        // 遍历字符串中的每个字符
        for (int i = 0; i < n; i++) {
            // 获取当前字符的ASCII码，用于索引数组last
            int index = s.charAt(i);
            // 更新窗口的起始位置，如果当前字符在之前出现过，则将起始位置移动到该字符上一次出现位置的下一个位置
            // 否则，保持当前起始位置
            start = Math.max(start, last[index] + 1);
            // 更新最长子串的长度
            res = Math.max(res, i - start + 1);
            // 更新当前字符的最后出现位置
            last[index] = i;
        }

        // 返回最长无重复字符子串的长度
        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        ArrayList<Character> arr = new ArrayList<>();
        // 记录下标
        int index = 0;
        // 记录最大字符串长度
        int max = 0;
        // 遍历字符串
        while (index < s.length()) {
            // 判断数组是否包含当前字符 不包含则添加到数组中 右移动下标 判断max
            if (!arr.contains(s.charAt(index))) {
                arr.add(s.charAt(index));
                index++;
                max = Math.max(max, arr.size());
            } else {
                // 否则删除第一个元素 直到没有重复元素继续执行上面一步
                arr.remove(0);
            }
        }
        return max;
    }


}
