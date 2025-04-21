package br.com.jaya.coinconverter.repository

import br.com.jaya.coinconverter.repository.model.UsersHistoricalCurrencyConvert
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersHistoricalCurrencyConvertRepository : CrudRepository<UsersHistoricalCurrencyConvert, Long> {

}