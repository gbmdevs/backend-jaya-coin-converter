package br.com.jaya.coinconverter.repository

import br.com.jaya.coinconverter.model.HistoricTransactionResponseDTO
import br.com.jaya.coinconverter.repository.model.UsersHistoricalCurrencyConvert
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsersHistoricalCurrencyConvertRepository : CrudRepository<UsersHistoricalCurrencyConvert, UUID> {

    @Query("SELECT  uhcc.id," +
                   "uhcc.user_id, " +
                   "uhcc.currency_origin, " +
                   "uhcc.currency_origin_value, "+
                   "uhcc.currency_destiny, "+
                   "uhcc.currency_destiny_value, "+
                   "uhcc.tax_conversion, "+
                   "uhcc.operation_date_time "+
            "FROM \"USERS_HIST_CURRCY_CONVRT\" uhcc\n" +
            "inner join \"USERS\" u on u.id = uhcc.user_id", nativeQuery = true)
    fun findAllHistoricalConvert(): List<Map<String, Any>>
}