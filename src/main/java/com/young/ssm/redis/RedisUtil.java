package com.young.ssm.redis;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

    @Component
    public class RedisUtil {

        private static final String CACHE_NAME = "perfect-ssm-cache:";
        // 过期时间
        private static final int EXPIRE_TIME = 3000;

        private RedisTemplate template;

        private RedisCache cache;

        public RedisUtil() {
            init();
        }

        public void init() {
            template = SpringUtil.getBean("redisTemplate");//RedisCacheConfig中定义了
            cache = new RedisCache(CACHE_NAME, CACHE_NAME.getBytes(), template, EXPIRE_TIME);
        }

        //添加
        public void put(String key, Object obj) {
            cache.put(key, obj);
        }

        //获取
        public Object get(String key, Class clazz) {
            return cache.get(key) == null ? null : cache.get(key, clazz);
        }

        //删除
        public void del(String key) {
            cache.evict(key);
        }
    }

