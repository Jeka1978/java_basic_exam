package com.epam.producer;

import com.epam.common.model.Quote;
import com.epam.common.model.QuoteStatus;
import com.epam.producer.services.IdGenerator;
import com.epam.producer.services.QuoteProducer;
import com.epam.producer.services.QuoteProducerImpl;
import com.epam.producer.services.QuoterService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Evgeny Borisov
 */
public class QuoteProducerImplTest {

    @Test
    public void testThatProducerCanGenerateQuote() {

        IdGenerator idGeneratorMock = mock(IdGenerator.class);

        when(idGeneratorMock.getNewId()).thenReturn(10L);

        QuoterService quoterServiceMock = mock(QuoterService.class);

        when(quoterServiceMock.getRandomQuoteText()).thenReturn("I'll be back");

        QuoteProducer producer = new QuoteProducerImpl(idGeneratorMock,quoterServiceMock);


        Quote quote = producer.generate();
        Assert.assertNotNull(quote);
        Assert.assertNotNull(quote.getText());
        Assert.assertNotNull(quote.getStatus());
        Assert.assertNotEquals(0, quote.getId());

        if (quote.getText().length() < 10) {
            Assert.assertSame(quote.getStatus(), QuoteStatus.SMALL);
        }

    }
}