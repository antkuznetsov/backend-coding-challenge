package com.up42.codingchallenge.feature.web.dto

import java.util.UUID

data class FeaturePreviewDto(
    var id: UUID?,
    var timestamp: Long?,
    var beginViewingDate: Long?,
    var endViewingDate: Long?,
    var missionName: String?
)
