package net.wordify.plugin.russian

import net.wordify.api.WordifyFeature
import net.wordify.api.WordifyLemmatizer
import net.wordify.api.WordifyPlugin
import net.wordify.api.WordifyTokenizer
import java.lang.RuntimeException

class WordifyRussianPlugin : WordifyPlugin {
    override fun getName(): String = "wordify-russian-plugin"

    override fun getDescription(): String = "Add supporting of Russian language into Wordify"

    override fun <T : WordifyFeature> hasSupport(lang: String, feature: Class<T>): Boolean {
        if (lang.isBlank()) {
            throw IllegalArgumentException("lang cannot be blank")
        }

        return lang == Consts.LANG_CODE &&
                (feature == WordifyTokenizer::class.java || feature == WordifyLemmatizer::class.java)
    }

    override fun <T : WordifyFeature> getFeature(lang: String, feature: Class<T>): T? {
        if (!hasSupport(lang, feature)) {
            return null
        }

        if (feature == WordifyTokenizer::class.java) {
            return WordifyRussianTokenizer() as T
        }

        if (feature == WordifyLemmatizer::class.java) {
            return WordifyRussianLemmatizer() as T
        }

        throw RuntimeException("Unsupported feature '${feature.name}' for language '$lang'")
    }
}
