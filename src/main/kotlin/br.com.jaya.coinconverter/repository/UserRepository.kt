package br.com.jaya.coinconverter.repository

import br.com.jaya.coinconverter.repository.model.Users
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<Users, Long> {
    fun findByEmail(email: String?): Optional<Users>
}