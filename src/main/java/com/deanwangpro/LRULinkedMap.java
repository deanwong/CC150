package com.deanwangpro;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRULinkedMap<K, V> {

    private int size;

    private LinkedHashMap<K, V> cache;

    public LRULinkedMap(int size) {
        this.size = size;

        cache = new LinkedHashMap<K, V>(16, 0, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                if (size + 1 == cache.size()) {
                    return true;
                } else {
                    return false;
                }
            }
        };

    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.get(key);
    }
}
