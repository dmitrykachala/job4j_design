package ru.job4j.io.find;

import ru.job4j.io.SearchFiles;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class Find extends SimpleFileVisitor<Path> {

    private StringJoiner joiner = new StringJoiner(System.lineSeparator());

    public void find(ArgsParser arguments, File target) throws IOException {
        List<Path> sources;
        if (arguments.getType().equals("name")) {
            sources = new ArrayList<>(findByName(Paths.get(arguments.getDir()), p -> p.getFileName()
                    .toString().equals(arguments.getName())));
            for (var source : sources) {
                joiner.add(source.toString());
            }
        }

         if (arguments.getType().equals("mask") || arguments.getType().equals("regex")) {
             joiner = findByMask(Paths.get(arguments.getDir()), arguments.getName());
         }

        try (PrintWriter outFile = new PrintWriter(new FileOutputStream(target))) {
            outFile.println(joiner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Path> findByName(Path dir, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(dir, searcher);
        return searcher.getPaths();
    }

    private StringJoiner findByMask(Path dir, String name) throws IOException {
        FindByPattern searcher = new FindByPattern(name);
        Files.walkFileTree(dir, searcher);
        return searcher.getJoiner();
    }

    public static void main(String[] args) throws IOException, IllegalArgumentException {
        Find find = new Find();
        ArgsParser arguments = new ArgsParser();
        arguments.parse(args);
        find.find(arguments, new File(arguments.getTargetFileName()));
    }
}
