package com.epam.common.model;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Evgeny Borisov
 */
@RequiredArgsConstructor
public enum QuoteStatus implements Serializable {
    SMALL(0, 10),
    MEDIUM(11, 20),
    LARGE(21, Integer.MAX_VALUE);

    private final int min;
    private final int max;


    public static QuoteStatus findByLength(int length) {
        return Arrays.stream(values())
                .filter(status -> status.min <= length)
                .filter(status -> status.max >= length)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("length is invalid"));

    }
}













