package ru.job4j.filefounder.filenamefilters;

import java.io.File;
import java.io.FilenameFilter;

public class StandardFilenameFilter implements FilenameFilter {
    private String fileName;

    public StandardFilenameFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.equals(fileName);
    }
}
