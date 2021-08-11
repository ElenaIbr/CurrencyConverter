package com.example.currencyconverter.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse : MutableLiveData<Response<Currency>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response: Response<Currency> = repository.getPost()
            myResponse.value = response
        }
    }

}