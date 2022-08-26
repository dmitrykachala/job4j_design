package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "first", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("three")
                .containsOnly("first", "three", "four", "five")
                .containsExactly("first", "first", "three", "four", "five")
                .containsAnyOf("six", "four")
                .doesNotContain("second")
                .containsSequence("four", "five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "three", "four", "five", "q");
        assertThat(set).isNotNull()
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(6);
                    assertThat(e.length()).isGreaterThan(0);
                })
                .anySatisfy(e -> {
                    assertThat(e.length()).isLessThan(2);
                    assertThat(e.length()).isEqualTo(1);
                })
                .allMatch(e -> e.length() < 6)
                .anyMatch(e -> e.length() == 4)
                .noneMatch(e -> e.length() < 1);
    }

    @Test
    void checkNavigationList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "first", "three", "four", "five");
        assertThat(list).first().isEqualTo("first");
        assertThat(list).element(2).isNotNull()
                .isEqualTo("three");
        assertThat(list).last().isNotNull()
                .isEqualTo("five");
    }

    @Test
    void checkFilteredList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("three", "first", "first", "four", "five");
        assertThat(list).filteredOn(e -> e.length() == 5).first().isEqualTo("three");
        assertThat(list).filteredOnAssertions(e -> assertThat(e.length()).isLessThan(5))
                .hasSize(2)
                .last().isEqualTo("five");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("three", "first", "four", "five");
        assertThat(map).hasSize(4)
                .containsValues(3, 2)
                .containsKeys("five", "four")
                .doesNotContainValue(23)
                .doesNotContainKey("six")
                .containsEntry("four", 2);
    }
}