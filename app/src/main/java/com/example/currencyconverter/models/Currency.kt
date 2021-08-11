package com.example.currencyconverter.models

data class Currency(
    val Date: String,
    val PreviousDate: String,
    val Timestamp: String,
    val PreviousURL: String,
    val Valute: Map<String, Valute>
)
