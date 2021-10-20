package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();
    private int size = 0;

    public int getSize() {
        return size;
    }

    public String get(String key) {
        return values.get(key);
    }

    public Map<String, String> getValues() {
        return values;
    }

    private void parse(String[] args) throws IllegalArgumentException {
        if (args.length < 1) {
            throw new IllegalArgumentException();
        }
        for (var arg : args) {
            String[] argArray = arg.split("=");
            if (argArray.length != 2 || argArray[0].equals("")) {
                throw new IllegalArgumentException();
            }
                values.put(argArray[0].substring(1), argArray[1]);
                size++;
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
