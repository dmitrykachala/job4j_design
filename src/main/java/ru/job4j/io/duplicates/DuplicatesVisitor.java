package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Path> foundFilesProperty = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileP = new FileProperty(file.toFile().length(),
                file.getFileName().toString());
        if (!foundFilesProperty.containsKey(fileP)) {
            foundFilesProperty.put(fileP, file);
        } else {
            System.out.println(file.toAbsolutePath() + " = " + foundFilesProperty.get(fileP));
        }
        return super.visitFile(file, attrs);
    }
}
