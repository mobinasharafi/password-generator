package uk.ac.gold.mobin.passwordapp.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratorTest {

    @Test
    void generatesPasswordOfCorrectLength() {
        Generator generator = new Generator();
        String password = generator.generate(12);
        assertEquals(12, password.length());
    }
}