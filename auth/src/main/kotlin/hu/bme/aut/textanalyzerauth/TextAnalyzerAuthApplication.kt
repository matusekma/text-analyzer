package hu.bme.aut.textanalyzerauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class TextAnalyzerAuthApplication

fun main(args: Array<String>) {
    runApplication<TextAnalyzerAuthApplication>(*args)
}
