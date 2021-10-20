package ru.job4j.io.find;

import ru.job4j.io.ArgsName;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArgsParser {

    private String dir;
    private String name;
    private String type;
    private String targetFileName;

    public void parse(String[] args) throws FileNotFoundException, IllegalArgumentException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Count of parameters are not enough."
                    + "Usage: java -jar find.jar -d=FOLDER_FOR_SEARCH "
                    + "-n=FILE_NAME OR MASK OR REGEX -t= TYPE_OF_SEARCH -o=TARGET_FILE");
        }
        ArgsName arguments = ArgsName.of(args);
        if (!arguments.getValues().containsKey("d") || !arguments.getValues().containsKey("n")
                || !arguments.getValues().containsKey("t")
                || !arguments.getValues().containsKey("o")) {
            throw new IllegalArgumentException("One or more parameters are wrong."
                    + "Usage: java -jar find.jar -d=FOLDER_FOR_SEARCH "
                    + "-n=FILE_NAME OR MASK OR REGEX -t= TYPE_OF_SEARCH -o=TARGET_FILE");
        }
        dir = arguments.get("d");
        name = arguments.get("n");
        type = arguments.get("t");
        targetFileName = arguments.get("o");
        if (!Files.exists(Paths.get(dir))) {
            throw new FileNotFoundException("There is no such directory.");
        }
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String exclude) {
        this.name = exclude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTargetFileName() {
        return targetFileName;
    }

    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }
}
