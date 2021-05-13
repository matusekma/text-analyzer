package hu.bme.aut.executor.feature.profanityfilter.controller

import org.springframework.web.bind.annotation.GetMapping

interface ProfanityFilterApi {

    @GetMapping("/profanityfilter")
    fun filterText(unfilteredText: String): String
}