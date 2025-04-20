package br.com.jaya.coinconverter.services

import br.com.jaya.coinconverter.repository.UserRepository
import br.com.jaya.coinconverter.repository.model.Users
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun findUserByEmailAuth(): Users? {
        val authentication  = SecurityContextHolder.getContext().authentication
        if(authentication != null ) {
            val userDetails = authentication.principal as UserDetails
            return userRepository.findByEmail(userDetails.username).
            orElseThrow{ UsernameNotFoundException("User not found on database.") }
        }
        return null
    }

}
