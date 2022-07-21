package com.techtalk.bulkperations.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document("game")
data class Game(
    @Id
    val id: String,
    val nome: String,
    val anoLancamento: Int,
    val valor: BigDecimal,
    val dataCompra: String,
    val horasJogadas: String
) {
    companion object  {
        @JvmStatic
        fun toDomain(message: Game) = message.toString()
    }
}