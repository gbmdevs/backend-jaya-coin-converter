package br.com.jaya.coinconverter.model

import java.util.*

data class HistoricTransactionResponseDTO(
    val transactionId: UUID
) {
    constructor(transactionId: String) : this(UUID.fromString(transactionId))
}
