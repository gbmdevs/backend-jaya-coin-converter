package br.com.jaya.coinconverter.repository.model

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
@Table(name = "users_hist_currcy_convrt")
class UsersHistoricalCurrencyConvert(

    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JdbcTypeCode(SqlTypes.CHAR)
    var user: Users?,

    @Column(name="operation_date_time")
    var operationDateTime: Date

    ) {
    constructor(): this(UUID.randomUUID(),null, Date())
}