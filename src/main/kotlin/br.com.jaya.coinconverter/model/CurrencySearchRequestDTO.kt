package br.com.jaya.coinconverter.model

import java.math.BigDecimal

data class CurrencySearchRequestDTO(
   val currencyOrigin: String,
   val currencyDestiny: String,
   val amount: BigDecimal
){}
