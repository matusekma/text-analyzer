package hu.bme.aut.executor.feature.nlp.profanityfilter.service

interface ProfanityFilterService {

    fun filterText(unfilteredText: String): String

}