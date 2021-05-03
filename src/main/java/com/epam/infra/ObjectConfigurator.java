package com.epam.infra;

/**
 * @author Evgeny Borisov
 */
public interface ObjectConfigurator {

    void configure(ApplicationContext context,Object t);
}
