import kotlin.test.Test
import kotlin.test.assertTrue

class MainTest {
    @Test
    fun testName() {
        println(createName())
        println(createName())
        println(createName())
        assertTrue(createName().length in 10..20)
    }
}