package ru.job4j.statistic;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        int changedUsers;
        int addedUsers;
        int deletedUsers;
        Set<User> allUsers = Stream
                .concat(previous.stream(), current.stream())
                .collect(Collectors.toSet());
        int unicsNumbers = allUsers.stream().map(x -> x.id).collect(Collectors.toSet()).size();
        changedUsers = Math.abs(allUsers.size() - unicsNumbers);
        int deleted = 0;
        int added = 0;
        for (User user : allUsers) {
            if (previous.contains(user) && current.contains(user)) {
                continue;
            } else if (previous.contains(user)) {
                deleted++;
            } else {
                added++;
            }
        }
        addedUsers = added - changedUsers;
        deletedUsers = deleted - changedUsers;
        return new Info(addedUsers, changedUsers, deletedUsers);
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }
}
