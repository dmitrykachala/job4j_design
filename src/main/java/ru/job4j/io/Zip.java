package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(Path base, List<File> sources, File target) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(target))) {
            for (var file : sources) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(base.relativize(file.toPath()).toString());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[10 * 1024 * 1024];
                    int r;
                    while ((r = fis.read(buffer)) > 0) {
                        zout.write(buffer, 0, r);
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) throws IOException, IllegalArgumentException {
        Zip zip = new Zip();
        ArgsParse arguments = new ArgsParse();
        arguments.parse(args);
        List<File> sources = Search.search(Path.of(arguments.getDir()),
                        f -> !f.toFile().getName().endsWith(arguments.getExclude()))
                .stream().map(Path::toFile)
                .collect(Collectors.toList());
        zip.packFiles(Path.of(arguments.getDir()), sources,
                new File(arguments.getTargetFileName()));
    }
}
