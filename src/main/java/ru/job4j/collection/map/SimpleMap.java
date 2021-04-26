package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Iterable<K> {
    private Object[] storage;
    private int size = 0;
    private int modCount = 0;
    private final float loadFactor = 0.75F;

    public SimpleMap(int capacity) {
        this.storage = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean insert(K key, V value) {
        if (storage.length * loadFactor == size) {
            growUp();
        }
        int newHash = hash(key);
        int index = getIndex(newHash);
        Node<K, V> newNode = new Node<>(newHash, key, value);
        return putInsert(index, newNode);
    }

    private boolean putInsert(int index, Node<K, V> newNode) {
        if (storage[index] == null) {
            storage[index] = newNode;
            size++;
            modCount++;
            return true;
        }
        return false;
    }

    public V get(K key) {
        int hash = hash(key);
        int index = getIndex(hash);
        Node<K, V> node = (Node<K, V>) storage[index];
        return node == null || !Objects.equals(key, node.getKey())
                ? null : node.getValue();
    }

    public boolean delete(K key) {
        int hash = hash(key);
        int index = getIndex(hash);
        if (storage[index] == null || !((Node<K, V>) storage[index]).getKey().equals(key)) {
            return false;
        }
        storage[index] = null;
        size--;
        modCount++;
        return true;
    }

    private int hash(K key) {
        return key.hashCode() ^ (key.hashCode() >>> 16);
    }

    private int getIndex(int hash) {
        return hash & (storage.length - 1);
    }

    private void growUp() {
        int newSize = storage.length * 2;
        SimpleMap<K, V> newMap = new SimpleMap<>(newSize);
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                Node<K, V> node = (Node<K, V>) storage[i];
                newMap.insert(node.getKey(), node.getValue());
            }
        }
        storage = newMap.storage;
        modCount++;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = 0;
            private int expectedModCount;

            {
                expectedModCount = modCount;
            }

            @Override
            public boolean hasNext() {
                while (index < storage.length) {
                    if (storage[index] == null) {
                        index++;
                    } else {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (isModCountChanged()) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return ((Node<K, V>) storage[index++]).getKey();
            }

            private boolean isModCountChanged() {
                return expectedModCount != modCount;
            }
        };
    }

    private static class Node<K, V> {
        int hash;
        K key;
        V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        SimpleMap<String, String> map = new SimpleMap<>(16);
        map.insert("Петр Петрович", "123456");
        map.insert("Семён Семёныч", "241241");
        map.insert("Алексей Воробей", "251351");
        map.insert("Хлапов Дмитрий", "123453");
        map.insert("Николай Басков", "123453");
        map.insert("Гриша Селиванов", "123453");
        map.insert("Стив Джобс", "123453");
        map.insert("Билл Гейтс", "123453");
        map.insert("Владимир Путин", "123453");
        map.insert("Бениссио Дель Торро", "123453");
        map.insert("Бред Питт", "123453");
        map.insert("Джек Воробей", "123453");
        map.insert("Халк Хоган", "123453");

        System.out.println(map.size());
        System.out.println(map.get("Дима"));
        for (String s : map) {
            System.out.println(s);
        }
    }
}
