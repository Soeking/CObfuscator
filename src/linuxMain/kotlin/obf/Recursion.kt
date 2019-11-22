package obf

import data.TokenType
import util.functionList

fun recursiveMain() {
    val functionToken = functionList.find { it.name == "main" } ?: return
    if (functionToken.tokenList[functionToken.tokenList.indexOfFirst { it.type == TokenType.BRACKET } + 1].type == TokenType.BRACKET ||
        functionToken.tokenList[functionToken.tokenList.indexOfFirst { it.type == TokenType.BRACKET } + 1].token == "void"
    ) {
        functionList.find { it.name == "main" }?.recursive()
    }
}