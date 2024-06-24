package com.example.calculator.presentation

enum class Action(val description: String) {
    DIVIDE("/"),
    MULTIPLI("*"),
    MINUS("-"),
    PLUS("+");

    fun getAction() = this.description
}