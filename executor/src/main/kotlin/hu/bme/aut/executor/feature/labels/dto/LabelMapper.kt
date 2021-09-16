package hu.bme.aut.executor.feature.labels.dto

import hu.bme.aut.executor.domain.Label

class LabelMapper {

    companion object {
        fun toLabel(userId: Long, createLabelRequest: CreateLabelRequest) =
                Label(userId = userId, name = createLabelRequest.name)

        fun toCreateLabelResponse(label: Label) =
                CreateLabelResponse(id = label.id!!, userId = label.userId, name = label.name)

        fun toLabelResponse(label: Label) =
                LabelResponse(id = label.id!!, userId = label.userId, name = label.name)
    }
}