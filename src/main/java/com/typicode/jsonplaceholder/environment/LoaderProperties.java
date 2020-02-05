package com.typicode.jsonplaceholder.environment;

import org.apache.commons.configuration.PropertiesConfiguration;

public class LoaderProperties {
    private static PropertiesConfiguration config = loadProperties();

    private static PropertiesConfiguration loadProperties() {

        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();

            try {
                propertiesConfiguration.load("src/main/resources/application.properties");
            } catch (org.apache.commons.configuration.ConfigurationException e) {
                e.printStackTrace();
            }

        return propertiesConfiguration;
    }

    public static String getBaseUri(){
        return config.getString("url.base.uri");
    }

    public static String getBasePathUsers(){
        return config.getString("url.base.path.users");
    }

    public static String getBasePathPosts(){
        return config.getString("url.base.path.posts");
    }
}
