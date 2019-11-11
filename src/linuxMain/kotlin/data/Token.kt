package data

class Token(token: String) {
    var type: TokenType

    init {
        type = initType()
    }

    private fun initType(): TokenType {
        return TokenType.VARIABLE
    }

    fun setType(type: TokenType) {
        this.type = type
    }
}