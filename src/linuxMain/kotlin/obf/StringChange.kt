package obf

import data.Token
import data.TokenType
import util.*

fun stringCheck() {
    functionList.forEach {
        val list = mutableListOf<Int>()
        it.tokenList.filter { t -> t.type == TokenType.STRING }.forEach { t ->
            if (it.tokenList[it.tokenList.indexOf(t) - 2].token == "printf" &&
                it.tokenList[it.tokenList.indexOf(t) - 3].token == ";"
            )
                list.add(it.tokenList.indexOf(t))
        }
        changeString(functionList.indexOf(it), list.reversed())
    }
}

fun changeString(id: Int, list: List<Int>) {
    list.forEach {
        var text = functionList[id].tokenList[it].token
        val charArrayId = it - 2
        if (text.count { c -> c == '%' } == 0) {
            val toArray = text.drop(1).dropLast(1)
            text = "\"%s\""
            val v = createName()
            functionList[id].tokenList.add(it + 1, Token(v))
            functionList[id].tokenList.add(it + 1, Token(","))
            functionList[id].tokenList[it] = Token(text)
            createCharArray(v, toArray).reversed().forEach { s ->
                functionList[id].tokenList.add(charArrayId, Token(s))
            }
        }
    }
}

fun createCharArray(v: String, target: String): List<String> {
    val list = mutableListOf("char", v, "[", "]", "=", "{")
    var i = 0
    while (i < target.length) {
        if (target[i] != '\\')
            list.add("${target[i].toInt()}")
        else
            list.add(
                when (target[++i]) {
                    'n' -> "${'\n'.toInt()}"
                    't' -> "${'\t'.toInt()}"
                    '\\' -> "${'\\'.toInt()}"
                    '\"' -> "${'\"'.toInt()}"
                    '\'' -> "${'\''.toInt()}"
                    'r' -> "${'\r'.toInt()}"
                    'b' -> "${'\b'.toInt()}"
                    else -> "${'\n'.toInt()}"
                }
            )
        list.add(",")
        i++
    }
    list.dropLast(1)
    return list + "}" + ";"
}