package ru.job4j.mailclient;

import ru.job4j.mailclient.entity.User;
import ru.job4j.mailclient.repository.MailClient;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        MailClient mailClient = new MailClient();
        mailClient.add(new User("Дима"), new HashSet<String>(Set.of("dima.khlapov@mail.ru", "dimon@mail.ru")));
        mailClient.add(new User("Маша"), new HashSet<String>(Set.of("masha@mail.ru", "masula@mail.ru")));
        mailClient.add(new User("Димон"), new HashSet<String>(Set.of("dima.khlapov@mail.ru", "dstick@yandex.ru")));
        mailClient.add(new User("Мария"), new HashSet<String>(Set.of("masha@mail.ru", "maha.ru")));

        mailClient.printAllUsers();
    }
}
