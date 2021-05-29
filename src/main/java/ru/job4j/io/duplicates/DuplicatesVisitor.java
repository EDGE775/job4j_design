package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<String>> files = new LinkedHashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File fileObject = file.normalize().toFile();
        if (fileObject.isFile()) {
            FileProperty currentFile = new FileProperty(fileObject.length(), fileObject.getName());
            if (!files.containsKey(currentFile)) {
                files.put(currentFile, new ArrayList<String>(List.of(fileObject.getAbsolutePath())));
            } else {
                files.get(currentFile).add(fileObject.getAbsolutePath());
            }
        }
        return super.visitFile(file, attrs);
    }

    public List<String> getFiles() {
        return files.values().stream()
                .filter(x -> x.size() > 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}