package com.gk.test.models.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ItemModel {

    int userId;
    int id;
    String title;
    String body;
}
