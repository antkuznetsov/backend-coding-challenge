package com.up42.codingchallenge.feature.web

import com.up42.codingchallenge.feature.FeatureService
import com.up42.codingchallenge.feature.web.dto.FeatureDetailDto
import com.up42.codingchallenge.feature.web.dto.FeaturePreviewDto
import org.springframework.http.CacheControl
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class FeatureController(private val service: FeatureService) {
    @GetMapping("/features")
    fun getFeatures(): List<FeaturePreviewDto> {
        val featureList = service.getAllFeatures()
        return FeatureMapper.createListDto(featureList)
    }

    @GetMapping("/features/{id}")
    fun getFeature(@PathVariable id: UUID): FeatureDetailDto {
        val feature = service.getFeature(id)
        return FeatureMapper.createDetailDto(feature)
    }

    @GetMapping("/features/{id}/quicklook")
    fun getQuicklook(@PathVariable id: UUID): ResponseEntity<ByteArray> {
        val quicklook = service.getQuicklook(id)

        val headers = HttpHeaders()
        headers.contentType = MediaType.IMAGE_PNG
        headers.cacheControl = CacheControl.noCache().headerValue

        return ResponseEntity(quicklook, headers, HttpStatus.OK)
    }
}
