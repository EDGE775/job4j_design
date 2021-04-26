package ru.job4j.collection.map;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.job4j.collection.list.List;
import ru.job4j.collection.list.SimpleLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenInsertAndGet() {
        SimpleMap<String, String> map = new SimpleMap<>(16);
        map.insert("Петр Петрович", "123456");
        map.insert("Семён Семёныч", "241241");
        map.insert("Алексей Воробей", "251351");
        assertThat(map.get("Семён Семёныч"), is("241241"));
    }

    @Test
    public void whenInsertSimilarKeyThenFalse() {
        SimpleMap<String, String> map = new SimpleMap<>(16);
        map.insert("Петр Петрович", "123456");
        assertFalse(map.insert("Петр Петрович", "241241"));
    }

    @Test
    public void getIsNull() {
        SimpleMap<String, String> map = new SimpleMap<>(16);
        map.insert("Петр Петрович", "123456");
        map.insert("Семён Семёныч", "241241");
        map.insert("Алексей Воробей", "251351");
        assertThat(map.get("Алебард Алебардович"), is(nullValue()));
    }

    @Test
    public void whenDelete() {
        SimpleMap<String, String> map = new SimpleMap<>(16);
        map.insert("Петр Петрович", "123456");
        map.insert("Семён Семёныч", "241241");
        map.insert("Алексей Воробей", "251351");
        assertThat(map.get("Семён Семёныч"), is("241241"));
        map.delete("Семён Семёныч");
        assertThat(map.get("Семён Семёныч"), is(nullValue()));
    }

    @Test
    public void whenGetIterator() {
        SimpleMap<String, String> map = new SimpleMap<>(16);
        map.insert("Петр Петрович", "123456");
        map.insert("Семён Семёныч", "241241");

        Iterator<String> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(notNullValue()));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(notNullValue()));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenGetFromConcurrentModificationExceptionThrown() {
        SimpleMap<String, String> map = new SimpleMap<>(16);
        map.insert("Петр Петрович", "123456");
        map.insert("Семён Семёныч", "241241");
        map.insert("Алексей Воробей", "251351");
        Iterator<String> iterator = map.iterator();
        iterator.next();
        map.insert("Василий Пупкин", "242352");
        iterator.next();
    }
}