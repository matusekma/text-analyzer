package hu.bme.aut.executor.feature.labels.service

import hu.bme.aut.executor.feature.labels.dto.CreateLabelRequest
import hu.bme.aut.executor.feature.labels.dto.CreateLabelResponse
import hu.bme.aut.executor.feature.labels.dto.LabelMapper
import hu.bme.aut.executor.feature.labels.dto.LabelResponse
import hu.bme.aut.executor.repository.LabelRepository
import org.springframework.stereotype.Service

@Service
class LabelServiceImpl(val labelRepository: LabelRepository) : LabelService {

    override fun getLabels(): List<LabelResponse> =
            labelRepository.findAll()
                    .map(LabelMapper.Companion::toLabelResponse)


    override fun createLabel(createLabelRequest: CreateLabelRequest): CreateLabelResponse {
        val userId = 1L // TODO get userid
        val label = LabelMapper.toLabel(userId, createLabelRequest)
        return labelRepository.save(label)
                .let(LabelMapper.Companion::toCreateLabelResponse)
    }

}