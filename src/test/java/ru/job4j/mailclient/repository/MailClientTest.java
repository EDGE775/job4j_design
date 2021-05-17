package ru.job4j.mailclient.repository;

import org.junit.Test;
import ru.job4j.mailclient.entity.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MailClientTest {

    @Test
    public void whenAddIdenticalKeyThenMerge() {
        MailClient mailClient = new MailClient();
        mailClient.add(new User("Дима"), new HashSet<>(Set.of("dima.khlapov@mail.ru", "dimon@mail.ru")));
        mailClient.add(new User("Дима"), new HashSet<>(Set.of("dstick@yandex.ru", "kpln@mail.ru")));
        Map<User, Set<String>> items = mailClient.getItems();
        Map<User, Set<String>> expected = Map.of(new User("Дима"), Set.of("dima.khlapov@mail.ru", "dimon@mail.ru", "dstick@yandex.ru", "kpln@mail.ru"));
        assertThat(items, is(expected));
    }

    @Test
    public void whenAddOtherKeyWithIdenticalMailThenMerge() {
        MailClient mailClient = new MailClient();
        mailClient.add(new User("Дима"), new HashSet<>(Set.of("dima.khlapov@mail.ru", "dimon@mail.ru")));
        mailClient.add(new User("Дмитрий"), new HashSet<>(Set.of("dstick@yandex.ru", "kpln@mail.ru", "dimon@mail.ru")));
        Map<User, Set<String>> items = mailClient.getItems();
        Map<User, Set<String>> expected = Map.of(new User("Дима"), Set.of("dima.khlapov@mail.ru", "dimon@mail.ru", "dstick@yandex.ru", "kpln@mail.ru"));
        assertThat(items, is(expected));
    }
}