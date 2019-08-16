package com.gk.test.services;


import com.gk.test.framework.helpers.ApiHelper;
import com.gk.test.models.api.ItemModel;
import com.jayway.restassured.response.Response;

import java.util.List;

public class Api extends ApiHelper {


    public static Response getList(String endpoint) {
        return givenConfig().when().get(endpoint);
    }

    public static Response postDetails(List<ItemModel> itemModels) {


        return givenConfig().
                body(gson().toJson(itemModels)).
                when().
                post("posts");
    }


    public static Response updateDetails(List<ItemModel> itemModels) {
        return givenConfig().
                body(gson().toJson(itemModels)).
                when().
                put("posts/1");
    }


    public static Response deleteItem(String id) {
        return givenConfig().
                when().delete("posts/" + id);
    }

}
