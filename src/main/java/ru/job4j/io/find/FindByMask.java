package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.StringJoiner;

public class FindByMask extends SimpleFileVisitor<Path> {

    private PathMatcher matcher;
    private StringJoiner joiner = new StringJoiner(System.lineSeparator());

    public FindByMask(String name) {
        matcher = FileSystems.getDefault().getPathMatcher("glob:" + name);
    }

    public StringJoiner getJoiner() {
        return joiner;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path fileName = file.getFileName();
        if (fileName != null && matcher.matches(fileName)) {
            joiner.add(file.toAbsolutePath().toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
