package br.com.jaya.coinconverter.repository

import br.com.jaya.coinconverter.repository.model.CurrencyType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyTypeRepository : CrudRepository<CurrencyType, Long> {
}