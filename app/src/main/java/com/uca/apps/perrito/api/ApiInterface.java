package com.uca.apps.perrito.api;

import com.uca.apps.perrito.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by moisolutions on 30/1/18.
 */

public interface ApiInterface {
    //@GET("dogs")
    //Call<List<UserModel>> listDogs();

    @POST("users/create.json")
    Call<UserModel> userCreate(@Body UserModel userModel);

}
