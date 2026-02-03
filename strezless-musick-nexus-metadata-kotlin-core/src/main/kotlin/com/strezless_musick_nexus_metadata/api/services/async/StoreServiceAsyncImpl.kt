// File generated from our OpenAPI spec by Stainless.

package com.strezless_musick_nexus_metadata.api.services.async

import com.strezless_musick_nexus_metadata.api.core.ClientOptions
import com.strezless_musick_nexus_metadata.api.core.RequestOptions
import com.strezless_musick_nexus_metadata.api.core.handlers.errorBodyHandler
import com.strezless_musick_nexus_metadata.api.core.handlers.errorHandler
import com.strezless_musick_nexus_metadata.api.core.handlers.jsonHandler
import com.strezless_musick_nexus_metadata.api.core.http.HttpMethod
import com.strezless_musick_nexus_metadata.api.core.http.HttpRequest
import com.strezless_musick_nexus_metadata.api.core.http.HttpResponse
import com.strezless_musick_nexus_metadata.api.core.http.HttpResponse.Handler
import com.strezless_musick_nexus_metadata.api.core.http.HttpResponseFor
import com.strezless_musick_nexus_metadata.api.core.http.parseable
import com.strezless_musick_nexus_metadata.api.core.prepareAsync
import com.strezless_musick_nexus_metadata.api.models.store.StoreListInventoryParams
import com.strezless_musick_nexus_metadata.api.models.store.StoreListInventoryResponse
import com.strezless_musick_nexus_metadata.api.services.async.store.OrderServiceAsync
import com.strezless_musick_nexus_metadata.api.services.async.store.OrderServiceAsyncImpl

class StoreServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    StoreServiceAsync {

    private val withRawResponse: StoreServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val orders: OrderServiceAsync by lazy { OrderServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): StoreServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): StoreServiceAsync =
        StoreServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override fun orders(): OrderServiceAsync = orders

    override suspend fun listInventory(
        params: StoreListInventoryParams,
        requestOptions: RequestOptions,
    ): StoreListInventoryResponse =
        // get /store/inventory
        withRawResponse().listInventory(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        StoreServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val orders: OrderServiceAsync.WithRawResponse by lazy {
            OrderServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): StoreServiceAsync.WithRawResponse =
            StoreServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        override fun orders(): OrderServiceAsync.WithRawResponse = orders

        private val listInventoryHandler: Handler<StoreListInventoryResponse> =
            jsonHandler<StoreListInventoryResponse>(clientOptions.jsonMapper)

        override suspend fun listInventory(
            params: StoreListInventoryParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StoreListInventoryResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("store", "inventory")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { listInventoryHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
