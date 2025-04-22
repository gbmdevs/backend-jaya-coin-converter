package br.com.jaya.coinconverter.model

import java.math.BigDecimal
import java.util.*

data class CurrencySearchResponseDTO(
    val transcationId: String,
    val userId: String,
    val currencyOrigin: String,
    val valueOrigin: BigDecimal,
    val currencyDestiny: String,
    val valueDestiny: BigDecimal,
    val taxCorvesion: BigDecimal,
    val dateOperationTime: Date
) {
    constructor() : this(
        "", "", "", BigDecimal.ZERO, "", BigDecimal.ZERO, BigDecimal.ZERO,
        Date()
    )
}
