package com.example.modellitpromx.api

import com.example.modellitpromx.models.AccountProperties
import com.example.modellitpromx.models.AccountPropertiesDeleteKotlin
import retrofit2.http.*

public interface OpenApiService {



    @GET("apiGetAccountFeedV2.php")
    suspend fun getAccountProperties(
        @Query("email") email: String,
        @Query("password") password: String
        ) : AccountProperties


    @POST()
    suspend fun login()


}