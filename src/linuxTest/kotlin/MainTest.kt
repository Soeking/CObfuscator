import platform.posix.fopen
import kotlin.test.Test
import kotlin.test.assertTrue

class MainTest {
    @Test
    fun testName() {
        println(createName())
        println(createName())
        println(createName())
        assertTrue(createName().length in 10..20)
        assertTrue(alphabets.contains(createName().first()))
    }

    @Test
    fun testSplit() {
        val testFile = fopen("test.c", "r") ?: return
        val testOut = fopen("out.c", "w") ?: return
        val list = splitToken(testFile, testOut)
        println(list)
        assertTrue(true)
    }
}