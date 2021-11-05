package hu.bme.aut.executor.feature.jobs.dto.singlejobs

import hu.bme.aut.executor.domain.SingleJobType

class LanguageDetectionJobResult(
    val languages: List<String>,
    val confidences: List<Float>
) : SingleJobResult(type = SingleJobType.LANGUAGE_DETECTION)