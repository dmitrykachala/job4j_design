package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindByPattern extends SimpleFileVisitor<Path> {

    private Pattern pattern;
    private Matcher matcher;
    private StringJoiner joiner = new StringJoiner(System.lineSeparator());

    public FindByPattern(String name) {
        pattern = Pattern.compile(name);
    }

    public StringJoiner getJoiner() {
        return joiner;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path fileName = file.getFileName();
        matcher = pattern.matcher(fileName.toString());
        if (matcher.find()) {
            joiner.add(file.toAbsolutePath().toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
