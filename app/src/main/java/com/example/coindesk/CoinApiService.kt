package com.example.coindesk

import com.example.coindesk.DataClass.Coin
import retrofit2.Call
import retrofit2.http.GET

interface CoinApiService {
    @GET("currentprice.json")
    fun getCoinData():Call<Coin>
}