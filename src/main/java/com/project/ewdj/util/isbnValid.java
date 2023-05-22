package com.project.ewdj.util;

public class isbnValid {

    public boolean isValidISBN(String isbn) {
        // Remove any hyphens or spaces from the ISBN
        String cleanedISBN = isbn.replaceAll("[\\s-]", "");

        // Check if the cleaned ISBN has a length of 10 or 13 digits
        if (cleanedISBN.length() != 10 && cleanedISBN.length() != 13) {
            return false;
        }

        // Calculate the check digit based on the ISBN type
        int checkDigit;
        if (cleanedISBN.length() == 10) {
            checkDigit = calculateISBN10CheckDigit(cleanedISBN);
        } else {
            checkDigit = calculateISBN13CheckDigit(cleanedISBN);
        }

        // Compare the check digit with the last digit of the ISBN
        char lastDigit = cleanedISBN.charAt(cleanedISBN.length() - 1);
        int lastDigitValue;
        if (lastDigit == 'X' || lastDigit == 'x') {
            lastDigitValue = 10;
        } else {
            lastDigitValue = Character.getNumericValue(lastDigit);
        }

        return checkDigit == lastDigitValue;
    }

    private int calculateISBN10CheckDigit(String isbn) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i + 1) * digit;
        }

        int checkDigit = sum % 11;
        if (checkDigit == 10) {
            return 'X';
        } else {
            return checkDigit;
        }
    }

    private int calculateISBN13CheckDigit(String isbn) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit;
    }

}
