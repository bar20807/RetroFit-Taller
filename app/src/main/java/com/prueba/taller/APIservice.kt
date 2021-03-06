package com.prueba.taller

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIservice {
    @GET("top-headlines")
    suspend fun getNewsByCategory(
            @Query("country") country:String,
            @Query("category") category:String,
            @Query("apiKey") apiKey:String
    ): Response<NewResponse>
}