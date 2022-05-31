package com.up42.codingchallenge.feature.web

import com.up42.codingchallenge.feature.model.Feature
import com.up42.codingchallenge.feature.web.dto.FeatureDetailDto
import com.up42.codingchallenge.feature.web.dto.FeaturePreviewDto

class FeatureMapper {
    companion object {
        fun createListDto(srcList: List<Feature>): List<FeaturePreviewDto> {
            val dstList: ArrayList<FeaturePreviewDto> = ArrayList()

            for (feature: Feature in srcList) {
                dstList.add(
                    FeaturePreviewDto(
                        feature.id,
                        feature.timestamp,
                        feature.beginViewingDate,
                        feature.endViewingDate,
                        feature.missionName,
                    )
                )
            }

            return dstList
        }

        fun createDetailDto(feature: Feature): FeatureDetailDto = FeatureDetailDto(
            feature.id,
            feature.timestamp,
            feature.beginViewingDate,
            feature.endViewingDate,
            feature.missionName,
            feature.quicklook
        )
    }
}
