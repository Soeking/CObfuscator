package data

import kotlin.test.Test
import kotlin.test.assertEquals

class TokenTest {
    @Test
    fun tokenTest() {
        assertEquals(Token("abc").type, TokenType.VARIABLE)
        assertEquals(Token("2.9").type, TokenType.NUMBER)
        assertEquals(Token("while").type, TokenType.CONTROL)
        assertEquals(Token(";").type, TokenType.BRACKET)
    }
}