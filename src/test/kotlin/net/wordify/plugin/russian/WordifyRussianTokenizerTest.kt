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

    @Test
    fun `Test parsing words with illegal chars at start`() {
        val tokenizer = WordifyRussianTokenizer()
        val words = tokenizer.parse("Привет, 7ёж! -ты ТумаН!!")
                .asSequence()
                .toList()

        assertEquals(listOf("привет", "туман"), words)
    }
}
