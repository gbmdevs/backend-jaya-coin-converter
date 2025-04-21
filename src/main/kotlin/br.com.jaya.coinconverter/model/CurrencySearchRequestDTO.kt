package br.com.jaya.coinconverter.model

data class CurrencySearchRequestDTO(
   val currencyOrigin: String,
   val currencyDestiny: String,
   val amount: Double
){}
