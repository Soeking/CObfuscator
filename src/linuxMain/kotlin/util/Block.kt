package util

import obf.*
import kotlin.random.Random

fun createBlock(b: Boolean): List<String> {
    val list = mutableListOf<String>()
    val varList = mutableListOf<String>()
    val rand = Random
    val size = rand.nextInt(3) + 2
    varList.add(createName())
    list.addAll(createLine(varList.first(), 0))
    while (varList.size <= size) {
        when (rand.nextInt(3)) {
            0 -> {
                varList.add(createName())
                list.addAll(createLine(varList.last(), 0))
            }
            in 1..2 -> if (b) list.addAll(createLine(varList.random(), (1..9).random()))
            else list.addAll(createLine(varList.random(), 1))
        }
    }
    return list
}

fun createLine(v: String, x: Int): List<String> {
    return when (x) {
        0 -> initLine(v)
        in 1..7 -> listOf(v, "=") + createListOfExp(arrayOf(0, 1, 2).random()) + ";"
        8 -> createMainText(v, 3)
        else -> createMainText(v)
    }
}

fun initLine(v: String): List<String> {
    val typeList = listOf("int", "double", "long", "float", "void", "char")
    var type = typeList.random()
    while (type == "void") type = typeList.random()
    return listOf(type, v, "=") + createListOfExp(arrayOf(0, 1, 2).random()) + ";"
}

fun createMainText(v: String = "arg${arrayOf(1, 2, 3).random()}", x: Int = (0..2).map { it }.random()): List<String> {
    val ifSen = when (x) {
        0 -> listOf("if", "(") + createListOfExp(0) + ")"
        1 -> listOf("if", "(") + createListOfExp(0) + "?" +
                createListOfExp(1) + ":" + createListOfExp(0) + ")"
        2 -> listOf("if", "(") + v + ">" + createListOfExp(0) + ")"
        else -> listOf()
    }
    return ifSen + listOf("main", "(") + createListOfExp(0) +
            "," + createListOfExp(0) +
            "," + createListOfExp(0) + listOf(")", ";")
}