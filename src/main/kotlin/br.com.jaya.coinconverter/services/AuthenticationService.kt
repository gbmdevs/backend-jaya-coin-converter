package br.com.jaya.coinconverter.services

import br.com.jaya.coinconverter.model.LoginUserRequestDTO
import br.com.jaya.coinconverter.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder
) {

    fun authenticate(login: LoginUserRequestDTO): UserDetails {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                login.email, login.password
            )
        )
        return userRepository.findByEmail(login.email)
            .orElseThrow{ UsernameNotFoundException("Users not found") }
    }
}