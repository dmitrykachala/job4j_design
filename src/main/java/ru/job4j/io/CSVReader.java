package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        validate(argsName);

        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        String[] filters = filter.split(",");

        StringJoiner joiner = fileScan(path, filters, delimiter);

        if (out.equals("stdout")) {
            stdout(joiner);
        } else {
        outToFile(joiner, out);
        }
    }

    private static void validate(ArgsName argsName) throws Exception {
        if (argsName.getSize() != 4) {
            throw new IllegalArgumentException("Count of parameters are not enough."
                    + "Usage: java -jar pack.jar -d=FOLDER_FOR_ARCH "
                    + "-e=EXCLUDE_PATTERN -o=TARGET_FILE");
        }
        String path = argsName.get("path");
        if (!Paths.get(path).toFile().exists()) {
            throw new FileNotFoundException("Such file is not found or path is incorrect.");
        }
    }

    private static StringJoiner fileScan(String path, String[] filters,
                                         String delimiter) throws Exception {
        ArrayList<Integer> fl = new ArrayList<>();
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        String str = "";
        Scanner scanner = new Scanner(new File(path));
        scanner.useDelimiter(System.lineSeparator());
        String[] columns = scanner.next().split(delimiter);
        for (String s : filters) {
            for (int j = 0; j < columns.length; j++) {
                if (s.equals(columns[j])) {
                    fl.add(j);
                    str = str + columns[j] + delimiter;
                    break;
                }
            }
        }
        joiner.add(str.substring(0, str.length() - 1));
        str = "";

        while (scanner.hasNext()) {
            String[] line = scanner.next().split(delimiter);
            for (var flEl : fl) {
                str = str + line[flEl] + delimiter;
            }
            joiner.add(str.substring(0, str.length() - 1));
            str = "";
        }
        scanner.close();
        return joiner;
    }

    private static void outToFile(StringJoiner joiner, String out) {
        try (PrintWriter outFile = new PrintWriter(new FileOutputStream(out))) {
            outFile.println(joiner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void stdout(StringJoiner joiner) {
        System.out.println(joiner);
    }

    public static void main(String[] args) throws Exception {
        ArgsName arguments = ArgsName.of(args);
        handle(arguments);
    }
}