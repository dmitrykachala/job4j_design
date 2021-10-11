package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unav1() throws IOException {
        File target = folder.newFile("target1.txt");
        Analizy test = new Analizy();
        test.unavailable("data/server1.log", target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;11:02:02;"));
    }

    @Test
    public void unav2() throws IOException {
        File target = folder.newFile("target.txt");
        Analizy test = new Analizy();
        test.unavailable("data/server.log", target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01;11:01:02;11:02:02;"));
    }
}