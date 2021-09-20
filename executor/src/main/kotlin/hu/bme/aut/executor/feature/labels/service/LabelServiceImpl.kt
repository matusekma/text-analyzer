package hu.bme.aut.executor.feature.labels.service

import hu.bme.aut.executor.domain.Label
import hu.bme.aut.executor.feature.labels.dto.*
import hu.bme.aut.executor.repository.LabelRepository
import org.springframework.stereotype.Service

@Service
class LabelServiceImpl(private val labelRepository: LabelRepository) : LabelService {

    override fun getLabels(): List<LabelResponse> =
            labelRepository.findAll()
                    .map(Label::toLabelResponse)


    override fun createLabel(userId: Long, createLabelRequest: CreateLabelRequest): CreateLabelResponse {
        val label = createLabelRequest.toLabel(userId)
        return labelRepository.save(label)
                .let(Label::toCreateLabelResponse)
    }

}