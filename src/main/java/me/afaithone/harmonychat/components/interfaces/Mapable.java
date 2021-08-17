package me.afaithone.harmonychat.components.interfaces;

import java.util.Map;
import java.util.Optional;

public interface Mapable<T, K> {

    void add(T t, K k);

    void remove(T t);

    void put(T t, K k);

    void setMap(Map<T, K> map);

    boolean containsKey(T t);

    boolean containsValue(K k);

    Map<T, K> getMap();

    Optional<K> getByKey(T t);
}
