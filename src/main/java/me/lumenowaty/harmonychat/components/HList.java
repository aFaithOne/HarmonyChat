package me.lumenowaty.harmonychat.components;

import me.lumenowaty.harmonychat.components.interfaces.Listable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class HList<T> implements Listable<T>, Serializable {

    protected List<T> list = new ArrayList<>();

    @Override
    public void add(T s) {
        this.list.add(s);
    }

    @Override
    public void remove(T s) {
        this.list.remove(s);
    }

    @Override
    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean contains(T s) {
        return list.contains(s);
    }

    @Override
    public Optional<T> getById(int id) {
        if (id >= this.list.size())
            return Optional.empty();
        return Optional.of(this.list.get(id));
    }

    @Override
    public List<T> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T p : list) {
            builder.append(p).append(", ");
        }

        return builder.toString();
    }
}
