package br.com.jaya.coinconverter.controller

import br.com.jaya.coinconverter.model.CurrencySearchRequestDTO
import br.com.jaya.coinconverter.model.CurrencySearchResponseDTO
import br.com.jaya.coinconverter.model.HistoricTransactionResponseDTO
import br.com.jaya.coinconverter.repository.model.CurrencyType
import br.com.jaya.coinconverter.services.CurrencyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("currency")
class CurrencyConverterController(
    private val currencyService: CurrencyService
) {

    @PostMapping("/search")
    fun serachForCoin(@RequestBody search: CurrencySearchRequestDTO): ResponseEntity<CurrencySearchResponseDTO> {
       return ResponseEntity.ok(currencyService.getExchangeRates(search))
    }

    @GetMapping("/types")
    fun getAllCurrencyTypes(): List<CurrencyType>{
        return currencyService.findAllCurrencyType()
    }

    @GetMapping("/historic")
    fun gettAllHistoricTransactionsConvert(): List<HistoricTransactionResponseDTO>{
        return currencyService.findAllHistoricTransactions();
    }


}