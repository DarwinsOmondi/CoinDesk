package com.example.coindesk

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoinRetrofitClient {
    private const val BASE_URL = "https://api.coindesk.com/v1/bpi/"

    fun getService():CoinApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CoinApiService::class.java)
    }
}