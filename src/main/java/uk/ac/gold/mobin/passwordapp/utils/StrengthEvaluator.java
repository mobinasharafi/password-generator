package uk.ac.gold.mobin.passwordapp.utils;

public class StrengthEvaluator {

    public enum StrengthLevel {
        WEAK, MEDIUM, STRONG, VERY_STRONG
    }

    public record StrengthResult(StrengthLevel level, double entropyBits) {}

    public static StrengthResult evaluate(String password, int poolSize) {
        double entropy = estimateEntropyBits(password.length(), poolSize);

        StrengthLevel level;
        if (entropy < 40) level = StrengthLevel.WEAK;
        else if (entropy < 60) level = StrengthLevel.MEDIUM;
        else if (entropy < 80) level = StrengthLevel.STRONG;
        else level = StrengthLevel.VERY_STRONG;

        return new StrengthResult(level, entropy);
    }

    private static double estimateEntropyBits(int length, int poolSize) {
        if (length <= 0 || poolSize <= 1) return 0.0;

        // log2(poolSize) = ln(poolSize) / ln(2)
        double log2 = Math.log(poolSize) / Math.log(2);
        return length * log2;
    }
}