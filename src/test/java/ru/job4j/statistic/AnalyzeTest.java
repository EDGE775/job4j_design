package ru.job4j.statistic;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    @Test
    public void when5PrevAnd6Curr() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "Дима"),
                new Analyze.User(2, "Саша"),
                new Analyze.User(3, "Петя"),
                new Analyze.User(4, "Катя"),
                new Analyze.User(5, "Зина"));
        List<Analyze.User> current = List.of(
                new Analyze.User(1, "Дима"),
                new Analyze.User(2, "Олег"),
                new Analyze.User(5, "Зина"),
                new Analyze.User(6, "Глаша"),
                new Analyze.User(7, "Наташа"),
                new Analyze.User(8, "Маша"));
        Analyze.Info result = new Analyze().diff(previous, current);
        Analyze.Info expectInfo = new Analyze.Info(3, 1, 2);
        assertThat(result, is(expectInfo));
    }

    @Test
    public void when2PrevAnd5Curr() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "Дима"),
                new Analyze.User(2, "Саша"));
        List<Analyze.User> current = List.of(
                new Analyze.User(3, "Зина"),
                new Analyze.User(4, "Глаша"),
                new Analyze.User(5, "Наташа"),
                new Analyze.User(6, "Маша"));
        Analyze.Info result = new Analyze().diff(previous, current);
        Analyze.Info expectInfo = new Analyze.Info(4, 0, 2);
        assertThat(result, is(expectInfo));
    }

    @Test
    public void whenAllChanged() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "Дима"),
                new Analyze.User(2, "Саша"),
                new Analyze.User(3, "Петя"),
                new Analyze.User(4, "Катя"),
                new Analyze.User(5, "Зина"));
        List<Analyze.User> current = List.of(
                new Analyze.User(1, "Дима1"),
                new Analyze.User(2, "Саша1"),
                new Analyze.User(3, "Петя1"),
                new Analyze.User(4, "Катя1"),
                new Analyze.User(5, "Зина1"));
        Analyze.Info result = new Analyze().diff(previous, current);
        Analyze.Info expectInfo = new Analyze.Info(0, 5, 0);
        assertThat(result, is(expectInfo));
    }
}