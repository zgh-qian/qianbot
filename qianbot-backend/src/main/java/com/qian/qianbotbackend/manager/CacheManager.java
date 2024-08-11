package com.qian.qianbotbackend.manager;

import cn.hutool.crypto.digest.DigestUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class CacheManager {
    /**
     * 用户回答缓存
     */
    private final Cache<String, String> answerCache = Caffeine
            .newBuilder()
            .initialCapacity(1024)
            .expireAfterWrite(30L, TimeUnit.MINUTES)// 缓存 30 分钟
            .build();

    /**
     * 构建用户回答缓存key
     *
     * @param appId   应用id
     * @param choices 用户选项
     * @return key
     */
    public String buildAnswerCacheKey(Long appId, List<String> choices) {
        StringBuilder sb = new StringBuilder();
        sb.append(appId).append(":");
        choices.forEach(sb::append);
        return DigestUtil.md5Hex(sb.toString());
    }

    /**
     * 获取用户回答缓存
     *
     * @param key 用户回答缓存key
     * @return 用户回答缓存
     */
    public String getCache(String key) {
        String cache = answerCache.getIfPresent(key);
        if (cache == null) {
            return null;
        }
        if (cache.startsWith("id:")) {
            return cache.substring(3);
        } else if (cache.startsWith("json:")) {
            return cache.substring(5);
        } else {
            return cache;
        }
    }

    /**
     * 设置用户回答缓存
     *
     * @param key   用户回答缓存key
     * @param value id或者json
     * @param isId  保存的是否id
     */
    public void putCache(String key, String value, boolean isId) {
        if (isId) {
            answerCache.put(key, "id:" + value);
        } else {
            answerCache.put(key, "json:" + value);
        }
    }

    /**
     * 移除用户回答缓存
     */
    public void clearCache() {
        answerCache.invalidateAll();
    }
}
