package net.wordify.plugin.russian

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WordifyRussianTokenizerTest {
    @Test
    fun `Test simple sentence`() {
        val tokenizer = WordifyRussianTokenizer()
        val words = tokenizer.parse("Привет, ёж! Как ты докатился до такого состояния? Ёжжжжж, ё!")
                .asSequence()
                .toList()

        assertEquals(words, listOf("привет", "ёж", "как", "ты", "докатился", "до", "такого", "состояния", "ёжжжжж"))
    }
}
