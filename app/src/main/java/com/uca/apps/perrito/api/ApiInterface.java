package com.uca.apps.perrito.api;

import com.uca.apps.perrito.models.DogModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by moisolutions on 30/1/18.
 */

public interface ApiInterface {
    @GET("dogs")
    Call<List<DogModel>> listDogs();
}
