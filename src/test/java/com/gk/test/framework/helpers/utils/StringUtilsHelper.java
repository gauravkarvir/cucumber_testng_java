package com.gk.test.framework.helpers.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtilsHelper {

    public static List<String> findTheLongOrShortWord(String sentence, String longOrShortOperation) {
        List<String> foundWords;
        String[] wordsInSentence = sentence.split(" ");

        if (longOrShortOperation.equals("LONG")) {
            return Arrays.stream(wordsInSentence).sorted((words1, words2) -> words2.length() - words1.length()).collect(Collectors.toList());
        } else if (longOrShortOperation.equals("SHORT")) {
            return Arrays.stream(wordsInSentence).sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        } else {
            return null;
        }

    }

}
