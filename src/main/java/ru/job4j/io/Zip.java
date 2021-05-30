package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private final ArgsName zipArgs;

    public Zip(ArgsName zipArgs) {
        this.zipArgs = zipArgs;
    }

    public void init() throws IOException {
        if (zipArgs.get("d") == null || zipArgs.get("o") == null || zipArgs.get("e") == null) {
            throw new IllegalArgumentException("Invalid input args. Use \"d\", \"o\", \"e\" as input args keys.");
        }
        Path source = Paths.get(zipArgs.get("d"));
        Path target = Paths.get(zipArgs.get("o"));
        String excludeFiles = zipArgs.get("e");
        if (!source.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not directory %s", source.toFile().getAbsoluteFile()));
        }
        List<Path> filesForZip = filterAndGetFilesForZip(source, excludeFiles);
        packFiles(filesForZip, target);
    }

    private List<Path> filterAndGetFilesForZip(Path source, String excludeFiles) throws IOException {
        return Search.search(source, p -> !p.toFile().getName().endsWith(excludeFiles));
    }

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(in.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip(ArgsName.of(args));
        zip.init();
    }
}