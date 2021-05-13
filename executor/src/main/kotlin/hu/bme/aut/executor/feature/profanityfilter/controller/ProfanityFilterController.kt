package hu.bme.aut.executor.feature.profanityfilter.controller

import hu.bme.aut.executor.feature.profanityfilter.service.ProfanityFilterService
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfanityFilterController(private val profanityFilterService: ProfanityFilterService) : ProfanityFilterApi {

    override fun filterText(unfilteredText: String): String {
        return profanityFilterService.filterText(unfilteredText)
    }
}