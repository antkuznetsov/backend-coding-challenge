package com.up42.codingchallenge.feature

import com.up42.codingchallenge.feature.exception.FeatureNotFoundException
import com.up42.codingchallenge.feature.exception.QuicklookNotFoundException
import com.up42.codingchallenge.feature.model.Feature
import com.up42.codingchallenge.feature.repository.FeatureRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.UUID

internal class FeatureServiceTest {
    val mockRepository = Mockito.mock(FeatureRepository::class.java)
    val featureService = FeatureService(mockRepository)

    @Test
    fun `should execute the method findAll()`() {
        featureService.getAllFeatures()
        Mockito.verify(mockRepository).findAll()
    }

    @Test
    fun `should throw FeatureNotFoundException`() {
        val testUUID = UUID.fromString("bd2cbad1-6ccf-48e3-bb92-bc9961bc011e")

        Mockito.`when`(mockRepository.findById(testUUID)).thenReturn(null)

        val exception: Exception = assertThrows(FeatureNotFoundException::class.java) {
            featureService.getFeature(testUUID)
        }

        assertEquals("Could not find the feature $testUUID", exception.message)
    }

    @Test
    fun `should throw QuicklookNotFoundException`() {
        val testUUID = UUID.fromString("bd2cbad1-6ccf-48e3-bb92-bc9961bc011e")
        val testFeature = Feature(
            null,
            testUUID,
            1558155148786,
            1558155148786,
            1558155173785,
            "Sentinel-1A",
            null
        )

        Mockito.`when`(mockRepository.findById(testUUID)).thenReturn(testFeature)

        val exception: Exception = assertThrows(QuicklookNotFoundException::class.java) {
            featureService.getQuicklook(testUUID)
        }

        assertEquals("Could not find the quicklook for the feature $testUUID", exception.message)
    }

    @Test
    fun `should throw FeatureNotFoundException for quicklook`() {
        val testUUID = UUID.fromString("bd2cbad1-6ccf-48e3-bb92-bc9961bc011e")

        Mockito.`when`(mockRepository.findById(testUUID)).thenReturn(null)

        val exception: Exception = assertThrows(FeatureNotFoundException::class.java) {
            featureService.getQuicklook(testUUID)
        }

        assertEquals("Could not find the feature $testUUID", exception.message)
    }
}
