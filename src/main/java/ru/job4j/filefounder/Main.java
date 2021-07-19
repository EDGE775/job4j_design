package ru.job4j.filefounder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Application application = new Application(ArgsParser.of(args));
        application.init();
    }
}
