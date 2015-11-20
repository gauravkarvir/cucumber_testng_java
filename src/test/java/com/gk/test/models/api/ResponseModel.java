package com.gk.test.models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {
    List<Colors> colors;
    List<Hues> hues;

    @Data
    public class Colors {
        String colorSpaceId;
        String sensation;
        String hue;
        String name;
        String colorId;
        String uriFriendlyName;
        String rgb;
        String collection;
    }

    @Data
    public class Hues {
        String hue;
        String name;
        String color;
        String mutedName;
        String mutedRgb;
        String rgb;
    }

}
