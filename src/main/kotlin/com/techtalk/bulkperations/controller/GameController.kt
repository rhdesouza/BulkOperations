package com.techtalk.bulkperations.controller

import com.techtalk.bulkperations.entity.Game
import com.techtalk.bulkperations.service.GameService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/game")
class GameController(
    val op: GameService,
) {

    @PostMapping("/qtd/{qtd}")
    @ResponseBody
    fun saveGame(
        @PathVariable(name = "qtd") qtd: Int,
    ): String {
        return op.saveGameList(qtd)
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getGame(
        @PathVariable(name = "id") id: String,
    ): Game? {
        return op.findById(id)
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun deleteGame(
        @PathVariable(name = "id") id: String,
    ): String {
        return op.deleteById(id)
    }

    @PostMapping("/updateAll")
    @ResponseBody
    fun updateAllGames(): String {
        return op.updateAll()
    }

    @PostMapping("/update")
    @ResponseBody
    fun updateOneGames(
        @RequestBody(required = false) game: Game,
    ): String {
        return op.updateOne(game)
    }

}