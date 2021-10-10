package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        StringJoiner line = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            String str = "";
            boolean status = false;
            for (var ln : read.lines().collect(Collectors.toList())) {
                String[] substr = ln.split(" ");
                if (substr[0].equals("400") || substr[0].equals("500")) {
                    if (!status) {
                        status = true;
                        str = substr[1] + ";";
                    }
                    continue;
                }
                if (status) {
                    str = str + substr[1] + ";";
                    line.add(str);
                    try (PrintWriter outFile = new PrintWriter(new FileOutputStream(target))) {
                        outFile.println(line);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                status = false;
                str = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
            Analizy test = new Analizy();
            test.unavailable("data/server.log", "data/target.txt");
            test.unavailable("data/server1.log", "data/target1.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
