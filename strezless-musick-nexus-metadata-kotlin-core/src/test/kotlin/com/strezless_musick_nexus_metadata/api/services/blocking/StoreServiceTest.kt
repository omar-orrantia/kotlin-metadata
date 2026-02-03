// File generated from our OpenAPI spec by Stainless.

package com.strezless_musick_nexus_metadata.api.services.blocking

import com.strezless_musick_nexus_metadata.api.TestServerExtension
import com.strezless_musick_nexus_metadata.api.client.okhttp.StrezlessMusickNexusMetadataOkHttpClient
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class StoreServiceTest {

    @Disabled("Prism tests are disabled")
    @Test
    fun listInventory() {
        val client =
            StrezlessMusickNexusMetadataOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("My API Key")
                .build()
        val storeService = client.store()

        val response = storeService.listInventory()

        response.validate()
    }
}
