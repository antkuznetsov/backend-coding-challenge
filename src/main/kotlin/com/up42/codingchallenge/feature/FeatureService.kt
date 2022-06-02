package com.up42.codingchallenge.feature

import com.up42.codingchallenge.feature.exception.FeatureNotFoundException
import com.up42.codingchallenge.feature.exception.QuicklookNotFoundException
import com.up42.codingchallenge.feature.repository.FeatureRepository
import org.apache.tomcat.util.codec.binary.Base64
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FeatureService(private val repository: FeatureRepository) {
    var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    fun getAllFeatures() = repository.findAll()

    fun getFeature(id: UUID) = repository.findById(id) ?: throw FeatureNotFoundException(id)

    fun getQuicklook(id: UUID): ByteArray {
        logger.debug("Try to find quicklook!")
        val feature = repository.findById(id) ?: throw FeatureNotFoundException(id)
        val quicklook = feature.quicklook ?: throw QuicklookNotFoundException(id)
        return Base64.decodeBase64(quicklook)
    }
}
