package com.example.currencyconverter.api

import com.example.currencyconverter.models.Currency
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {

    @GET("daily_json.js")
    suspend fun getPost() : Response<Currency>

}