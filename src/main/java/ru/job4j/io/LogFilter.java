package ru.job4j.io;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class LogFilter {
    public static List<String> filter(String file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines()
                    .filter(ln -> {
                        String[] substr = ln.split(" ");
                        return substr[substr.length - 2].equals("404"); })
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
