import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

// static, only run once as we load the class
val words = File("./scrabble.txt")
    .readLines()
class AnagramTests {
    @Test
    fun `load words`() {
        assertEquals(
            178691,
            words.size
        )
    }
}