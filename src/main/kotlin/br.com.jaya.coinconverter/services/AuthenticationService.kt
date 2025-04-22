package br.com.jaya.coinconverter.services

import br.com.jaya.coinconverter.exception.BussinessException
import br.com.jaya.coinconverter.exception.UserFoundedException
import br.com.jaya.coinconverter.model.LoginUserRequestDTO
import br.com.jaya.coinconverter.model.SignUpRequestDTO
import br.com.jaya.coinconverter.repository.UserRepository
import br.com.jaya.coinconverter.repository.model.Users
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

    fun signup(input: SignUpRequestDTO): Users {
        if(!isAccountExist(input.email)){
            throw UserFoundedException("User with email on request founded.")
        }
        val user = Users(
            email = input.email,
            hashedPassword = passwordEncoder.encode(input.password),
            name = input.name
        )
        return userRepository.save(user)
    }
    fun authenticate(login: LoginUserRequestDTO): UserDetails {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    login.email, login.password
                )
            )
            return userRepository.findByEmail(login.email)
                .orElseThrow { UsernameNotFoundException("Users not found") }
        }catch(ex: Exception){
            throw BussinessException("Username or password incorrect.")
        }
    }

    fun isAccountExist(username: String): Boolean{
       return userRepository.findByEmail(username).isEmpty;
    }
}