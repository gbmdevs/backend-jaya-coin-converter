package br.com.jaya.coinconverter.services

import br.com.jaya.coinconverter.exception.BussinessException
import br.com.jaya.coinconverter.model.CurrencySearchRequestDTO
import br.com.jaya.coinconverter.model.CurrencySearchResponseDTO
import br.com.jaya.coinconverter.model.ExchangeRateResponse
import br.com.jaya.coinconverter.repository.CurrencyTypeRepository
import br.com.jaya.coinconverter.repository.UsersHistoricalCurrencyConvertRepository
import br.com.jaya.coinconverter.repository.model.CurrencyType
import br.com.jaya.coinconverter.repository.model.UsersHistoricalCurrencyConvert
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*

@Service
class CurrencyService(
    private val currencyTypeRepository: CurrencyTypeRepository,
    private val usersHistoricalCurrencyConvertRepository: UsersHistoricalCurrencyConvertRepository,
    @Value("\${currency.server.api.url:null}")
    private val baseUrlExchangeApi: String,
    @Value("\${currency.server.api.key:null}")
    private val exchangeApiKey: String,
    @Autowired
    private val restTemplate: RestTemplate,
    private val userService: UserService
) {
    fun getExchangeRates(search: CurrencySearchRequestDTO): CurrencySearchResponseDTO {
        val currencyList = findAllCurrencyType();
        if(currencyList.isEmpty()){
            throw BussinessException("No currency found in database.")
        }
        val apiUrl = UriComponentsBuilder.fromHttpUrl(baseUrlExchangeApi)
            .queryParam("access_key",exchangeApiKey)
            .queryParam("symbols",currencyList.joinToString(",") { it.currency })
            .toUriString()

        val responseApi = restTemplate.getForEntity(apiUrl, String::class.java)
        val responseBody = responseApi.body ?: throw RuntimeException("Failed to fetch exchange rates")
        return buildResponseExchangeRate(responseBody,search)

    }
    fun findAllCurrencyType(): List<CurrencyType>{
      return currencyTypeRepository.findAll().toList()
    }

    fun buildResponseExchangeRate(responseBody: String, search: CurrencySearchRequestDTO): CurrencySearchResponseDTO{
        val mapper = jacksonObjectMapper()
        val exchangeRateResponse = mapper.readValue(responseBody, ExchangeRateResponse::class.java)

        val fromRate = exchangeRateResponse.rates[search.currencyOrigin]
            ?: throw RuntimeException("Currency $search.currencyOrigin not found")
        val toRate = exchangeRateResponse.rates[search.currencyDestiny]
            ?: throw RuntimeException("Currency $search.currencyDestiny not found")
        val conversionRate = toRate / fromRate
        val valueDestiny = (search.amount * conversionRate)

        val historical = usersHistoricalCurrencyConvertRepository.save(
            UsersHistoricalCurrencyConvert(
                UUID.randomUUID(),
                userService.findUserByEmailAuth(),
                Date()
            )
        )
        return CurrencySearchResponseDTO(
            historical.id.toString(),
            "a",
            search.currencyOrigin,
            search.amount,
            search.currencyDestiny,
            valueDestiny,
            conversionRate,
            historical.operationDateTime
        )
    }


}