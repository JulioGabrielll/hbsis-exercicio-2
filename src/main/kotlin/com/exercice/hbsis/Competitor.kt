package com.exercice.hbsis

class Competitor (var name: String, choise: String) {
    val choise: String = choise.toUpperCase();
    override fun toString(): String {
        return "Competitor(name='$name', choise='$choise')"
    }
}