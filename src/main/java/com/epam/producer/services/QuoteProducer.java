package com.epam.producer.services;

import com.epam.common.model.Quote;

/**
 * @author Evgeny Borisov
 */
public interface QuoteProducer {
    Quote generate();
}
