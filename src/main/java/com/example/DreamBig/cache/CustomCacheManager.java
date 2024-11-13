package com.example.DreamBig.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@Component
public class CustomCacheManager implements CacheManager {

    private final Map<String, CustomCache> cacheMap = new HashMap<>();
    private static final int MAX_CACHE_SIZE = 100;

    @Override
    public Cache getCache(String name) {
        return cacheMap.computeIfAbsent(name, key -> new CustomCache(name));
    }

    @Override
    public Collection<String> getCacheNames() {
        return cacheMap.keySet();
    }

    private class CustomCache implements Cache {

        private final String name;
        private final Map<Object, Object> store;

        public CustomCache(String name) {
            this.name = name;
            this.store = new HashMap<>();
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public Object getNativeCache() {
            return this.store;
        }

        @Override
        public ValueWrapper get(Object key) {
            Object value = store.get(key);
            return value != null ? new SimpleValueWrapper(value) : null;
        }

        @Override
        public <T> T get(Object key, Class<T> type) {
            Object value = store.get(key);
            return value != null ? type.cast(value) : null;
        }

        @Override
        public <T> T get(Object key, Callable<T> valueLoader) {
            Object value = store.get(key);
            if (value != null) {
                return (T) value;
            }
            try {
                T valueFromLoader = valueLoader.call();
                put(key, valueFromLoader);
                return valueFromLoader;
            } catch (Exception e) {
                throw new RuntimeException("Error loading value for key " + key, e);
            }
        }

        @Override
        public void put(Object key, Object value) {
            if (store.size() >= MAX_CACHE_SIZE) {
                Object firstKey = store.keySet().iterator().next();
                store.remove(firstKey);
            }
            store.put(key, value);
        }

        @Override
        public void evict(Object key) {
            store.remove(key);
        }

        @Override
        public void clear() {
            store.clear();
        }
    }


}
