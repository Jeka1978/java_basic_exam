package com.epam.infra;

import com.epam.producer.services.InjectValue;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Evgeny Borisov
 */
public class InjectValueAnnotationObjectConfigurator implements ObjectConfigurator {

    private Map<String,String> propertyMap;

    @SneakyThrows
    public InjectValueAnnotationObjectConfigurator() {
        URI uri = ClassLoader.getSystemClassLoader().getResource("application.properties").toURI();
        File file = new File(uri);
        propertyMap = new BufferedReader(new FileReader(file)).lines()
                .map(line -> line.split("="))
                .collect(Collectors.toMap(arr -> arr[0].trim(), arr -> arr[1].trim()));
    }

    @Override
    @SneakyThrows
    public void configure(ApplicationContext context, Object t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectValue annotation = field.getAnnotation(InjectValue.class);
            if (annotation != null) {
                String propertyName = annotation.value();
                field.setAccessible(true);
                field.set(t,propertyMap.get(propertyName));
            }
        }
    }
}
