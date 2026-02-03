package com.strezless_musick_nexus_metadata.api.errors

open class StrezlessMusickNexusMetadataException(
    message: String? = null,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
