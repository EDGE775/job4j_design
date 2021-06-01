package ru.job4j.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Класс представляет собой консольный чат с ботом
 */
public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private boolean flag = true;
    private final Scanner in;

    /**
     * Конструктор чат-бота
     *
     * @param path       имя файла, в который будет записан весь диалог между ботом и пользователем
     * @param botAnswers имя файла в котором находятся строки с ответами, которые будет использовать бот
     * @param in         объект класса InputStream, определяющий как пользователь будет вводить запросы для бота
     */
    public ConsoleChat(String path, String botAnswers, InputStream in) {
        this.path = path;
        this.botAnswers = botAnswers;
        this.in = new Scanner(in, StandardCharsets.UTF_8);
    }

    /**
     * Метод для запуска чат бота
     */
    public void run() {
        String question = in.nextLine();
        writeLogFile(question);
        if (question.toLowerCase().equals(CONTINUE)) {
            flag = true;
        } else if (question.toLowerCase().equals(STOP)) {
            flag = false;
        } else if (question.toLowerCase().equals(OUT)) {
            return;
        } else if (flag) {
            String botAnswer = getBotAnswer();
            System.out.println(botAnswer);
            writeLogFile(botAnswer);
        }
        run();
    }

    private String getBotAnswer() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(botAnswers))) {
            bufferedReader.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        return answers.get(random.nextInt(answers.size()));
    }

    private void writeLogFile(String text) {
        try (PrintWriter printWriter = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            printWriter.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/main/resources/chat_log.txt",
                "./src/main/resources/bot_answers.txt",
                System.in);
        cc.run();
    }
}
