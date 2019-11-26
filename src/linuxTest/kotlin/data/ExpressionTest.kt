package data

import util.createListOfExp
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
        println(m.text)
        assertTrue(m.value == 26)
    }

    @Test
    fun testCreate() {
        var list = createListOfExp(0)
        println(list.size)
        list = createListOfExp(1)
        println(list.size)
        list = createListOfExp(2)
        println(list.size)
        list = createListOfExp(3)
        println(list.size)
        list = createListOfExp(4)
        println(list.size)
        list = createListOfExp(5)
        println(list.size)
        list = createListOfExp(6)
        println(list.size)
        list = createListOfExp(7)
        println(list.size)
        list = createListOfExp(8)
        println(list.size)
    }
}