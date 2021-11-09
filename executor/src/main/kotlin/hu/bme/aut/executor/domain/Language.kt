package hu.bme.aut.executor.domain

enum class Language {
    EN, DE
}

fun Language.toLanguageString() =
    if (this == Language.DE) "german" else "english"
