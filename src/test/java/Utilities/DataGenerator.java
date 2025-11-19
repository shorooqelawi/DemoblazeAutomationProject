package Utilities;

import java.security.SecureRandom;

public class DataGenerator {
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String LETTERS_UP = LETTERS.toUpperCase();
    private static final String DIGITS = "0123456789";
    private static final String ALPHANUM = LETTERS + LETTERS_UP + DIGITS;
    private static final SecureRandom RND = new SecureRandom();

    public static String randomAlpha(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) sb.append(LETTERS.charAt(RND.nextInt(LETTERS.length())));
        return sb.toString();
    }

    public static String randomAlphaNum(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) sb.append(ALPHANUM.charAt(RND.nextInt(ALPHANUM.length())));
        return sb.toString();
    }

    public static String randomDigits(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) sb.append(DIGITS.charAt(RND.nextInt(DIGITS.length())));
        return sb.toString();
    }

    // Username must be 5-12 chars. Keep it alphanumeric and unique-ish without being too long.
    public static String username(String prefix) {
        String cleanPrefix = prefix == null ? "user" : prefix.replaceAll("[^A-Za-z0-9]", "");
        if (cleanPrefix.isEmpty()) cleanPrefix = "user";

        int minLen = 5;
        int maxLen = 12;
        int targetLen = 8 + RND.nextInt(5); // 8-12
        if (targetLen < minLen) targetLen = minLen;
        if (targetLen > maxLen) targetLen = maxLen;

        // Reserve 3 digits for uniqueness at the end
        int uniqueness = 3;
        int baseLen = Math.max(minLen - uniqueness, 1);
        int remaining = Math.max(baseLen, targetLen - uniqueness) - cleanPrefix.length();
        if (remaining < 0) remaining = 0;
        String body = randomAlphaNum(remaining);
        String suffix = randomDigits(uniqueness);

        String candidate = (cleanPrefix + body + suffix);
        // Trim to max length if needed
        if (candidate.length() > maxLen) {
            candidate = candidate.substring(0, maxLen);
        }
        // Ensure length >= minLen
        while (candidate.length() < minLen) {
            candidate += randomAlphaNum(1);
        }
        return candidate;
    }

    public static String email(String localPrefix, String domain) {
        // Keep local part reasonably short but unique
        String lp = (localPrefix == null || localPrefix.isBlank()) ? "qa" : localPrefix.replaceAll("[^A-Za-z0-9]", "");
        String dm = (domain == null || domain.isBlank()) ? "mailinator.com" : domain;
        return lp + "+" + randomAlphaNum(4) + randomDigits(2) + "@" + dm;
    }

    public static String password(int len) {
        // UI rule: 4-12 chars, include at least one upper, one lower, one digit
        int minLen = 4, maxLen = 12;
        int target = Math.max(minLen, Math.min(len, maxLen));
        String upper = LETTERS_UP.charAt(RND.nextInt(LETTERS_UP.length())) + "";
        String lower = LETTERS.charAt(RND.nextInt(LETTERS.length())) + "";
        String digit = DIGITS.charAt(RND.nextInt(DIGITS.length())) + "";
        String rest = randomAlphaNum(Math.max(0, target - 3));
        String combined = upper + lower + digit + rest;
        // shuffle
        char[] a = combined.toCharArray();
        for (int i = a.length - 1; i > 0; i--) {
            int j = RND.nextInt(i + 1);
            char tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        return new String(a);
    }

    public static String firstName() { return capitalize(randomAlpha(2 + RND.nextInt(10))); } // 2-11
    public static String lastName()  { return capitalize(randomAlpha(2 + RND.nextInt(12))); } // 2-13
    public static String city()      { return capitalize(randomAlpha(3 + RND.nextInt(10))); }
    public static String state()     { return capitalize(randomAlpha(3 + RND.nextInt(10))); }
    public static String address()   { return randomDigits(3) + " " + capitalize(randomAlpha(5 + RND.nextInt(10))) + " St"; }
    public static String postalCode(){ return randomDigits(5); }
    public static String phone()     { return randomDigits(10); }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
