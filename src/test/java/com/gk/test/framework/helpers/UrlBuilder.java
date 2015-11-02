package com.gk.test.framework.helpers;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class UrlBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(UrlBuilder.class);
    @Getter
    private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
    private static URL basePath;
    private static URL apiUrl;

    static {
        try {
            LoadProperties.loadRunConfigProps();
            basePath = new URL(LoadProperties.getRunProps().getProperty("site.url"));
            apiUrl = new URL(LoadProperties.getRunProps().getProperty("api.url"));
        } catch (MalformedURLException e) {
            LOG.error(e.getMessage());
        }

    }

    public static void startAtHomePage() {
        WebDriverHelper.getWebDriver().navigate().to(getUrl());
    }

    public static URL getApiUrlForEndPoint(String endpoint) {
        return createApiUrl(endpoint);
    }

    public static URI getBasePathURI() {
        return URI.create(LoadProperties.getRunProps().getProperty(""));
    }


    private static String getUrl() {
        return LoadProperties.getRunProps().getProperty("site.url");
    }

    private static URL createApiUrl(String endpoint) {
        try {
            return new URL(apiUrl.getProtocol(), apiUrl.getHost(), apiUrl.getPort(), endpoint);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public static URL createUrl(String path) {
        try {
            return new URL(basePath.getProtocol(), basePath.getHost(), basePath.getPort(), path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
