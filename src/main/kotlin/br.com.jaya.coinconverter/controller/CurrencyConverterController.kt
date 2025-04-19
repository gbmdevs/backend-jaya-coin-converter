package br.com.jaya.coinconverter.controller

import br.com.jaya.coinconverter.repository.model.CurrencyType
import br.com.jaya.coinconverter.services.CurrencyService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("currency")
class CurrencyConverterController(
    private val currencyService: CurrencyService
) {

    @GetMapping("/search")
    fun serachForCoin(){

    }

    @GetMapping("/types")
    fun getAllCurrencyTypes(): List<CurrencyType>{
        return currencyService.findAllCurrencyType()
    }

}