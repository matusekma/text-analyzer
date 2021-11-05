package hu.bme.aut.executor.feature.labels.controller

import hu.bme.aut.executor.feature.labels.dto.CreateLabelRequest
import hu.bme.aut.executor.feature.labels.dto.CreateLabelResponse
import hu.bme.aut.executor.feature.labels.dto.LabelResponse
import org.springframework.web.bind.annotation.*

@RequestMapping("/labels")
interface LabelApi {

    @GetMapping
    fun getLabels(@RequestHeader("userId") userId: Long): List<LabelResponse>

    @PostMapping
    fun createLabel(@RequestHeader("userId") userId: Long, @RequestBody createLabelRequest: CreateLabelRequest): CreateLabelResponse
}