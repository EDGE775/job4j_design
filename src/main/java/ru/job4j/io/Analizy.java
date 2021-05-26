package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> log = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(log::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean available = true;
            for (String logItem : log) {
                String[] keyValue = logItem.split(" ");
                if (Integer.parseInt(keyValue[0]) > 300 && available) {
                    out.printf("%s;", keyValue[1]);
                    available = false;
                } else if (Integer.parseInt(keyValue[0]) < 400 && !available) {
                    out.printf("%s;%n", keyValue[1]);
                    available = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
    }
}