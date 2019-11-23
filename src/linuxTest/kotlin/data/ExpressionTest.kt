package data

import kotlin.test.Test
import kotlin.test.assertTrue

class ExpressionTest {
    @Test
    fun testExpression() {
        val five = Expression(null, null, null, 5)
        val eight = Expression(null, null, null, 8)
        val two = Expression(null, null, null, 2)
        val ul = Expression(five, eight, "+", 0)
        val m = Expression(ul, two, "*", 0)
        val t = Expression(m, two, "<<", 0)
        println(m.text)
        println(t.text)
        assertTrue(m.value == 26)
        assertTrue(t.value == 104)
    }
}