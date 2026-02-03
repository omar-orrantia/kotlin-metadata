// File generated from our OpenAPI spec by Stainless.

package com.strezless_musick_nexus_metadata.api.services.async.store

import com.strezless_musick_nexus_metadata.api.core.ClientOptions
import com.strezless_musick_nexus_metadata.api.core.RequestOptions
import com.strezless_musick_nexus_metadata.api.core.checkRequired
import com.strezless_musick_nexus_metadata.api.core.handlers.emptyHandler
import com.strezless_musick_nexus_metadata.api.core.handlers.errorBodyHandler
import com.strezless_musick_nexus_metadata.api.core.handlers.errorHandler
import com.strezless_musick_nexus_metadata.api.core.handlers.jsonHandler
import com.strezless_musick_nexus_metadata.api.core.http.HttpMethod
import com.strezless_musick_nexus_metadata.api.core.http.HttpRequest
import com.strezless_musick_nexus_metadata.api.core.http.HttpResponse
import com.strezless_musick_nexus_metadata.api.core.http.HttpResponse.Handler
import com.strezless_musick_nexus_metadata.api.core.http.HttpResponseFor
import com.strezless_musick_nexus_metadata.api.core.http.json
import com.strezless_musick_nexus_metadata.api.core.http.parseable
import com.strezless_musick_nexus_metadata.api.core.prepareAsync
import com.strezless_musick_nexus_metadata.api.models.Order
import com.strezless_musick_nexus_metadata.api.models.store.orders.OrderCreateParams
import com.strezless_musick_nexus_metadata.api.models.store.orders.OrderDeleteParams
import com.strezless_musick_nexus_metadata.api.models.store.orders.OrderRetrieveParams

class OrderServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    OrderServiceAsync {

    private val withRawResponse: OrderServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): OrderServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): OrderServiceAsync =
        OrderServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun create(params: OrderCreateParams, requestOptions: RequestOptions): Order =
        // post /store/order
        withRawResponse().create(params, requestOptions).parse()

    override suspend fun retrieve(
        params: OrderRetrieveParams,
        requestOptions: RequestOptions,
    ): Order =
        // get /store/order/{orderId}
        withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun delete(params: OrderDeleteParams, requestOptions: RequestOptions) {
        // delete /store/order/{orderId}
        withRawResponse().delete(params, requestOptions)
    }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        OrderServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): OrderServiceAsync.WithRawResponse =
            OrderServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val createHandler: Handler<Order> = jsonHandler<Order>(clientOptions.jsonMapper)

        override suspend fun create(
            params: OrderCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Order> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("store", "order")
                    .apply { params._body()?.let { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { createHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<Order> = jsonHandler<Order>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: OrderRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Order> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("orderId", params.orderId())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("store", "order", params._pathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val deleteHandler: Handler<Void?> = emptyHandler()

        override suspend fun delete(
            params: OrderDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("orderId", params.orderId())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("store", "order", params._pathParam(0))
                    .apply { params._body()?.let { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response.use { deleteHandler.handle(it) }
            }
        }
    }
}
