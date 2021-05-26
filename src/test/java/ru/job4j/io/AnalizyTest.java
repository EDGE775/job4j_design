package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailableTest1() throws IOException {
        String sourseFile = "./data/server_test_1.log";
        File targetFile = folder.newFile("target.txt");
        Analizy analizy = new Analizy();
        analizy.unavailable(sourseFile, targetFile.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(targetFile))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;11:02:02;"));
    }

    @Test
    public void unavailableTest2() throws IOException {
        String sourseFile = "./data/server_test_2.log";
        File targetFile = folder.newFile("target.txt");
        Analizy analizy = new Analizy();
        analizy.unavailable(sourseFile, targetFile.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(targetFile))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:58:01;10:59:01;11:01:02;11:02:02;"));
    }
}