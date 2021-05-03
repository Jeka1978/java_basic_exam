package com.epam.infra;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Evgeny Borisov
 */
public class ApplicationContextTest {

    @Test
    public void getObject() {
        ApplicationContext context = new ApplicationContext(JavaConfig.builder().packagesToScan("com.epam.infra").build());
        PersonService personService = context.getObject(PersonService.class);
        Assert.assertNotNull(personService.repo);
    }
}