package com.example.currencyconverter.main

class Exchange(sum: Double, _course: Double) {

    val sum = sum
    val cour = _course

    public fun getResult(): Double{
        return  sum*cour
    }



}


