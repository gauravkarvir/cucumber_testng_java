package com.gk.test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
* Define Constants.
* Create a new enum Class for defining new constants
*/


@AllArgsConstructor
public enum TestConstants {
    SALMON_TEST_FRAMEWORK("salmon test framework"),
    TEST_COUNT("test count");

    @Getter
    String cssClass;
}
