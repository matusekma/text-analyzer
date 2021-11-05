package hu.bme.aut.executor.feature.labels.service

import hu.bme.aut.executor.domain.Label
import hu.bme.aut.executor.exception.EntityAlreadyExistsException
import hu.bme.aut.executor.feature.labels.dto.*
import hu.bme.aut.executor.repository.LabelRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LabelServiceImpl(private val labelRepository: LabelRepository) : LabelService {

    override fun getLabelsByUser(userId: Long): List<LabelResponse> =
            labelRepository.findAllByUserId(userId)
                    .map(Label::toLabelResponse)

    @Transactional
    override fun createLabel(userId: Long, createLabelRequest: CreateLabelRequest): CreateLabelResponse {
        val existingLabel = labelRepository.findByName(createLabelRequest.name)
        if (existingLabel != null) {
            throw EntityAlreadyExistsException("Label already exists!")
        }
        val label = createLabelRequest.toLabel(userId)
        return labelRepository.save(label)
                .let(Label::toCreateLabelResponse)
    }

}