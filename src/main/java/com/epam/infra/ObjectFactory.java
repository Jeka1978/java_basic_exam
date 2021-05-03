package com.epam.infra;

import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Evgeny Borisov
 */
public class ObjectFactory {

    private ApplicationContext context;

    private List<ObjectConfigurator> configurators = new ArrayList<>();


    @SneakyThrows
    ObjectFactory(ApplicationContext context) {
        this.context = context;
        Reflections scanner = context.getScanner();
        Set<Class<? extends ObjectConfigurator>> classes = scanner.getSubTypesOf(ObjectConfigurator.class);
        for (Class<? extends ObjectConfigurator> aClass : classes) {
            if (!Modifier.isAbstract(aClass.getModifiers())) {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            }
        }
    }


    @SneakyThrows
    public <T> T createObject(Class<T> type) {

        T t = create(type);
        configure(t);


        return t;
    }

    private <T> T create(Class<T> type) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {

        Constructor<?>[] constructors = type.getDeclaredConstructors();
        if (constructors.length != 1) {
            throw new IllegalAccessException("you should decide if you love field injection, or constructor injection, anyway you should have exactly one constructor");
        }
        T t;
        Constructor<?> constructor = constructors[0];
        if (constructor.getParameterCount() == 0) {

            t = (T) constructor.newInstance();
        } else {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] params = Arrays.stream(parameterTypes).map(context::getObject).toArray();

            t = (T) constructor.newInstance(params);

        }
        return t;
    }

    private <T> void configure(T t) {
        for (ObjectConfigurator configurator : configurators) {
            configurator.configure(context, t);
        }
    }


}
