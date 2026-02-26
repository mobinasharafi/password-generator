package uk.ac.gold.mobin.passwordapp.utils;

import java.security.SecureRandom;

public class Generator {

    private final boolean includeUpper;
    private final boolean includeLower;
    private final boolean includeDigits;
    private final boolean includeSymbols;

    private final boolean requireUpper;
    private final boolean requireLower;
    private final boolean requireDigit;
    private final boolean requireSymbol;

    private final SecureRandom random = new SecureRandom();

    public Generator(boolean includeUpper, boolean includeLower,
                     boolean includeDigits, boolean includeSymbols,
                     boolean requireUpper, boolean requireLower,
                     boolean requireDigit, boolean requireSymbol) {

        this.includeUpper = includeUpper;
        this.includeLower = includeLower;
        this.includeDigits = includeDigits;
        this.includeSymbols = includeSymbols;

        this.requireUpper = requireUpper;
        this.requireLower = requireLower;
        this.requireDigit = requireDigit;
        this.requireSymbol = requireSymbol;

        // Ensure we are not building an empty character set
        if (CharacterSet.build(includeUpper, includeLower, includeDigits, includeSymbols).isEmpty()) {
            throw new IllegalArgumentException("Character set is empty. Enable at least one character group.");
        }
    }

    public String generate(int length) {

        if (length < 1) {
            throw new IllegalArgumentException("Length must be >= 1");
        }

        int requiredClasses = 0;
        if (requireUpper) requiredClasses++;
        if (requireLower) requiredClasses++;
        if (requireDigit) requiredClasses++;
        if (requireSymbol) requiredClasses++;

        if (length < requiredClasses) {
            throw new IllegalArgumentException("Length is too small for the required character classes.");
        }

        String all = CharacterSet.build(includeUpper, includeLower, includeDigits, includeSymbols);
        StringBuilder password = new StringBuilder(length);

        // Guarantee required character classes are satisfied
        if (requireUpper) password.append(pickFrom(CharacterSet.upper()));
        if (requireLower) password.append(pickFrom(CharacterSet.lower()));
        if (requireDigit) password.append(pickFrom(CharacterSet.digits()));
        if (requireSymbol) password.append(pickFrom(CharacterSet.symbols()));

        // Fill remaining length from allowed pool
        while (password.length() < length) {
            password.append(all.charAt(random.nextInt(all.length())));
        }

        // Shuffle to avoid predictable ordering of required characters
        return shuffle(password);
    }

    private char pickFrom(String chars) {
        return chars.charAt(random.nextInt(chars.length()));
    }

    // Fisher–Yates shuffle for unbiased distribution
    private String shuffle(StringBuilder sb) {
        for (int i = sb.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char tmp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, tmp);
        }
        return sb.toString();
    }
}