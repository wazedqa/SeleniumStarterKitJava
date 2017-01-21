package de.zalando.project.selenium.properties;

import de.zalando.project.selenium.driver.ParameterKey;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class SystemProperties {

    private static final Properties PROPERTIES = System.getProperties();

    public static Map<String, String> getDriverSystemProperties() {
        synchronized (PROPERTIES) {
            return PROPERTIES.entrySet().stream()
                    .filter(entry -> String.valueOf(entry.getKey()).startsWith(ParameterKey.DRIVER_PREFIX))
                    .collect(Collectors.toMap(entry -> String.valueOf(entry.getKey()), entry -> String.valueOf(entry.getValue())));
        }
    }

    public static void setProperty(String key, String value) {
        synchronized (PROPERTIES) {
            PROPERTIES.setProperty(key, value);
        }
    }

    public static String getProperty(String key) {
        synchronized (PROPERTIES){
            return PROPERTIES.getProperty(key);
        }
    }
}