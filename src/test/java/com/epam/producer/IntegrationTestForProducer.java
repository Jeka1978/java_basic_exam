package com.epam.producer;

import com.epam.common.model.Quote;
import com.epam.infra.ApplicationContext;
import com.epam.infra.JavaConfig;
import com.epam.producer.services.QuoteProducer;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Evgeny Borisov
 */
public class IntegrationTestForProducer {

    @Test
    public void testGetQuote() {
        ApplicationContext context = new ApplicationContext(JavaConfig.builder().packagesToScan("com.epam").build());
        QuoteProducer producer = context.getObject(QuoteProducer.class);
        Quote quote = producer.generate();
        Assert.assertNotNull(quote);
        Assert.assertNotNull(quote.getText());
        Assert.assertNotNull(quote.getStatus());
        Assert.assertNotEquals(0, quote.getId());
    }
}
