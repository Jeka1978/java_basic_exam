package com.epam.common.model;

import lombok.*;

import java.io.Serializable;

/**
 * @author Evgeny Borisov
 */
@Data
@Builder
@AllArgsConstructor
public class Quote implements Serializable {
    private final long id;
    private final String text;
    private final QuoteStatus status;
}

