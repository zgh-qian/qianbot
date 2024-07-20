package com.qian.qianbotbackend.common;

/**
 * ThreadLocal 用于传递用户id
 */
public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setUserId(Long id) {
        threadLocal.set(id);
    }

    public static Long getUserId() {
        return threadLocal.get();
    }

    public static void removeUserId() {
        threadLocal.remove();
    }

}
