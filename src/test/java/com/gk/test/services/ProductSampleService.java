package com.gk.test.services;

import com.gk.test.framework.helpers.UrlBuilder;

import java.net.URL;

class ProductSampleService extends UrlBuilder {

    public URL getListOfColours() {
        return getApiUrlForEndPoint("/en/api/products/colors");
    }
}
