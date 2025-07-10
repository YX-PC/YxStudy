package com.yx.utils;

public class ThreadUtils {
    /**
     * 暂停当前线程指定的毫秒数
     * 此方法用于在程序执行过程中引入暂停，通常用于控制程序执行速度或等待特定条件
     *
     * @param millis 暂停的时间，单位为毫秒
     */
    public static void sleep(long millis) {
        try {
            // 使用Thread.sleep方法暂停当前线程
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // 当线程被中断时，捕获InterruptedException
            // 打印异常堆栈跟踪信息
            e.printStackTrace();
        }
    }
}
