package com.up42.codingchallenge.feature.web

import com.up42.codingchallenge.feature.FeatureService
import com.up42.codingchallenge.feature.web.dto.FeatureDetailDto
import com.up42.codingchallenge.feature.web.dto.FeaturePreviewDto
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.CacheControl
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController()
@RequestMapping("/features")
class FeatureController(private val service: FeatureService) {
    var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping()
    @ApiOperation(value = "Return all features.")
    fun getFeatures(): List<FeaturePreviewDto> {
        logger.debug("/features endpoint was executed!")
        val featureList = service.getAllFeatures()
        return FeatureMapper.createListDto(featureList)
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Return one feature by its id.")
    fun getFeature(@PathVariable id: UUID): FeatureDetailDto {
        logger.debug("/features/{id} endpoint was executed!")
        val feature = service.getFeature(id)
        return FeatureMapper.createDetailDto(feature)
    }

    @GetMapping("/{id}/quicklook")
    @ApiOperation(value = "Return quicklook for a feature by its id.")
    fun getQuicklook(@PathVariable id: UUID): ResponseEntity<ByteArray> {
        logger.debug("/features/{id}/quicklook endpoint was executed!")

        val quicklook = service.getQuicklook(id)

        val headers = HttpHeaders()
        headers.contentType = MediaType.IMAGE_PNG
        headers.cacheControl = CacheControl.noCache().headerValue

        return ResponseEntity(quicklook, headers, HttpStatus.OK)
    }
}
