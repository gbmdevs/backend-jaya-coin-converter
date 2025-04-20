package br.com.jaya.coinconverter.controller

import br.com.jaya.coinconverter.model.CurrencySearchRequestDTO
import br.com.jaya.coinconverter.model.CurrencySearchResponseDTO
import br.com.jaya.coinconverter.repository.model.CurrencyType
import br.com.jaya.coinconverter.services.CurrencyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("currency")
class CurrencyConverterController(
    private val currencyService: CurrencyService
) {

    @GetMapping("/search")
    fun serachForCoin(@RequestBody search: CurrencySearchRequestDTO): ResponseEntity<CurrencySearchResponseDTO> {
       return ResponseEntity.ok(currencyService.getExchangeRates(search))
    }

    @GetMapping("/types")
    fun getAllCurrencyTypes(): List<CurrencyType>{
        return currencyService.findAllCurrencyType()
    }

}