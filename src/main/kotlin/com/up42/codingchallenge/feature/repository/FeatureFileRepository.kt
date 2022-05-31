package com.up42.codingchallenge.feature.repository

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.up42.codingchallenge.feature.model.Feature
import com.up42.codingchallenge.feature.model.FeatureCollection
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Repository
class FeatureFileRepository : FeatureRepository {
    private val data: List<Feature> = ClassPathResource("/static/source-data.json").file.readText().let { jsonString ->
        jacksonObjectMapper().readValue<List<FeatureCollection>>(jsonString)
    }.flatMap {
        it.features
    }.map {
        it.apply {
            id = properties?.id
            timestamp = properties?.timestamp
            beginViewingDate = properties?.acquisition?.beginViewingDate
            endViewingDate = properties?.acquisition?.endViewingDate
            missionName = properties?.acquisition?.missionName
        }
    }.ifEmpty {
        throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No features found")
    }

    override fun findAll(): List<Feature> {
        return data
    }

    override fun findById(id: UUID): Feature? {
        for (feature : Feature in data) {
            if (feature.id == id) {
                return feature
            }
        }
        return null
    }
}
