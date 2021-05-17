package ru.job4j.mailclient.repository;

import ru.job4j.mailclient.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MailClient {
    private final Map<User, Set<String>> items = new HashMap<>();

    public boolean add(User user, Set<String> mailSet) {
        boolean result = false;
        User oldUser = getUserIfMailContainsOrNull(mailSet);
        if (oldUser != null) {
            result = items.get(oldUser).addAll(mailSet);
        } else if (items.containsKey(user)) {
            result = items.get(user).addAll(mailSet);
        } else {
            items.put(user, mailSet);
            result = true;
        }
        return result;
    }

    public Set<String> remove(User user) {
        return items.remove(user);
    }

    private User getUserIfMailContainsOrNull(Set<String> mailSet) {
        for (Map.Entry<User, Set<String>> userSetEntry : items.entrySet()) {
            Set<String> userMails = userSetEntry.getValue();
            for (String mail : mailSet) {
                if (userMails.contains(mail)) {
                    return userSetEntry.getKey();
                }
            }
        }
        return null;
    }

    public void printAllUsers() {
        items.entrySet().forEach(System.out::println);
    }

    public Map<User, Set<String>> getItems() {
        return items;
    }
}
