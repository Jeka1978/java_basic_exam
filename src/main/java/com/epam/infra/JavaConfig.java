package com.epam.infra;

import lombok.*;

import java.util.Map;

/**
 * @author Evgeny Borisov
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JavaConfig implements Config {

    @Getter
    private String packagesToScan;

    @Singular
    private Map<Class,Class> ifc2ImplClasses;

    @Override
    public <T> Class<T> getImplClass(Class<T> type) {
        return ifc2ImplClasses.get(type);
    }
}








