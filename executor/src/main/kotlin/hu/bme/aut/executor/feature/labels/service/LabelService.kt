package hu.bme.aut.executor.feature.labels.service

import hu.bme.aut.executor.feature.labels.dto.CreateLabelRequest
import hu.bme.aut.executor.feature.labels.dto.CreateLabelResponse
import hu.bme.aut.executor.feature.labels.dto.LabelResponse

interface LabelService {

    fun getLabelsByUser(userId: Long): List<LabelResponse>

    fun createLabel(userId: Long, createLabelRequest: CreateLabelRequest): CreateLabelResponse

}