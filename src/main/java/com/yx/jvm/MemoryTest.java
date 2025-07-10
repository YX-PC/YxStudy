package com.yx.jvm;

import com.yx.utils.ThreadUtils;

public class MemoryTest {
    public volatile static boolean flag = false;

    public static void main(String[] args) {
        new Thread(() -> {
            while (!flag) {
            }
            System.out.println("你能看见我吗？");
        }).start();
        ThreadUtils.sleep(1000L);
        System.out.println("我出来了");
        flag = true;
        System.out.println(flag);
    }
}
