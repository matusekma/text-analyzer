package hu.bme.aut.textanalyzer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TextanalyzerApplication

fun main(args: Array<String>) {
	runApplication<TextanalyzerApplication>(*args)
}
