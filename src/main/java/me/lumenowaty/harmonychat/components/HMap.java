package me.lumenowaty.harmonychat.components;

import me.lumenowaty.harmonychat.components.interfaces.Mapable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class HMap<T, K> implements Mapable<T, K>, Serializable {

    protected Map<T, K> map = new HashMap<>();

    @Override
    public void add(T t, K k) {
        this.map.put(t, k);
    }

    @Override
    public void remove(T t) {
        this.map.remove(t);
    }

    @Override
    public void put(T t, K k) {
        map.put(t, k);
    }

    @Override
    public void setMap(Map<T, K> map) {
        this.map = map;
    }

    @Override
    public boolean containsKey(T t) {
        return this.map.containsKey(t);
    }

    @Override
    public boolean containsValue(K k) {
        return this.map.containsValue(k);
    }

    @Override
    public Map<T, K> getMap() {
        return this.map;
    }

    @Override
    public Optional<K> getByKey(T t) {
        return Optional.ofNullable(map.get(t));
    }
}
