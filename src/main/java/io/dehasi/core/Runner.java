package io.dehasi.core;

import java.io.File;
import java.util.List;

import static io.dehasi.core.PomReader.loadProperties;
import static io.dehasi.core.Property.isPropertiesSorted;

public class Runner {

    private void run() {

        List<Property> properties = loadProperties(new File("pom.xml"));

        if (isPropertiesSorted(properties)) {

            System.out.println("The properties are sorted");

        } else {

            properties.stream()
                    .map(Property::toXmlLine)
                    .forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        new Runner().run();
    }
}
