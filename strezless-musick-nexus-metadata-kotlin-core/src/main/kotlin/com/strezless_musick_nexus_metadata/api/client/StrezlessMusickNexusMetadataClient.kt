// File generated from our OpenAPI spec by Stainless.

package com.strezless_musick_nexus_metadata.api.client

import com.strezless_musick_nexus_metadata.api.core.ClientOptions
import com.strezless_musick_nexus_metadata.api.services.blocking.PetService
import com.strezless_musick_nexus_metadata.api.services.blocking.StoreService
import com.strezless_musick_nexus_metadata.api.services.blocking.UserService

/**
 * A client for interacting with the Strezless Musick Nexus Metadata REST API synchronously. You can
 * also switch to asynchronous execution via the [async] method.
 *
 * This client performs best when you create a single instance and reuse it for all interactions
 * with the REST API. This is because each client holds its own connection pool and thread pools.
 * Reusing connections and threads reduces latency and saves memory. The client also handles rate
 * limiting per client. This means that creating and using multiple instances at the same time will
 * not respect rate limits.
 *
 * The threads and connections that are held will be released automatically if they remain idle. But
 * if you are writing an application that needs to aggressively release unused resources, then you
 * may call [close].
 */
interface StrezlessMusickNexusMetadataClient {

    /**
     * Returns a version of this client that uses asynchronous execution.
     *
     * The returned client shares its resources, like its connection pool and thread pools, with
     * this client.
     */
    fun async(): StrezlessMusickNexusMetadataClientAsync

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): StrezlessMusickNexusMetadataClient

    fun pets(): PetService

    fun store(): StoreService

    fun users(): UserService

    /**
     * Closes this client, relinquishing any underlying resources.
     *
     * This is purposefully not inherited from [AutoCloseable] because the client is long-lived and
     * usually should not be synchronously closed via try-with-resources.
     *
     * It's also usually not necessary to call this method at all. the default HTTP client
     * automatically releases threads and connections if they remain idle, but if you are writing an
     * application that needs to aggressively release unused resources, then you may call this
     * method.
     */
    fun close()

    /**
     * A view of [StrezlessMusickNexusMetadataClient] that provides access to raw HTTP responses for
     * each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): StrezlessMusickNexusMetadataClient.WithRawResponse

        fun pets(): PetService.WithRawResponse

        fun store(): StoreService.WithRawResponse

        fun users(): UserService.WithRawResponse
    }
}
