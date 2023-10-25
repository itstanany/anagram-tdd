import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

// static, only run once as we load the class
val words = File("./scrabble.txt")
    .readLines()
    .plus(
        listOf(
            "A",
            "O",
            "I",
        )
    )
    .sorted()

class AnagramTests {
    @Test
    fun `load words`() {
        assertEquals(
            178694,
            words.size
        )
    }

    // multi-word anagram
    // filter potential words that can be made from the subject
    // filter multi-word anagram that mirror the subject
    
    @Test
    fun `can be made from the letters in`() {

        assertTrue(
            "A"
                .couldBeMadeFromLettersIn(
                    "A CAT"
                )
        )


        assertTrue(
            "AA"
                .couldBeMadeFromLettersIn(
                    "A CAT"
                )
        )
        assertTrue(
            "ACT"
                .couldBeMadeFromLettersIn(
                    "A CAT"
                )
        )
        assertTrue(
            "CAT"
                .couldBeMadeFromLettersIn(
                    "A CAT"
                )
        )


        assertFalse(
            "H"
                .couldBeMadeFromLettersIn(
                    "A CAT"
                )
        )
        assertFalse(
            "AAH"
                .couldBeMadeFromLettersIn(
                    "A CAT"
                )
        )
        assertFalse(
            "TAT"
                .couldBeMadeFromLettersIn(
                    "A CAT"
                )
        )

        // boundary conditions
        assertTrue(
            ""
                .couldBeMadeFromLettersIn(
                    "A CAT"
                )
        )
        assertTrue(
            ""
                .couldBeMadeFromLettersIn(
                    ""
                )
        )
    }

    @Test
    fun anagramsFor() {
        val input = "A CAT"
        assertEquals(
            setOf(
                "A ACT",
                "A CAT",
                "ACTA",
            ),
            words.anagramsFor(input),
        )
    }
}

fun List<String>.anagramsFor(input: String): Set<String> {
    val result: MutableList<String> = mutableListOf<String>()
    process(
        input,
        this,
        { result.add(it) },
    )
    val deDuplicated: Set<String> = result.map { it.split(" ").sorted().joinToString(" ") }.toSet()
    return deDuplicated
}

private fun process(
    input: String,
    words: List<String>,
    collector: (String) -> Unit,
    prefix: String = ""
    ) {
    // get all candidates
    val _words = words.filter {
        it.couldBeMadeFromLettersIn(input)
    }
//        println("_words ${_words.toString()}")
            _words
        .forEach { word ->
            val remainingLetters = input.minusLettersIn(word)
            if (remainingLetters.isNotBlank()) {
//                println("word: $word,, remaining letters: [$remainingLetters]")
//                println("$prefix $word [$remainingLetters]")

                process(
                    remainingLetters,
                    words,
                    collector,
                    prefix = "  $word",
                    )
            } else {
                collector("$prefix $word".trim())
//                println("$prefix $word")
//                println("word--no-remaining-letters: $word")

                /* else {
                                               println("word--no-remaining-letters: $word")
                                           }*/
            }
        }
}

private fun String.minusLettersIn(word: String): String {
    val lettersList = this.toMutableList()
    word.forEach { char ->
        if (!lettersList.remove(char)) error("BAD")
    }
    return String(lettersList.toCharArray())
}

private fun String.couldBeMadeFromLettersIn(letters: String): Boolean {
    val lettersList = letters.toMutableList()
// test presence of letters by looping
//    removing chars guarantee testing letter repetition
    this.forEach { char ->
        if(!lettersList.remove(char)) return false
    }
    return true

}
