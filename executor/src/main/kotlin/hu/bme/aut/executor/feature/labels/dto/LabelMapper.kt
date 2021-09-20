package hu.bme.aut.executor.feature.labels.dto

import hu.bme.aut.executor.domain.Label

fun CreateLabelRequest.toLabel(userId: Long) =
        Label(userId = userId, name = name)

fun Label.toCreateLabelResponse() =
        CreateLabelResponse(id = id!!, userId = userId, name = name)

fun Label.toLabelResponse() =
        LabelResponse(id = id!!, userId = userId, name = name)
