package com.gk.test.framework.helpers.utils;

import com.gk.test.enums.PermittedCharacters;
import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public final class RandomGenerator {

    public static String random(Integer length, PermittedCharacters permittedCharacters) {
        String randomString = null;
        if (PermittedCharacters.ALPHABETS.equals(permittedCharacters)) {
            randomString = randomString(length);
        } else if (PermittedCharacters.NUMERIC.equals(permittedCharacters)) {
            randomString = randomInteger(length);
        } else if (PermittedCharacters.ALPHANUMERIC.equals(permittedCharacters)) {
            randomString = randomAlphanumeric(length);
        } else if (PermittedCharacters.ANY_CHARACTERS.equals(permittedCharacters)) {
            randomString = randomAsciiCharacters(length);
        } else if (PermittedCharacters.ANY_CHARACTERS_SUPPORTS_MULTILINGUAL.equals(permittedCharacters)) {
            randomString = randomAsciiCharacters(length);
        }
        return randomString;
    }

    /**
     * Generates random Number.
     *
     * @param length length of random number to be generated
     */
    private static String randomInteger(Integer length) {
        return RandomStringUtils.randomNumeric(length);
    }

    /**
     * Generates random String.
     *
     * @param length length of random characters to be generated
     */
    private static String randomString(Integer length) {
        return RandomStringUtils.random(length, true, false);
    }

    /**
     * Generates random alphanumeric String.
     *
     * @param length length of random alphanumeric characters to be generated
     */
    private static String randomAlphanumeric(Integer length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    /**
     * Generates random alphabetic String.
     *
     * @param length length of random alphabetic characters to be generated
     */
    public static String randomAlphabetic(Integer length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    /**
     * Generates random emailaddress for @example.com domain  in lower case
     *
     * @param length length of random alphanumeric characters to be generated for the local part of email address
     */
    public static String randomEmailAddress(Integer length) {
        String email = randomAlphanumeric(length) + "@example.com";
        return email.toLowerCase();
    }

    /**
     * Generates random gender in short text form "M" , "F" , "U"
     * M = Male , F = Female , U = Unspecified
     */
    public static String randomGenderShortText() {
        List<String> gender = new LinkedList<>();
        gender.add("M");
        gender.add("F");
        gender.add("U");
        Random rand = new Random();
        int choice = rand.nextInt(gender.size());
        return gender.get(choice);
    }

    /**
     * Generates random gender in full text form
     * Male , Female , Unspecified
     */
    public static String randomGenderFullText() {
        List<String> gender = new LinkedList<>();
        gender.add("Male");
        gender.add("Female");
        gender.add("Unspecified");
        Random rand = new Random();
        int choice = rand.nextInt(gender.size());
        return gender.get(choice);
    }

    /**
     * Generates random plus or minus
     * "-" , "+"
     */

    public static String randomPlusOrMinus() {
        List<String> item = new LinkedList<>();
        item.add("-");
        item.add("+");

        Random rand = new Random();
        int choice = rand.nextInt(item.size());
        return item.get(choice);
    }


    public static DateTime randomAdultsDOB() {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.minusYears((int) (18 + (Math.random() * ((50 - 18) + 1))));
        return dateTime.minusYears((int) (18 + (Math.random() * ((50 - 18) + 1))));
    }

    public static String formatDate(DateTime dateTime, String dateformat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(dateformat);
        return dateTime.toString(dateTimeFormatter);
    }

    public static DateTimeFormatter dateFormatterWithLocale(Locale locale) {
        return DateTimeFormat.mediumDate().withLocale(locale);
    }

    public static String dateWithNoLeadingZero(String dateWithLeadingZero) {
        String dateWithNoLeadingZero;
        dateWithNoLeadingZero = CharMatcher.is('0').trimLeadingFrom(dateWithLeadingZero);
        return dateWithNoLeadingZero;
    }

    public static String randomFutureFormattedDate(String dateformat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(dateformat);
        DateTime dateTime = new DateTime();
        final DateTime plusYears = dateTime.plusYears((int) (1 + Math.random() * (10 - 1)));
        return plusYears.toString(dateTimeFormatter);
    }

    private static String randomAsciiCharacters(Integer characterAmount) {
        return RandomStringUtils.random(characterAmount, 32, 127, false, false);
    }

}