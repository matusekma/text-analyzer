package hu.bme.aut.executor.feature.profanityfilter.service

interface ProfanityFilterService {

    fun filterText(unfilteredText: String): String

}