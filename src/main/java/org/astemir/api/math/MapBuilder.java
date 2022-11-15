package org.astemir.api.math;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K,V>{

    private Map<K,V> map = new HashMap<>();

    public MapBuilder<K,V> put(K key, V value){
        this.map.put(key,value);
        return this;
    }

    public V get(K key){
        return map.get(key);
    }

    public Map<K, V> build() {
        return map;
    }
}
