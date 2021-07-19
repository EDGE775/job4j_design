package ru.job4j.filefounder;

import java.util.HashMap;
import java.util.Map;

public class ArgsParser {

    private final Map<String, String> values = new HashMap<>();

    private ArgsParser() {
    }

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Invalid input args. Usage -PARAM=VALUE.");
        }
        for (String arg : args) {
            String[] argArray = arg.split("=");
            if (argArray.length != 2 || !argArray[0].startsWith("-")) {
                throw new IllegalArgumentException("Invalid input args. Usage -PARAM=VALUE.");
            }
            values.put(argArray[0].replaceFirst("-", ""), argArray[1]);
        }
    }

    public static ArgsParser of(String[] args) {
        ArgsParser names = new ArgsParser();
        names.parse(args);
        return names;
    }
}