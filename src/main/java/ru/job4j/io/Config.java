package ru.job4j.io;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            List<String> lnOnlyNeeded = read.lines()
                    .filter(ln -> !ln.startsWith("#") && !ln.equals(""))
                    .collect(Collectors.toList());
            for (var ln : lnOnlyNeeded) {
                    String[] keyAndValue = ln.split("=");
                        values.put(keyAndValue[0], keyAndValue[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) throws IllegalArgumentException {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
