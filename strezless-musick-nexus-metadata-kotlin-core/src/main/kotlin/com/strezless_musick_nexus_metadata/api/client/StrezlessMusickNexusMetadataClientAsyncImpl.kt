// File generated from our OpenAPI spec by Stainless.

package com.strezless_musick_nexus_metadata.api.client

import com.strezless_musick_nexus_metadata.api.core.ClientOptions
import com.strezless_musick_nexus_metadata.api.core.getPackageVersion
import com.strezless_musick_nexus_metadata.api.services.async.PetServiceAsync
import com.strezless_musick_nexus_metadata.api.services.async.PetServiceAsyncImpl
import com.strezless_musick_nexus_metadata.api.services.async.StoreServiceAsync
import com.strezless_musick_nexus_metadata.api.services.async.StoreServiceAsyncImpl
import com.strezless_musick_nexus_metadata.api.services.async.UserServiceAsync
import com.strezless_musick_nexus_metadata.api.services.async.UserServiceAsyncImpl

class StrezlessMusickNexusMetadataClientAsyncImpl(private val clientOptions: ClientOptions) :
    StrezlessMusickNexusMetadataClientAsync {

    private val clientOptionsWithUserAgent =
        if (clientOptions.headers.names().contains("User-Agent")) clientOptions
        else
            clientOptions
                .toBuilder()
                .putHeader("User-Agent", "${javaClass.simpleName}/Kotlin ${getPackageVersion()}")
                .build()

    // Pass the original clientOptions so that this client sets its own User-Agent.
    private val sync: StrezlessMusickNexusMetadataClient by lazy {
        StrezlessMusickNexusMetadataClientImpl(clientOptions)
    }

    private val withRawResponse: StrezlessMusickNexusMetadataClientAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val pets: PetServiceAsync by lazy { PetServiceAsyncImpl(clientOptionsWithUserAgent) }

    private val store: StoreServiceAsync by lazy {
        StoreServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    private val users: UserServiceAsync by lazy { UserServiceAsyncImpl(clientOptionsWithUserAgent) }

    override fun sync(): StrezlessMusickNexusMetadataClient = sync

    override fun withRawResponse(): StrezlessMusickNexusMetadataClientAsync.WithRawResponse =
        withRawResponse

    override fun withOptions(
        modifier: (ClientOptions.Builder) -> Unit
    ): StrezlessMusickNexusMetadataClientAsync =
        StrezlessMusickNexusMetadataClientAsyncImpl(
            clientOptions.toBuilder().apply(modifier).build()
        )

    override fun pets(): PetServiceAsync = pets

    override fun store(): StoreServiceAsync = store

    override fun users(): UserServiceAsync = users

    override fun close() = clientOptions.close()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        StrezlessMusickNexusMetadataClientAsync.WithRawResponse {

        private val pets: PetServiceAsync.WithRawResponse by lazy {
            PetServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val store: StoreServiceAsync.WithRawResponse by lazy {
            StoreServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val users: UserServiceAsync.WithRawResponse by lazy {
            UserServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): StrezlessMusickNexusMetadataClientAsync.WithRawResponse =
            StrezlessMusickNexusMetadataClientAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        override fun pets(): PetServiceAsync.WithRawResponse = pets

        override fun store(): StoreServiceAsync.WithRawResponse = store

        override fun users(): UserServiceAsync.WithRawResponse = users
    }
}
