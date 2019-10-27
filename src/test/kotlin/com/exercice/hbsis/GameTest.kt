package com.exercice.hbsis

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@SpringBootTest
class GameTest {
    @InjectMocks
    private lateinit var game: Game;
    @Test
    fun `should throw exception when invalid choise of players`(){
        val participants = mutableListOf(
                Competitor("Armando", "R"), Competitor("Dave", "N")
        )
        val exception = assertFailsWith<NoSuchStrategyException> { game.rps_game_winner(participants) }
        assertEquals(exception.message, "Invalid choise from players");
    }

    @Test
    fun `should throw exception when invalid number os players`(){
        val participants = mutableListOf(
                Competitor("Armando", "R"), Competitor("Dave", "p"), Competitor("Junior", "p")
        )
        val exception = assertFailsWith<WrongNumberOfPlayersException> { game.rps_game_winner(participants) }
        assertEquals(exception.message, "Number of players invalid");
    }

    @Test
    fun `should dave win the match`(){
        val participants = mutableListOf(
                Competitor("Armando", "R"), Competitor("Dave", "p")
        )
        Assertions.assertEquals("Competitor(name='Dave', choise='P')", game.rps_game_winner(participants).toString())
    }

    @Test
    fun `should throw exception when invalid number of players to tournament`() {
        val participants = mutableListOf(
                Competitor("Armando", "R")
        )
        val exception = assertFailsWith<WrongNumberOfPlayersException> { game.rps_game_winner(participants) }
        assertEquals(exception.message, "Number of players invalid");    }

    @Test
    fun `should richard win the tournament`(){
        val participants = mutableListOf(
                Competitor("Armando", "P"), Competitor("Dave", "S"),
                Competitor("Richard", "R"), Competitor("Michel", "S"),
                Competitor("Allen", "S"), Competitor("Omer", "P"),
                Competitor("David E.", "R"), Competitor("Richard X.", "P")
        )
        Assertions.assertEquals("Competitor(name='Richard', choise='R')", game.rps_tournament_winner(participants).toString())
    }
}