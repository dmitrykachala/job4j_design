package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArgsParse {

    private String dir;
    private String exclude;
    private String targetFileName;

    public void parse(String[] args) throws IOException, IllegalArgumentException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Count of parameters are not enough."
                    + "Usage: java -jar pack.jar -d=FOLDER_FOR_ARCH "
                    + "-e=EXCLUDE_PATTERN -o=TARGET_FILE");
        }
        ArgsName arguments = ArgsName.of(args);
        dir = arguments.get("d");
        exclude = arguments.get("e");
        targetFileName = arguments.get("o");
        if (!Files.exists(Paths.get(dir))) {
            throw new IOException("There is no such directory.");
        }
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getTargetFileName() {
        return targetFileName;
    }

    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }
}
