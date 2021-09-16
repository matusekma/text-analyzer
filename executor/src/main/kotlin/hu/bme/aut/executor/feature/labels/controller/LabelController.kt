package hu.bme.aut.executor.feature.labels.controller

import hu.bme.aut.executor.feature.labels.dto.CreateLabelRequest
import hu.bme.aut.executor.feature.labels.dto.CreateLabelResponse
import hu.bme.aut.executor.feature.labels.dto.LabelResponse
import hu.bme.aut.executor.feature.labels.service.LabelService
import org.springframework.web.bind.annotation.RestController

@RestController
class LabelController(private val labelService: LabelService) : LabelApi {

    override fun getLabels(): List<LabelResponse> = labelService.getLabels()

    override fun createLabel(createLabelRequest: CreateLabelRequest): CreateLabelResponse =
            labelService.createLabel(createLabelRequest)

}