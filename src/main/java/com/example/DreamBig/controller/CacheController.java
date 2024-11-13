package com.example.DreamBig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    private final CacheManager cacheManager;

    @Autowired
    public CacheController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @DeleteMapping("/cache/{cacheName}")
    public String clearCache(@PathVariable String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
            return "Кеш '" + cacheName + "' очищено.";
        } else {
            return "Кеш '" + cacheName + "' не знайдено.";
        }
    }

    @DeleteMapping("/cache/{cacheName}/{key}")
    public String evictCacheByKey(@PathVariable String cacheName, @PathVariable String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
            return "Кеш з ключем '" + key + "' у кеші '" + cacheName + "' очищено.";
        } else {
            return "Кеш '" + cacheName + "' не знайдено.";
        }
    }
}