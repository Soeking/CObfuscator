package data

import kotlin.test.Test
import kotlin.test.assertTrue

class TokenTest {
    @Test
    fun tokenTest() {
        assertTrue(Token("abc").type == TokenType.VARIABLE)
        assertTrue(Token("2.9").type == TokenType.NUMBER)
    }
}