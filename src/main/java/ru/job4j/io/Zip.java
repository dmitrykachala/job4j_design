package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(Path base, List<File> sources, File target) {
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

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, IllegalArgumentException {
        if (args.length != 3) {
            for (var arg : args) {
                System.out.println(arg);
            }
            throw new IllegalArgumentException("Count of parameters are not enough."
                    + "Usage: java -jar pack.jar -d=FOLDER_FOR_ARCH "
                    + "-e=EXCLUDE_PATTERN -o=TARGET_FILE");
        }
        ArgsName arguments = ArgsName.of(args);
        String dir = arguments.get("d");
        String exclude = arguments.get("e");
        String targetFileName = arguments.get("o");
        if (!Files.exists(Paths.get(dir))) {
            throw new IOException("There is no such directory.");
        }
        List<File> sources = Search.search(Path.of(dir),
                        f -> !f.toFile().getName().endsWith(exclude))
                .stream().map(Path::toFile)
                .collect(Collectors.toList());
        packFiles(Path.of(dir), sources, new File(targetFileName));
    }
}
