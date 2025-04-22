package br.com.jaya.coinconverter.repository.model

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "USERS_HIST_CURRCY_CONVRT")
class UsersHistoricalCurrencyConvert(

    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    var user: Users?,

    @Column(name="currency_origin")
    var currencyOrigin: String,

    @Column(name="currency_origin_value")
    var currencyOriginValue: BigDecimal,

    @Column(name="tax_conversion")
    var taxCorvesion: BigDecimal,

    @Column(name="operation_date_time")
    var operationDateTime: Date

    ) {
    constructor(): this(UUID.randomUUID(),null,"", BigDecimal.ZERO,BigDecimal.ZERO, Date())
}