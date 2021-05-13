package hu.bme.aut.executor.feature.profanityfilter.service

import hu.bme.aut.executor.feature.profanityfilter.dto.ProfanityFilterRequest
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ProfanityFilterServiceImpl(
    private val restTemplate: RestTemplate,
) : ProfanityFilterService {

    @Value("\${application.services.profanityFilter.url}")
    lateinit var PROFANITY_FILTER_URL: String

    @CircuitBreaker(name = "profanityfilter", fallbackMethod = "fallbackFilterText")
    override fun filterText(unfilteredText: String): String {
        return restTemplate.postForObject(
            "$PROFANITY_FILTER_URL/filter",
            ProfanityFilterRequest(unfilteredText),
            String::class.java
        )
            ?: unfilteredText
    }

    private fun fallbackFilterText(unfilteredText: String, ex: Exception) = unfilteredText
}