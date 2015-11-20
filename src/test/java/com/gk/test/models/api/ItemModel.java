package com.gk.test.models.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ItemModel {

    String itemType;
    String uniqueId;
    String itemName;


}
