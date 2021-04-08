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
        T foundModel = findById(id);
        if (foundModel != null) {
            foundModel = model;
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        T foundModel = findById(id);
        if (foundModel != null) {
            mem.remove(foundModel);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        T foundModel = null;
        for (T element : mem) {
            if (element.getId().equals(id)) {
                foundModel = element;
                break;
            }
        }
        return foundModel;
    }
}