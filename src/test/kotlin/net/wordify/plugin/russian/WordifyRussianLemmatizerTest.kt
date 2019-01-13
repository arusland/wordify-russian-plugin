package net.wordify.plugin.russian

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class WordifyRussianLemmatizerTest {
    @Test
    fun `Lemmatizer simple test`() {
        val lemmatizer = WordifyRussianLemmatizer()

        val result = lemmatizer.extract(listOf("сделал", "козе", "баян").iterator())

        result.asSequence().forEach { println(it) }
    }

    @Test
    fun `Lemmatizer sample test`() {
        val tokenizer = WordifyRussianTokenizer()
        val lemmatizer = WordifyRussianLemmatizer()

        val tokens = tokenizer.parse(sample1)
        val lemmas = lemmatizer.extract(tokens)
                .asSequence()
                .toList()
        lemmas.forEach {
            assertTrue { it.isNotBlank() }
            println(it)
        }

        println("Found ${lemmas.size} lemmas")
    }

    companion object {
        const val sample1 = """Палашка принесла нам наши шпаги. Мы вышли от коменданта по-видимому примиренные. Иван Игнатьич нас сопровождал. «Как вам не стыдно было, – сказал я ему сердито, – доносить на нас коменданту после того, как дали мне слово того не делать!» – «Как бог свят, я Ивану Кузмичу того не говорил, – ответил он, – Василиса Егоровна выведала все от меня. Она всем и распорядилась без ведома коменданта. Впрочем, слава богу, что все так кончилось». С этим словом он повернул домой, а Швабрин и я остались наедине. «Наше дело этим кончиться не может», – сказал я ему. «Конечно, – отвечал Швабрин, – вы своею кровью будете отвечать мне за вашу дерзость; но за нами, вероятно, станут присматривать. Несколько дней нам должно будет притворяться. До свидания!» И мы расстались, как ни в чем не бывали. Возвратясь к коменданту, я, по обыкновению своему, подсел к Марье Ивановне. Ивана Кузмича не было дома; Василиса Егоровна занята была хозяйством. Мы разговаривали вполголоса. Марья Ивановна с нежностию выговаривала мне за беспокойство, причиненное всем моею ссорою с Швабриным. «Я так и обмерла, – сказала она, – когда сказали нам, что вы намерены биться на шпагах."""
    }
}
