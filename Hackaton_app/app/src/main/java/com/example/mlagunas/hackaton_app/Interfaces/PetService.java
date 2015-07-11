package com.example.mlagunas.hackaton_app.Interfaces;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.mime.TypedInput;

/**
 * Created by mlagunas on 11/07/15.
 */
public interface PetService {
    @Headers("Content-Type: text/plain")
    @POST("/data/query/285/sql?origin=original")
    void listAnimals(@Body TypedInput string, Callback<Response> callback);;
}
