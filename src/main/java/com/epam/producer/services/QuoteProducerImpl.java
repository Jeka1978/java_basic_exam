package com.epam.producer.services;

import com.epam.common.model.Quote;
import com.epam.common.model.QuoteStatus;
import com.epam.infra.Singleton;
import lombok.RequiredArgsConstructor;

/**
 * @author Evgeny Borisov
 */
@RequiredArgsConstructor
@Singleton
public class QuoteProducerImpl implements QuoteProducer {
    private final IdGenerator idGenerator;
    private final QuoterService quoterService;

    @Override
    public Quote generate() {


        String quoteText = quoterService.getRandomQuoteText();
        Quote quote = Quote.builder()
                .id(idGenerator.getNewId())
                .text(quoteText)
                .status(QuoteStatus.findByLength(quoteText.length()))
                .build();

        return quote;
    }
}

