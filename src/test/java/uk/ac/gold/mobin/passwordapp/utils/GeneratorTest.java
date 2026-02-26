package uk.ac.gold.mobin.passwordapp.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {

    @Test
    void generatesPasswordOfCorrectLength() {
        Generator generator = new Generator(
                true, true, true, true,
                false, false, false, false
        );

        String password = generator.generate(12);

        assertEquals(12, password.length());
    }

    @Test
    void requiredUppercaseIsPresent() {
        Generator generator = new Generator(
                true, true, true, true,
                true, false, false, false
        );

        String password = generator.generate(12);

        assertTrue(password.chars().anyMatch(Character::isUpperCase));
    }

    @Test
    void requiredDigitIsPresent() {
        Generator generator = new Generator(
                true, true, true, true,
                false, false, true, false
        );

        String password = generator.generate(12);

        assertTrue(password.chars().anyMatch(Character::isDigit));
    }

    @Test
    void excludedDigitsAreNotPresent() {
        Generator generator = new Generator(
                true, true, false, true,
                false, false, false, false
        );

        String password = generator.generate(12);

        assertFalse(password.chars().anyMatch(Character::isDigit));
    }

    @Test
    void throwsExceptionIfLengthTooSmallForRequirements() {
        Generator generator = new Generator(
                true, true, true, true,
                true, true, true, false
        );

        assertThrows(IllegalArgumentException.class, () -> generator.generate(2));
    }
}