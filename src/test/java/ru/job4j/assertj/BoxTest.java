package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(23, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isCorrectVertices() {
        Box box = new Box(8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(7)
                .isLessThan(9)
                .isEqualTo(8);
    }

    @Test
    void isNotCorrectVertices() {
        Box box = new Box(23, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isNotZero()
                .isNegative()
                .isGreaterThan(-2)
                .isLessThan(0)
                .isEqualTo(-1);
    }

    @Test
    void checkBooleanTrue() {
        Box box = new Box(8, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void checkBooleanFalse() {
        Box box = new Box(23, 10);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void checkDoubleCube() {
        Box box = new Box(8, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(600d, withPrecision(0.006d))
                .isCloseTo(601, withPrecision(1d))
                .isCloseTo(599d, Percentage.withPercentage(0.17d))
                .isGreaterThan(599d)
                .isLessThan(601d);
    }

    @Test
    void checkDoubleSphere() {
        Box box = new Box(0, 2);
        double result = box.getArea();
        assertThat(result).isEqualTo(50.26d, withPrecision(0.01d))
                .isCloseTo(50.3d, withPrecision(0.04d))
                .isCloseTo(51d, Percentage.withPercentage(1.5d))
                .isGreaterThan(50d)
                .isLessThan(51d);
    }
}