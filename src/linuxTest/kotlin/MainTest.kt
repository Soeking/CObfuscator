import platform.posix.fopen
import kotlin.test.Test
import kotlin.test.assertTrue

class MainTest {
    @Test
    fun testSplit() {
        val testFile = fopen("test.c", "r") ?: return
        val list = splitSpace(testFile)
        list.forEach {
            println(it)
        }
        assertTrue(true)
    }
}