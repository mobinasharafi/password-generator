package uk.ac.gold.mobin.passwordapp.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrengthEvaluatorTest {

    @Test
    void evaluatesWeakForLowEntropy() {
        StrengthEvaluator.StrengthResult result =
                StrengthEvaluator.evaluate("abcdef", 10);

        assertEquals(StrengthEvaluator.StrengthLevel.WEAK, result.level());
    }

    @Test
    void evaluatesVeryStrongForHighEntropy() {
        StrengthEvaluator.StrengthResult result =
                StrengthEvaluator.evaluate("abcdefghijklmnopqrst", 70);

        assertEquals(StrengthEvaluator.StrengthLevel.VERY_STRONG, result.level());
    }
}