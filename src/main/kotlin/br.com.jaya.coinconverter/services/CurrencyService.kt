package br.com.jaya.coinconverter.services

import br.com.jaya.coinconverter.exception.UserFoundedException
import br.com.jaya.coinconverter.model.LoginUserRequestDTO
import br.com.jaya.coinconverter.model.SignUpRequestDTO
import br.com.jaya.coinconverter.repository.CurrencyTypeRepository
import br.com.jaya.coinconverter.repository.UserRepository
import br.com.jaya.coinconverter.repository.model.CurrencyType
import br.com.jaya.coinconverter.repository.model.Users
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CurrencyService(
    private val currencyTypeRepository: CurrencyTypeRepository
) {
    fun findAllCurrencyType(): List<CurrencyType>{
      return currencyTypeRepository.findAll().toList()
    }
}