package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test
    public void whenEmpty() {
        String path = "./data/withempty.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("q"), is("Q QQ"));
        assertThat(config.value("x"), is("X XX"));
        assertThat(config.value("a"), is("A AA"));
        assertThat(config.value("z"), is("Z ZZ"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotCorrect() {
        String path = "./data/notcorrect.properties";
        Config config = new Config(path);
        config.load();
        config.value("a");
    }
}