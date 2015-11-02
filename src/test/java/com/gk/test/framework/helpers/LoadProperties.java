package com.gk.test.framework.helpers;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class LoadProperties {
    private static final Logger LOG = LoggerFactory.getLogger(LoadProperties.class);
    @Getter
    private static Properties runProps;

    public static void loadRunConfigProps() {

        runProps = new Properties();
        try (InputStream inputStream = LoadProperties.class.getResourceAsStream(UrlBuilder.getRUN_CONFIG_PROPERTIES())) {
            runProps.load(inputStream);
            setUpEnvironmentURLFor("site.url");
            setUpEnvironmentURLFor("api.url");
            setUpEnvironmentURLFor("site.port");
            setUpEnvironmentURLFor("site.basepath");
            setUpEnvironmentURLFor("browser");
            setUpEnvironmentURLFor("platform");
            setUpEnvironmentURLFor("driver.root.dir");
        } catch (IOException e) {
            LOG.info(e.getMessage());
        }
    }

    private static void setUpEnvironmentURLFor(String key) {
        String value = getRunProps().getProperty(key);
        LOG.warn("Properties : key  " + key + " value :" + value);

        if (StringUtils.startsWith(value, "http://")) {
            return;
        }
        String urlFromVMOptions = System.getProperty(key);
        if (null != urlFromVMOptions) {

            LoadProperties.getRunProps().put(key, urlFromVMOptions);
        }
    }
}
