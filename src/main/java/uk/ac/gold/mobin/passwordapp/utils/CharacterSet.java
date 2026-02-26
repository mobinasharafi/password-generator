package uk.ac.gold.mobin.passwordapp.utils;

public class CharacterSet {

    // Explicit character groups kept separate for clarity and reuse
    static String upper() {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    static String lower() {
        return "abcdefghijklmnopqrstuvwxyz";
    }

    static String digits() {
        return "0123456789";
    }

    static String symbols() {
        return "@#$%";
    }

    // Build the allowed pool from CLI flags (public so PasswordApp can compute pool size)
    public static String build(boolean includeUpper, boolean includeLower,
                               boolean includeDigits, boolean includeSymbols) {

        StringBuilder sb = new StringBuilder();

        if (includeUpper) sb.append(upper());
        if (includeLower) sb.append(lower());
        if (includeDigits) sb.append(digits());
        if (includeSymbols) sb.append(symbols());

        return sb.toString();
    }
}