package com.techtalk.bulkperations.service

import com.techtalk.bulkperations.entity.Game
import com.techtalk.bulkperations.repository.MongoRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class GameService(
    val repository: MongoRepository,
) {
    fun saveGameList(qtd: Int = 15000): String {
        var games: MutableList<Game> = ArrayList()
        (1..qtd).forEach {
            games.add(
                Game(it.toString(), "111", 2000, BigDecimal(500), "1151", it.toString())
            )
        }
        return repository.save(games)
    }

    fun findById(id: String): Game? {
        return repository.findById(id)
    }

    fun deleteById(id: String): String {
        return repository.deleteById(id)
    }

    fun updateAll(): String {
        return repository.updateAllGames()
    }

    fun updateOne(game: Game): String {
        return repository.updateOne(game)
    }

}