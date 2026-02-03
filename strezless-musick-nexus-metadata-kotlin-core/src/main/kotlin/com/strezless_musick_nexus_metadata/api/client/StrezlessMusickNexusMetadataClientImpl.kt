// File generated from our OpenAPI spec by Stainless.

package com.strezless_musick_nexus_metadata.api.client

import com.strezless_musick_nexus_metadata.api.core.ClientOptions
import com.strezless_musick_nexus_metadata.api.core.getPackageVersion
import com.strezless_musick_nexus_metadata.api.services.blocking.PetService
import com.strezless_musick_nexus_metadata.api.services.blocking.PetServiceImpl
import com.strezless_musick_nexus_metadata.api.services.blocking.StoreService
import com.strezless_musick_nexus_metadata.api.services.blocking.StoreServiceImpl
import com.strezless_musick_nexus_metadata.api.services.blocking.UserService
import com.strezless_musick_nexus_metadata.api.services.blocking.UserServiceImpl

class StrezlessMusickNexusMetadataClientImpl(private val clientOptions: ClientOptions) :
    StrezlessMusickNexusMetadataClient {

    private val clientOptionsWithUserAgent =
        if (clientOptions.headers.names().contains("User-Agent")) clientOptions
        else
            clientOptions
                .toBuilder()
                .putHeader("User-Agent", "${javaClass.simpleName}/Kotlin ${getPackageVersion()}")
                .build()

    // Pass the original clientOptions so that this client sets its own User-Agent.
    private val async: StrezlessMusickNexusMetadataClientAsync by lazy {
        StrezlessMusickNexusMetadataClientAsyncImpl(clientOptions)
    }

    private val withRawResponse: StrezlessMusickNexusMetadataClient.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val pets: PetService by lazy { PetServiceImpl(clientOptionsWithUserAgent) }

    private val store: StoreService by lazy { StoreServiceImpl(clientOptionsWithUserAgent) }

    private val users: UserService by lazy { UserServiceImpl(clientOptionsWithUserAgent) }

    override fun async(): StrezlessMusickNexusMetadataClientAsync = async

    override fun withRawResponse(): StrezlessMusickNexusMetadataClient.WithRawResponse =
        withRawResponse

    override fun withOptions(
        modifier: (ClientOptions.Builder) -> Unit
    ): StrezlessMusickNexusMetadataClient =
        StrezlessMusickNexusMetadataClientImpl(clientOptions.toBuilder().apply(modifier).build())

    override fun pets(): PetService = pets

    override fun store(): StoreService = store

    override fun users(): UserService = users

    override fun close() = clientOptions.close()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        StrezlessMusickNexusMetadataClient.WithRawResponse {

        private val pets: PetService.WithRawResponse by lazy {
            PetServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val store: StoreService.WithRawResponse by lazy {
            StoreServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val users: UserService.WithRawResponse by lazy {
            UserServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): StrezlessMusickNexusMetadataClient.WithRawResponse =
            StrezlessMusickNexusMetadataClientImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        override fun pets(): PetService.WithRawResponse = pets

        override fun store(): StoreService.WithRawResponse = store

        override fun users(): UserService.WithRawResponse = users
    }
}
