package com.epam.infra;

/**
 * @author Evgeny Borisov
 */
public interface Config {
    <T> Class<T> getImplClass(Class<T> type);

    String getPackagesToScan();
}
