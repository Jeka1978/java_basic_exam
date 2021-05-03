package com.epam.infra;

import lombok.AccessLevel;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Evgeny Borisov
 */
@Getter(AccessLevel.PACKAGE)
public class ApplicationContext {
    private Reflections scanner;

    private final Config config;

    private ObjectFactory factory;

    private Map<Class<?>, Object> singletonMap = new HashMap<>();

    public ApplicationContext(Config config) {
        this.config = config;
        scanner = new Reflections(config.getPackagesToScan());
        factory = new ObjectFactory(this);
    }


    public <T> T getObject(Class<T> type) {
        type = resolveRealImpl(type);
        if (singletonMap.containsKey(type)) {
            return (T) singletonMap.get(type);
        }
        T t = factory.createObject(type);
        if (type.isAnnotationPresent(Singleton.class)) {
            singletonMap.put(type, t);
        }
        return t;
    }

    private <T> Class<T> resolveRealImpl(Class<T> type) {
        if (type.isInterface()) {
            Class<T> implClass = this.getConfig().getImplClass(type);
            if (implClass == null) {
                Set<Class<? extends T>> classes = this.getScanner().getSubTypesOf(type);
                if (classes.size() != 1) {
                    throw new IllegalStateException("0 or more than one impl found for type " + type);
                }
                type = (Class<T>) classes.iterator().next();
            } else {
                type = implClass;
            }
        }
        return type;
    }
}






