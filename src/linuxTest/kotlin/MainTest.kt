import platform.posix.fopen
import util.*
import kotlin.test.Test
import kotlin.test.assertTrue

class MainTest {
    @Test
    fun testSplit() {
        val testFile = fopen("test.c", "r") ?: return
        val testOut = fopen("out.c", "w") ?: return
        val list = splitSpace(testFile, testOut)
        list.forEach {
            println(it)
        }
        assertTrue(true)
    }
}