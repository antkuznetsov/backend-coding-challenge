package com.up42.codingchallenge.feature.web

import com.up42.codingchallenge.feature.FeatureService
import com.up42.codingchallenge.feature.web.dto.FeatureDetailDto
import com.up42.codingchallenge.feature.web.dto.FeaturePreviewDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
@RequestMapping("/v1/features")
class FeatureController(private val service: FeatureService) {
    var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping()
    @Operation(summary = "Get all features")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found features", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = FeaturePreviewDto::class)))))])]
    )
    fun getFeatures(): List<FeaturePreviewDto> {
        logger.debug("/features endpoint was executed!")
        val featureList = service.getAllFeatures()
        return FeatureMapper.createListDto(featureList)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one feature by its id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found feature", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = FeatureDetailDto::class)))))]),
        ApiResponse(responseCode = "404", description = "Feature is not found", content = [Content()])]
    )
    fun getFeature(@PathVariable id: UUID): FeatureDetailDto {
        logger.debug("/features/{id} endpoint was executed!")
        val feature = service.getFeature(id)
        return FeatureMapper.createDetailDto(feature)
    }

    @GetMapping("/{id}/quicklook")
    @Operation(summary = "Get quicklook for a feature by feature's id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found quicklook", content = [
            (Content(mediaType = "image/png"))]),
        ApiResponse(responseCode = "404", description = "Feature is not found", content = [Content()]),
        ApiResponse(responseCode = "406", description = "Quicklook for this feature doesn't exist", content = [Content()])]
    )
    fun getQuicklook(@PathVariable id: UUID): ResponseEntity<ByteArray> {
        logger.debug("/features/{id}/quicklook endpoint was executed!")

        val quicklook = service.getQuicklook(id)

        val headers = HttpHeaders()
        headers.contentType = MediaType.IMAGE_PNG
        headers.cacheControl = CacheControl.noCache().headerValue

        return ResponseEntity(quicklook, headers, HttpStatus.OK)
    }
}
