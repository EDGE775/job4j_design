package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean available = true;
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                String[] keyValue = line.split(" ");
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