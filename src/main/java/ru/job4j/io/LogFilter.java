package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] arrayLine = line.split(" ");
                if (arrayLine[arrayLine.length - 2].equals("404")
                        && !arrayLine[arrayLine.length - 1].equals("-")) {
                    result.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String s : log) {
            System.out.println(s);
        }
    }
}