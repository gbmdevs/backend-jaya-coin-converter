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
@Table(name = "CURRCY_TYPE")
class CurrencyType(

    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    val id: UUID = UUID.randomUUID(),

    @Column(name="currency")
    val currency: String,

    @Column(name="country")
    val country: String,
    @Column(name="symbol")
    val symbol: String

) {
    constructor() : this(UUID.randomUUID(), "", "","")
}