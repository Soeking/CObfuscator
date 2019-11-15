package data

import util.*

class Token(token: String) {
    var type: TokenType

    init {
        type = initType(token)
    }

    private fun initType(token: String): TokenType {
        if (token.length == 1) {
            if (bracketList.contains(token.first()))
                return TokenType.BRACKET
            if (!nameChars.contains(token.first()))
                return TokenType.OPTION
        }
        if (typeList.contains(token)) return TokenType.TYPE
        if (numbers.contains(token.first())) return TokenType.NUMBER
        if (token.count { it == '"' } >= 2 && token.first() == '"' && token.last() == '"')
            return TokenType.STRING
        if (controlList.contains(token)) return TokenType.CONTROL
        return TokenType.VARIABLE
    }

    fun setType(type: TokenType) {
        this.type = type
    }
}