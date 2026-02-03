// File generated from our OpenAPI spec by Stainless.

package com.strezless_musick_nexus_metadata.api.errors

import com.strezless_musick_nexus_metadata.api.core.JsonValue
import com.strezless_musick_nexus_metadata.api.core.http.Headers

abstract class StrezlessMusickNexusMetadataServiceException
protected constructor(message: String, cause: Throwable? = null) :
    StrezlessMusickNexusMetadataException(message, cause) {

    abstract fun statusCode(): Int

    abstract fun headers(): Headers

    abstract fun body(): JsonValue
}
