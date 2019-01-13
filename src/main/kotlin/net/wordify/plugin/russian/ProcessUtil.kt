package net.wordify.plugin.russian

import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

fun String.runCommand(input: String,
                      workingDir: File,
                      timeout: Long = 60,
                      timeUnit: TimeUnit = TimeUnit.SECONDS): String {
    try {
        val parts = this.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
                .directory(workingDir)
                .start()
        val stdin = proc.outputStream.bufferedWriter()

        stdin.write(input)
        stdin.flush()
        stdin.close()

        proc.waitFor(timeout, timeUnit)
        return proc.inputStream.bufferedReader().readText()
    } catch (e: IOException) {
        throw RuntimeException(e)
    }
}
