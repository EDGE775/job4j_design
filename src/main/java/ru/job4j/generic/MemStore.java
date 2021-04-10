package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = findIndexById(id);
        if (isIndexPresent(index)) {
            mem.set(index, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int index = findIndexById(id);
        if (isIndexPresent(index)) {
            mem.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        int index = findIndexById(id);
        return isIndexPresent(index) ? mem.get(index) : null;
    }

    private int findIndexById(String id) {
        int index = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private boolean isIndexPresent(int index) {
        return index != -1;
    }

}