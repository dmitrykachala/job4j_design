package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void  checkParseAndEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void  checkValidateAndWithoutEquality() {
        NameLoad nameLoad = new NameLoad();
        String names = "sdsss";
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names)
                .hasMessageContaining("this name: " + names + " does not contain the symbol \"=\"");
    }

    @Test
    void  checkValidateAndWithoutKey() {
        NameLoad nameLoad = new NameLoad();
        String names = "=sdsss";
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names)
                .hasMessageContaining("this name: " + names + " does not contain a key");
    }

    @Test
    void  checkValidateAndWithoutValue() {
        NameLoad nameLoad = new NameLoad();
        String names = "sdsss=";
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names)
                .hasMessageContaining("this name: " + names + " does not contain a value");
    }

    @Test
    void  checkGetMapAndEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("collection contains no data");
    }
}