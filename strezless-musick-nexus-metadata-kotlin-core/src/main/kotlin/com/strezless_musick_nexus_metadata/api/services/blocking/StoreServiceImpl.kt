// File generated from our OpenAPI spec by Stainless.

package com.strezless_musick_nexus_metadata.api.services.blocking

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
import com.strezless_musick_nexus_metadata.api.core.prepare
import com.strezless_musick_nexus_metadata.api.models.store.StoreListInventoryParams
import com.strezless_musick_nexus_metadata.api.models.store.StoreListInventoryResponse
import com.strezless_musick_nexus_metadata.api.services.blocking.store.OrderService
import com.strezless_musick_nexus_metadata.api.services.blocking.store.OrderServiceImpl

class StoreServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    StoreService {

    private val withRawResponse: StoreService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val orders: OrderService by lazy { OrderServiceImpl(clientOptions) }

    override fun withRawResponse(): StoreService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): StoreService =
        StoreServiceImpl(clientOptions.toBuilder().apply(modifier).build())

    override fun orders(): OrderService = orders

    override fun listInventory(
        params: StoreListInventoryParams,
        requestOptions: RequestOptions,
    ): StoreListInventoryResponse =
        // get /store/inventory
        withRawResponse().listInventory(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        StoreService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val orders: OrderService.WithRawResponse by lazy {
            OrderServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): StoreService.WithRawResponse =
            StoreServiceImpl.WithRawResponseImpl(clientOptions.toBuilder().apply(modifier).build())

        override fun orders(): OrderService.WithRawResponse = orders

        private val listInventoryHandler: Handler<StoreListInventoryResponse> =
            jsonHandler<StoreListInventoryResponse>(clientOptions.jsonMapper)

        override fun listInventory(
            params: StoreListInventoryParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StoreListInventoryResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("store", "inventory")
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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
