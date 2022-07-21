package com.techtalk.bulkperations.repository

import com.mongodb.bulk.BulkWriteResult
import com.techtalk.bulkperations.entity.Game
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.BulkOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.util.Pair
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
class MongoRepository(
    private val mongoTemplate: MongoTemplate,
) {
    private val logger: Logger = LoggerFactory.getLogger(MongoRepository::class.java)
    val bulkOps: BulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, Game::class.java)

    fun save(games: List<Game>): String {
        bulkOps.insert(games)
        val returnBulkOps = bulkOps.execute()
        logger.info(returnBulkOps.toString())
        return formatBulkWriteResult(returnBulkOps)
    }

    fun save(games: Game): String {
        bulkOps.insert(games)
        val returnBulkOps = bulkOps.execute()
        logger.info(returnBulkOps.toString())
        return formatBulkWriteResult(returnBulkOps)
    }


    fun findById(id: String): Game? {
        return mongoTemplate.findOne(
            Query()
                .addCriteria(Game::id isEqualTo id), Game::class.java
        )
    }

    fun deleteById(id: String): String {
        bulkOps.remove(Query().addCriteria(Game::id isEqualTo id))

        val returnBulkOps = bulkOps.execute()
        logger.info(returnBulkOps.toString())
        return formatBulkWriteResult(returnBulkOps)
    }

    fun updateAllGames(): String {
        val pairs: List<Pair<Query, Update>> = listOf(
            Pair.of(
                Query()
                    .addCriteria(Game::anoLancamento isEqualTo 2000),

                Update()
                    .set("anoLancamento", 2003)
                    .set("horasJogadas", "10")
            )
        )
        bulkOps.updateMulti(pairs)
        val returnBulkOps = bulkOps.execute()
        logger.info(returnBulkOps.toString())
        return formatBulkWriteResult(returnBulkOps)
    }

    fun updateOne(game: Game): String {
        bulkOps.updateOne(
            Query()
                .addCriteria(Game::id isEqualTo game.id),
            Update()
                .set("nome", game.nome)
                .set("valor", game.valor)
                .set("anoLancamento", 2003)
                .set("dataCompra", game.dataCompra)
                .set("horasJogadas", game.horasJogadas)
                .set("horasJogadas", "10")
        )
        val returnBulkOps = bulkOps.execute()
        logger.info(returnBulkOps.toString())
        return formatBulkWriteResult(returnBulkOps)
    }

    fun formatBulkWriteResult(bulkWriteResult: BulkWriteResult): String {
        return "{insertedCount=${bulkWriteResult.insertedCount}, matchedCount=${bulkWriteResult.matchedCount}, removedCount= ${bulkWriteResult.deletedCount}, modifiedCount= ${bulkWriteResult.modifiedCount}}"
    }

}
