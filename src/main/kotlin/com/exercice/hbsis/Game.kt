package com.exercice.hbsis
import org.springframework.stereotype.Component

@Component
class Game {

    fun rps_game_winner(participants: List<Competitor>) : Competitor {
        if(participants.size != 2) {
            throw WrongNumberOfPlayersException("Number of players invalid");
        }
        try {
            val firstCompetidor = participants.first();
            val firstCompetidorChoise = Choise.valueOf(firstCompetidor.choise);
            val secondCompetidor = participants.last();
            val secondCompetidorChoise = Choise.valueOf(secondCompetidor.choise);

            if (firstCompetidorChoise.equals(secondCompetidorChoise)) return firstCompetidor;

            if (firstCompetidorChoise.winFrom.equals(secondCompetidorChoise.description)) {
                return firstCompetidor;
            }
            return secondCompetidor;

        } catch (e: Exception) {
            throw NoSuchStrategyException("Invalid choise from players");
        }
    }

    fun rps_tournament_winner(participants: MutableList<Competitor>, nextFase: MutableList<Competitor> = mutableListOf(), matchStart: Boolean = false) : Competitor? {
        if (participants.isEmpty() && nextFase.size == 1) {
            return nextFase.get(nextFase.lastIndex);
        }
        if ((participants.isEmpty() && nextFase.isEmpty()) || (participants.size.rem(2) != 0 && !matchStart)) {
            throw WrongNumberOfPlayersException("Number of players invalid");
        }
        val firstCompetidor = participants.first();
        participants.remove(firstCompetidor)
        if (participants.isEmpty()) {
            nextFase.add(firstCompetidor);
        } else {
            val secondCompetidor = participants.first();
            participants.remove(secondCompetidor)
            nextFase.add(rps_game_winner(listOf(firstCompetidor, secondCompetidor)));
        }
        return if (participants.isEmpty() && nextFase.size != 1) rps_tournament_winner(nextFase, participants, true); else rps_tournament_winner(participants, nextFase, true);
    }
}