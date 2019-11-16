package data

import util.*

class FunctionToken(tokens: MutableList<String>) {
    val tokenList = mutableListOf<Token>()
    var isFunction = false
    var name = ""

    init {
        tokens.forEach {
            tokenList.add(Token(it))
        }

        if (tokens.first() == "typedef") typeList.add(tokens[tokens.size - 2])
        name = tokens[1]
        if (tokens.count { it == "{" } >= 1 && tokens.indexOf("{") < tokens.indexOf("struct"))
            isFunction = true
    }
}