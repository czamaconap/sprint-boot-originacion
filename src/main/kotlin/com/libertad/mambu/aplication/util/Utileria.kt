package com.libertad.mambu.aplication.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlin.random.Random

fun generateRandom10DigitsString(): String {
    val randomNumber = Random.nextInt(0, 1_000_000_000)
    return String.format("%010d", randomNumber)
}

fun generateRandom13DigitsString(): String {
    val randomNumber = Random.nextLong(0L, 1_000_000_000_000L)
    return "N${String.format("%012d", randomNumber)}"
}

fun generateRandom16DigitsString(): String {
    val randomNumber = Random.nextLong(0L, 100_000_000_000_000L)
    return "AD${String.format("%014d", randomNumber)}"
}


fun prettyPrint(obj: Any): String {
    val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    return gson.toJson(obj)
}