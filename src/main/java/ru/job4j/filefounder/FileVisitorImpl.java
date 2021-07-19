package ru.job4j.filefounder;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class FileVisitorImpl extends SimpleFileVisitor<Path> {
    private FilenameFilter filenameFilter;
    private final List<File> foundedFiles = new LinkedList<>();

    public FileVisitorImpl(FilenameFilter filenameFilter) {
        this.filenameFilter = filenameFilter;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File normalizedFile = file.normalize().toFile();
        if (normalizedFile.isFile()) {
            if (filenameFilter.accept(normalizedFile.getParentFile(), normalizedFile.getName())) {
                foundedFiles.add(normalizedFile);
            }
        }
        return super.visitFile(file, attrs);
    }

    public List<File> getFiles() {
        return foundedFiles;
    }
}
