package hu.bme.aut.executor.feature.labels.controller

import hu.bme.aut.executor.feature.labels.dto.CreateLabelRequest
import hu.bme.aut.executor.feature.labels.dto.CreateLabelResponse
import hu.bme.aut.executor.feature.labels.dto.LabelResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/labels")
interface LabelApi {

    @GetMapping
    fun getLabels(): List<LabelResponse>

    @PostMapping
    fun createLabel(@RequestBody createLabelRequest: CreateLabelRequest): CreateLabelResponse
}