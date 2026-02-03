// File generated from our OpenAPI spec by Stainless.

package com.strezless_musick_nexus_metadata.api.services.async

import com.strezless_musick_nexus_metadata.api.TestServerExtension
import com.strezless_musick_nexus_metadata.api.client.okhttp.StrezlessMusickNexusMetadataOkHttpClientAsync
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class StoreServiceAsyncTest {

    @Disabled("Prism tests are disabled")
    @Test
    suspend fun listInventory() {
        val client =
            StrezlessMusickNexusMetadataOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("My API Key")
                .build()
        val storeServiceAsync = client.store()

        val response = storeServiceAsync.listInventory()

        response.validate()
    }
}
