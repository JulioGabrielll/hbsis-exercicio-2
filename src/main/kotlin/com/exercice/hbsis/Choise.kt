package com.exercice.hbsis

enum class Choise() {
    R(CONST.ROCK, CONST.SCISSORS),
    S(CONST.SCISSORS, CONST.PAPER),
    P(CONST.PAPER, CONST.ROCK);

    lateinit var description: String;
    lateinit var winFrom: String;

    constructor(description: String, winFrom: String) {
        this.description = description;
        this.winFrom = winFrom;
    }

    object CONST {
        const val ROCK: String = "ROCK";
        const val PAPER: String = "PAPER";
        const val SCISSORS: String = "SCISSORS";
    }
}