package com.example.currencyconverter.repository

import com.example.currencyconverter.api.RetrofitInstance
import com.example.currencyconverter.models.Currency
import retrofit2.Response

class Repository {

    suspend fun getPost() : Response<Currency> {
        return RetrofitInstance.apiService.getPost()
    }
}