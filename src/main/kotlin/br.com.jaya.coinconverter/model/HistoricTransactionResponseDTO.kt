package br.com.jaya.coinconverter.model

import java.math.BigDecimal

data class HistoricTransactionResponseDTO(
    val id: String,
    val userId: String,
    val currencyOrigin: String,
    val valueOrigin: BigDecimal,
    val currencyDestiny: String,
    var valueDestiny: BigDecimal,
    val taxConversion: BigDecimal,
    val dateOperation: String
) {
    constructor(transactionId: String) : this(
        "", "", "", BigDecimal.ZERO,
        "", BigDecimal.ZERO, BigDecimal.ZERO,""
    )
}
