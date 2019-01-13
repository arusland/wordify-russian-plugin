package net.wordify.plugin.russian

import net.wordify.api.WordifyLemmatizer
import java.io.File
import java.util.regex.Pattern

/**
 * TODO: Исправить кейсы типа когда "нам" лемматизируется в "мы"
 */
class WordifyRussianLemmatizer() : WordifyLemmatizer {
    private val PATTERN = Pattern.compile("[^а-яё\\d\\-]+")
    override fun getLang(): String = Consts.LANG_CODE

    override fun extract(tokens: Iterator<String>): Iterator<String> {
        val input = tokens.asSequence().joinToString("\n")
        val output = "mystem -nl".runCommand(input, File("."))

        return output.split("\n")
                .map { getLemma(it) }
                .filter { it.isNotBlank() }
                .iterator()
    }

    private fun getLemma(line: String): String {
        val sl = PATTERN.split(line)

        return if (sl.isNotEmpty()) sl[0] else line
    }
}
