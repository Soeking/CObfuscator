package data

import util.*

class Token(token: String) {
    var type: TokenType

    init {
        type = initType(token)
    }

    private fun initType(token: String): TokenType {
        if (token.length == 1 && !nameChars.contains(token.first()))
            return TokenType.OPTION
        if (typeList.contains(token)) return TokenType.TYPE
        return TokenType.VARIABLE
    }

    fun setType(type: TokenType) {
        this.type = type
    }
}