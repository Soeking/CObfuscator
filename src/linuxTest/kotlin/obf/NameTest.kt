package obf

import util.alphabets
import kotlin.test.Test
import kotlin.test.assertTrue

class NameTest {
    @Test
    fun testName() {
        println(createName())
        println(createName())
        println(createName())
        assertTrue(createName().length in 10..20)
        assertTrue(alphabets.contains(createName().first()))
    }
}