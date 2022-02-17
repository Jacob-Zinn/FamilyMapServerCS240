package com.example.modellitpromx.api;

import com.example.modellitpromx.models.AccountProperties;

import retrofit2.http.GET;
import retrofit2.http.Query;

public class OpenApiServiceJava {

    @GET("apiGetAccountFeedV2.php")
    suspend fun getAccountProperties2(
            @Query("email")email: String,
            @Query("password")password: String
    ) : AccountProperties
}
