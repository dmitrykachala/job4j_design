package ru.job4j.io;

import ru.job4j.collection.List;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        ArrayList<String> rsl = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String question = "Что скажете?";
        String answer = "";
        boolean outFlag = false;
        boolean stopFlag = false;
        System.out.println(question);
        rsl.add(question);
        String ask = sc.nextLine();
        if (ask.equals(OUT)) {
            outFlag = true;
            rsl.add(ask);
        }
        if (ask.equals(STOP)) {
            stopFlag = true;
        }
        while (!outFlag) {
            if (!stopFlag) {
                 try {
                     answer = readPhrases().get((int) (Math.random()
                             * readPhrases().size()));
                     System.out.println(answer);
                 } catch (FileNotFoundException e) {
                     e.printStackTrace();
                 }
            } else {
                answer = "Отвечать не буду, пока не напечатаете \"продолжить\".";
                System.out.println(answer);
            }
            rsl.add(answer);
            ask = sc.nextLine();
            rsl.add(ask);
            if (ask.equals(OUT)) {
                outFlag = true;
            } else if (ask.equals(STOP)) {
                stopFlag = true;
            } else if (ask.equals(CONTINUE)) {
                stopFlag = false;
            }
        }
        answer = "Bye-bye";
        System.out.println(answer);
        rsl.add(answer);
        saveLog(rsl);
    }

    private ArrayList<String> readPhrases() throws  FileNotFoundException {
        if (!Paths.get(this.botAnswers).toFile().exists()) {
            throw new FileNotFoundException();
        }
        ArrayList<String> rsl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.botAnswers))) {
            String readLine = br.readLine();
            while (readLine != null) {
                rsl.add(readLine);
                readLine = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(ArrayList<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(this.path,
                Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            for (var arg : args) {
                System.out.println(arg);
            }
            throw new IllegalArgumentException("Count of parameters are not enough."
                    + "Usage: java -jar pack.jar -d=FOLDER_FOR_ARCH "
                    + "-e=EXCLUDE_PATTERN -o=TARGET_FILE");
        }
        ArgsName arguments = ArgsName.of(args);
        String target = arguments.get("t");
        String source = arguments.get("s");
        if (!Files.exists(Paths.get(source))) {
            throw new IOException("There is no such directory.");
        }
        ConsoleChat cc = new ConsoleChat(target, source);
        cc.run();
    }
}
