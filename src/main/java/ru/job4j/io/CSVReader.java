package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    private final ArgsName argsParser;
    private Path pathToFile;
    private String delimeter;
    private PrintStream out;
    private List<String> filters;

    public CSVReader(ArgsName argsParser) {
        this.argsParser = argsParser;
    }

    public CSVReader init() throws FileNotFoundException {
        if (argsParser.get("path") == null
                || argsParser.get("delimiter") == null
                || argsParser.get("out") == null
                || argsParser.get("filter") == null) {
            throw new IllegalArgumentException(
                    "Invalid input args. Use \"path\", \"delimiter\", \"out\", \"filter\" as input args keys.");
        }
        this.pathToFile = Paths.get(argsParser.get("path"));
        this.delimeter = argsParser.get("delimiter");
        String outFilePath = argsParser.get("out");
        if (!outFilePath.equals("stdout")) {
            PrintStream ps = new PrintStream(outFilePath);
            System.setOut(ps);
        }
        this.out = System.out;
        this.filters = Arrays.stream(argsParser.get("filter").split(","))
                .collect(Collectors.toList());
        return this;
    }

    public void parse() {
        try (Scanner scanner = new Scanner(pathToFile)
                .useDelimiter(System.lineSeparator())) {
            StringJoiner stringJoiner = new StringJoiner(";");
            List<Integer> indexes = new ArrayList<>();
            String[] columns = scanner.next().split(delimeter);
            for (int i = 0; i < columns.length; i++) {
                if (filters.contains(columns[i])) {
                    indexes.add(i);
                    stringJoiner.add(columns[i]);
                }
            }
            out.println(stringJoiner.toString());
            while (scanner.hasNext()) {
                stringJoiner = new StringJoiner(";");
                String[] tokens = scanner.next().split(delimeter);
                for (Integer index : indexes) {
                    stringJoiner.add(tokens[index]);
                }
                out.println(stringJoiner.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        CSVReader reader = new CSVReader(ArgsName.of(args));
        reader.init().parse();
    }
}
