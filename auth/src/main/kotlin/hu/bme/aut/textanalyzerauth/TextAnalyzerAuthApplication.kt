package hu.bme.aut.textanalyzerauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TextAnalyzerAuthApplication

fun main(args: Array<String>) {
	runApplication<TextAnalyzerAuthApplication>(*args)
}
