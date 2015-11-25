package com.gk.test.framework.helpers;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.System.out;

public class LoadProperties {
    private static final Logger LOG = LoggerFactory.getLogger(LoadProperties.class);
    @Getter
    private static Properties environmentProps;
    @Getter
    private static Properties props;

    public static void loadRunConfigProps(String configPropertyFileLocation) {
        environmentProps = new Properties();
        try (InputStream inputStream = LoadProperties.class.getResourceAsStream(configPropertyFileLocation)) {
            environmentProps.load(inputStream);
            environmentProps.list(out);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        props = new Properties();
        try (InputStream inputStream = LoadProperties.class.getResourceAsStream(environmentProps.getProperty("profile.path"))) {
            props.load(inputStream);
            props.list(out);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
