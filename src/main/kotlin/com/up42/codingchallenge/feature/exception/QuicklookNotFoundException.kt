package com.up42.codingchallenge.feature.exception

import java.util.UUID

class QuicklookNotFoundException(id: UUID) : RuntimeException("Could not find the quicklook for the feature $id")
