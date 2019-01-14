package net.wordify.plugin.russian

import org.slf4j.LoggerFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

fun String.runCommand(workingDir: File,
                      timeout: Long = 60,
                      timeUnit: TimeUnit = TimeUnit.SECONDS): String {
    try {
        val log = LoggerFactory.getLogger(WordifyRussianPlugin::class.java)

        log.debug("run command: {}", this)
        val parts = this.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
                .directory(workingDir)
                .start()

        val output = proc.inputStream.bufferedReader().readText()

        proc.waitFor(timeout, timeUnit)

        return output
    } catch (e: IOException) {
        throw RuntimeException(e)
    }
}
