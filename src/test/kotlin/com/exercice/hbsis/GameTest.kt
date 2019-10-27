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
        assertEquals("Invalid choise from players", exception.message);
    }

    @Test
    fun `should throw exception when invalid number os players`(){
        val participants = mutableListOf(
                Competitor("Armando", "R"), Competitor("Dave", "p"), Competitor("Junior", "p")
        )
        val exception = assertFailsWith<WrongNumberOfPlayersException> { game.rps_game_winner(participants) }
        assertEquals("Number of players invalid", exception.message);
    }

    @Test
    fun `should dave win the match`(){
        val participants = mutableListOf(
                Competitor("Armando", "R"), Competitor("Dave", "p")
        )
        Assertions.assertEquals(game.rps_game_winner(participants).toString(), "Competitor(name='Dave', choise='P')")
    }

    @Test
    fun `should throw exception when invalid number of players to tournament`() {
        val participants = mutableListOf(
                Competitor("Armando", "R")
        )
        val exception = assertFailsWith<WrongNumberOfPlayersException> { game.rps_game_winner(participants) }
        assertEquals("Number of players invalid", exception.message);    }

    @Test
    fun `should nick win the tournament`(){
        val participants = mutableListOf(
                Competitor("Armando", "R"), Competitor("Dave", "p"),
                Competitor("Junior", "p"), Competitor("Alex", "S"),
                Competitor("Klaus", "S"), Competitor("Rodrigo", "R"),
                Competitor("Elijah", "R"), Competitor("Morgana", "R"),
                Competitor("Nick", "P"), Competitor("Peter", "R"),
                Competitor("Megan", "R"), Competitor("Diana", "p")
        )
        Assertions.assertEquals(game.rps_tournament_winner(participants).toString(), "Competitor(name='Nick', choise='P')")
    }
}