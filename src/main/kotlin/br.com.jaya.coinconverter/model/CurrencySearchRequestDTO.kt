package br.com.jaya.coinconverter.model

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@Schema(description = "Product entity")
data class CurrencySearchRequestDTO(
   val currencyOrigin: String,
   val currencyDestiny: String,
   val amount: BigDecimal
){}
