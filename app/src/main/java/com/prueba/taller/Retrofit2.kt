package com.prueba.taller
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Retrofit2 {

    private var urls: APIservice? = null

    init {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        urls = retrofit.create(APIservice::class.java)
    }

    fun getService(): APIservice? {
        return urls
    }
}