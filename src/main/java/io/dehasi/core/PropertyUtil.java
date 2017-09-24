package io.dehasi.core;

import com.google.common.collect.Ordering;

import java.util.List;

final class PropertyUtil {

    private PropertyUtil() {}

    static boolean isPropertiesSorted(List<Property> properties) {
        return Ordering.natural().isOrdered(properties);
    }

    static String propertyToXmlLine(Property property) {
        String pattern = "<%s>%s</%s>";
        return String.format(pattern, property.name, property.value, property.name);
    }

}
