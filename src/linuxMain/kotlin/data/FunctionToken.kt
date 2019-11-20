package data

import obf.*
import util.*

class FunctionToken(tokens: MutableList<String>) {
    val tokenList = mutableListOf<Token>()
    var isFunction = false
    val name:String
    val varName = mutableMapOf<String, String>()

    init {
        tokens.forEach {
            tokenList.add(Token(it))
        }

        if (tokens.first() == "typedef") typeList.add(tokens[tokens.size - 2])
        if (tokens.count { it == "{" } >= 1 && (tokens.indexOf("{") < tokens.indexOf("struct")
                    || (!tokens.contains("struct") && tokens.contains("{"))))
            isFunction = true
        for (i in tokenList.indices) {
            if (tokenList[i].type == TokenType.VARIABLE && i != tokenList.size - 1 && tokenList[i + 1].token == "(")
                tokenList[i].type = TokenType.FUNCTION
        }
        name = when {
            this.isFunction -> tokens[tokenList.indexOfFirst { it.type == TokenType.FUNCTION }]
            tokens.first() != "typedef" -> tokens[tokenList.indexOfFirst { it.type == TokenType.TYPE } + 1]
            else -> ""
        }
    }

    fun changeVar() {
        tokenList.forEach {
            if (it.type == TokenType.VARIABLE && tokenList[tokenList.indexOf(it) - 1].type == TokenType.TYPE) {
                var randName = createName()
                while (varName.containsValue(randName)) randName = createName()
                varName[it.token] = randName
            }
        }
        tokenList.forEach {
            if (it.type == TokenType.VARIABLE && varName.containsKey(it.token))
                it.token = varName[it.token] ?: it.token
        }
    }
}