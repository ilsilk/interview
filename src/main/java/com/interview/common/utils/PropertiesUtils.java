package com.interview.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;

@UtilityClass
public final class PropertiesUtils {

    private static final String PROPERTIES_FILE = "config.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        try (var inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            PROPERTIES.load(inputStream);
        } catch (IOException | NullPointerException e) {
            throw new IllegalStateException("Load properties file error", e);
        }
    }

    private static <T> T getProperty(String key, Function<String, T> parser, T defaultValue) {
        var value = System.getenv(key.toUpperCase().replace(".", "_"));
        if (StringUtils.isBlank(value)) {
            value = PROPERTIES.getProperty(key);
        }
        return StringUtils.isNotBlank(value) ? parser.apply(value) : defaultValue;
    }

    public static String getStringProperty(String key) {
        return getProperty(key, s -> s, "");
    }

    public static int getIntProperty(String key) {
        return getProperty(key, Integer::parseInt, 0);
    }
}
