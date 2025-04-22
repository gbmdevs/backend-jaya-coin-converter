package br.com.jaya.coinconverter.repository.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
@Table(name = "USERS")
class Users(
    @JsonIgnore
    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "email")
    val email: String,

    @JsonIgnore
    @Column(name = "password")
    val hashedPassword: String,

    @Column(name = "name")
    val name: String

) : UserDetails {

    constructor() : this(UUID.randomUUID(), "", "", "")

    override fun getUsername(): String {
        return email
    }

    @JsonIgnore
    override fun getPassword(): String {
        return hashedPassword
    }

    @JsonIgnore
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return emptyList() // Retorna uma lista vazia, adaptável conforme necessidade
    }

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun toString(): String {
        return "Users = [ ID = $id, Email = $email, Nome = $username, password = $password ]"
    }
}