package br.com.jaya.coinconverter.repository

import br.com.jaya.coinconverter.model.HistoricTransactionResponseDTO
import br.com.jaya.coinconverter.repository.model.UsersHistoricalCurrencyConvert
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsersHistoricalCurrencyConvertRepository : CrudRepository<UsersHistoricalCurrencyConvert, UUID> {

    @Query("SELECT CAST(id AS TEXT) AS transactionId FROM \"USERS_HIST_CURRCY_CONVRT\"", nativeQuery = true)
    fun findAllHistoricalConvert(): List<HistoricTransactionResponseDTO>
}