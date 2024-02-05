package core.testBase;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static core.helpers.LogUtils.logInfo;
import static org.apache.commons.lang3.StringUtils.isBlank;

public final class Config {
    public static final String ENVIRONMENT;
    @Getter
    private static final Properties properties;

    static {
        ENVIRONMENT = getEnv();

        properties = loadProperties(ENVIRONMENT);
    }

    private Config() {
    }

    private static Properties loadProperties(String env) {
        String configFileName = env + "_config.properties";
        InputStream in = ClassLoader.getSystemResourceAsStream(configFileName);
        Properties properties = new Properties();

        try {
            properties.load(in);
        } catch (IOException ioe) {
            throw new IllegalStateException("Exception on loading {" + configFileName + "} conf file from classpath", ioe);
        }

        return properties;
    }

    private static String getEnv() {
        String env = System.getProperties().getProperty("env");

        if (isBlank(env)) {
            logInfo("No ENV option is set, please set -Denv in java. Now default environment local will be set");
            return "local";
        }

        return env;
    }

    public static String getPetStoreApiUrl() {
        return getUrl("petStore.url");
    }

    private static String getUrl(String configName) {
        if (ENVIRONMENT.equals("test") || ENVIRONMENT.equals("prod")) {
            //read url from system.properties
            return System.getProperties().getProperty(configName);
        } else if (ENVIRONMENT.equals("local")) {
            //read url from resources/stage_config.properties
            return properties.getProperty(configName);
        } else {
            return "";
        }
    }

}
