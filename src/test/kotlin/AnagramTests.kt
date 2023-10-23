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

    // playing with a single word
    @Test
    fun `anagrms of cat`() {
        val input = "CAT"
        val anagrams = words
            .filter {
                it.length == 3
            }
            .filter {
                it.hasSameLettersAs(input)
            }

        assertEquals(
            listOf(
                "ACT",
                "CAT",
            ),
            anagrams,
        )
    }
}

private fun String.hasSameLettersAs(
    other: String,
): Boolean {
    return this
        .toCharArray()
        .sortedArray()
        .contentEquals(
            other
            .toCharArray()
            .sortedArray()
        )

}
