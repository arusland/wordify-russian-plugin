package net.wordify.plugin.russian

import net.wordify.api.WordifyLemmatizer
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Files
import java.util.regex.Pattern

/**
 * TODO: Исправить кейсы типа когда "нам" лемматизируется в "мы"
 */
class WordifyRussianLemmatizer() : WordifyLemmatizer {
    private val log = LoggerFactory.getLogger(javaClass)

    private val PATTERN = Pattern.compile("[^а-яё\\d\\-]+")
    override fun getLang(): String = Consts.LANG_CODE

    override fun extract(tokens: Iterator<String>): Iterator<String> {
        val input = tokens.asSequence().joinToString("\n")
        val tempDir = Files.createTempDirectory("wrdf").toFile()

        try {
            val inputFile = File(tempDir, "input.txt")
            inputFile.writeText(input)

            log.debug("Write ${inputFile.length()} bytes into $inputFile")

            val output = "mystem -nl input.txt".runCommand(tempDir)

            return output.split("\n")
                    .map { getLemma(it) }
                    .filter { it.isNotBlank() }
                    .iterator()
        } finally {
            tempDir.deleteRecursively()
        }
    }

    private fun getLemma(line: String): String {
        val sl = PATTERN.split(line)

        return if (sl.isNotEmpty()) sl[0] else line
    }
}
