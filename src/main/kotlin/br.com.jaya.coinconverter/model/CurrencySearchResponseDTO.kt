package br.com.jaya.coinconverter.model

import java.util.*

data class CurrencySearchResponseDTO(
    val transcationId: String,
    val userId: String,
    val currencyOrigin: String,
    val valueOrigin: Double,
    val currencyDestiny: String,
    val valueDestiny: Double,
    val taxCorvesion: Double,
    val dateOperationTime: Date
) {
    constructor() : this(
        "", "", "", 0.0, "", 0.0, 0.0,
        Date()
    )
}
