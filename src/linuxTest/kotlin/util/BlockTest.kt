package util

import kotlin.test.Test
import kotlin.test.assertTrue

class BlockTest {
    @Test
    fun testCreateBlock() {
        println(createBlock(true))
        println(createBlock(true))
        assertTrue(createBlock(true).count { it == ";" } >= 2)
    }
}