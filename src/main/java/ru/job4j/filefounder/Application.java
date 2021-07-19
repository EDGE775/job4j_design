package ru.job4j.filefounder;

import ru.job4j.filefounder.filenamefilters.MaskFilenameFilter;
import ru.job4j.filefounder.filenamefilters.RegexFilenameFilter;
import ru.job4j.filefounder.filenamefilters.StandardFilenameFilter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Application {
    private final ArgsParser argsParser;
    private Path startDirectory;
    private String nameForSearch;
    private FilenameFilter filenameFilter;
    private Path outFilePath;

    public Application(ArgsParser argsParser) {
        this.argsParser = argsParser;
    }

    public void init() throws IOException {
        config();
        FileVisitorImpl fileVisitor = new FileVisitorImpl(filenameFilter);
        Files.walkFileTree(startDirectory, fileVisitor);
        try (PrintWriter pw = new PrintWriter(new FileWriter(outFilePath.toFile(), false))) {
            List<File> files = fileVisitor.getFiles();
            for (File file : files) {
                pw.println(file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void config() {
        if (argsParser.get("d") == null
                || argsParser.get("n") == null
                || argsParser.get("t") == null
                || argsParser.get("o") == null) {
            throw new IllegalArgumentException(
                    "Invalid input args. Use \"d\", \"n\", \"t\", \"o\" as input args keys.");
        }
        this.startDirectory = Paths.get(argsParser.get("d"));
        this.nameForSearch = argsParser.get("n");
        this.filenameFilter = getFileNameFilter(argsParser.get("t"));
        this.outFilePath = Paths.get(argsParser.get("o"));
    }

    private FilenameFilter getFileNameFilter(String searchType) {
        if (searchType.equals("name")) {
            return new StandardFilenameFilter(nameForSearch);
        } else if (searchType.equals("mask")) {
            return new MaskFilenameFilter(nameForSearch);
        } else if (searchType.equals("regex")) {
            return new RegexFilenameFilter(nameForSearch);
        } else {
            throw new IllegalArgumentException(
                    "Invalid input search type for file search (\"t\" arg). You must use \"mask\", \"regex\" or \"name\"");
        }
    }
}
