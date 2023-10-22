package com.sgriendt.game;

public class InputValidation {

    public static boolean validNumberInput(String s) {
        if (s.isBlank() || s.isEmpty()) {
            return false;
        }
        final String trimmedString = s.trim();
        try {
            Integer.parseInt(trimmedString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidRange(String s) {
        return isInValidRange(Integer.parseInt(s));
    }

    public static boolean isInValidRange(int i) {
        return i >= 0 && i < 6;
    }
}
