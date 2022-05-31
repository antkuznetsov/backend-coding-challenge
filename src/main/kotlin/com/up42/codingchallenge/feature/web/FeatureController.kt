package com.up42.codingchallenge.feature.web

import com.up42.codingchallenge.feature.FeatureService
import com.up42.codingchallenge.feature.web.dto.FeatureDetailDto
import com.up42.codingchallenge.feature.web.dto.FeaturePreviewDto
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

import java.util.UUID

@RestController
class FeatureController(private val service: FeatureService) {
    @GetMapping("/features")
    fun getFeatures() : List<FeaturePreviewDto> {
        val featureList = service.getAllFeatures()
        return FeatureMapper.createListDto(featureList)
    }

    @GetMapping("/features/{id}")
    fun getFeature(@PathVariable id: UUID) : FeatureDetailDto {
        val feature = service.getFeature(id) ?: throw ResponseStatusException(NOT_FOUND, "Feature does not exist!")
        return FeatureMapper.createDetailDto(feature)
    }
}
