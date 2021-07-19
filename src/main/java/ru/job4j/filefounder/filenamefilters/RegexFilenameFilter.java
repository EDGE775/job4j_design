package ru.job4j.filefounder.filenamefilters;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class RegexFilenameFilter implements FilenameFilter {
    private String regex;

    public RegexFilenameFilter(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean accept(File dir, String name) {
        return Pattern.matches(regex, name);
    }
}
