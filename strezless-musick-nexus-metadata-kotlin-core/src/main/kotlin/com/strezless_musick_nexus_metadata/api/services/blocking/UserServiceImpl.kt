// File generated from our OpenAPI spec by Stainless.

package com.strezless_musick_nexus_metadata.api.services.blocking

import com.strezless_musick_nexus_metadata.api.core.ClientOptions
import com.strezless_musick_nexus_metadata.api.core.RequestOptions
import com.strezless_musick_nexus_metadata.api.core.checkRequired
import com.strezless_musick_nexus_metadata.api.core.handlers.emptyHandler
import com.strezless_musick_nexus_metadata.api.core.handlers.errorBodyHandler
import com.strezless_musick_nexus_metadata.api.core.handlers.errorHandler
import com.strezless_musick_nexus_metadata.api.core.handlers.jsonHandler
import com.strezless_musick_nexus_metadata.api.core.handlers.stringHandler
import com.strezless_musick_nexus_metadata.api.core.http.HttpMethod
import com.strezless_musick_nexus_metadata.api.core.http.HttpRequest
import com.strezless_musick_nexus_metadata.api.core.http.HttpResponse
import com.strezless_musick_nexus_metadata.api.core.http.HttpResponse.Handler
import com.strezless_musick_nexus_metadata.api.core.http.HttpResponseFor
import com.strezless_musick_nexus_metadata.api.core.http.json
import com.strezless_musick_nexus_metadata.api.core.http.parseable
import com.strezless_musick_nexus_metadata.api.core.prepare
import com.strezless_musick_nexus_metadata.api.models.users.User
import com.strezless_musick_nexus_metadata.api.models.users.UserCreateParams
import com.strezless_musick_nexus_metadata.api.models.users.UserCreateWithListParams
import com.strezless_musick_nexus_metadata.api.models.users.UserDeleteParams
import com.strezless_musick_nexus_metadata.api.models.users.UserLoginParams
import com.strezless_musick_nexus_metadata.api.models.users.UserLogoutParams
import com.strezless_musick_nexus_metadata.api.models.users.UserRetrieveParams
import com.strezless_musick_nexus_metadata.api.models.users.UserUpdateParams

class UserServiceImpl internal constructor(private val clientOptions: ClientOptions) : UserService {

    private val withRawResponse: UserService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): UserService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): UserService =
        UserServiceImpl(clientOptions.toBuilder().apply(modifier).build())

    override fun create(params: UserCreateParams, requestOptions: RequestOptions): User =
        // post /user
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(params: UserRetrieveParams, requestOptions: RequestOptions): User =
        // get /user/{username}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(params: UserUpdateParams, requestOptions: RequestOptions) {
        // put /user/{username}
        withRawResponse().update(params, requestOptions)
    }

    override fun delete(params: UserDeleteParams, requestOptions: RequestOptions) {
        // delete /user/{username}
        withRawResponse().delete(params, requestOptions)
    }

    override fun createWithList(
        params: UserCreateWithListParams,
        requestOptions: RequestOptions,
    ): User =
        // post /user/createWithList
        withRawResponse().createWithList(params, requestOptions).parse()

    override fun login(params: UserLoginParams, requestOptions: RequestOptions): String =
        // get /user/login
        withRawResponse().login(params, requestOptions).parse()

    override fun logout(params: UserLogoutParams, requestOptions: RequestOptions) {
        // get /user/logout
        withRawResponse().logout(params, requestOptions)
    }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        UserService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): UserService.WithRawResponse =
            UserServiceImpl.WithRawResponseImpl(clientOptions.toBuilder().apply(modifier).build())

        private val createHandler: Handler<User> = jsonHandler<User>(clientOptions.jsonMapper)

        override fun create(
            params: UserCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<User> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("user")
                    .apply { params._body()?.let { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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

        private val retrieveHandler: Handler<User> = jsonHandler<User>(clientOptions.jsonMapper)

        override fun retrieve(
            params: UserRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<User> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("username", params.username())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("user", params._pathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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

        private val updateHandler: Handler<Void?> = emptyHandler()

        override fun update(
            params: UserUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("existingUsername", params.existingUsername())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PUT)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("user", params._pathParam(0))
                    .apply { params._body()?.let { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response.use { updateHandler.handle(it) }
            }
        }

        private val deleteHandler: Handler<Void?> = emptyHandler()

        override fun delete(
            params: UserDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("username", params.username())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("user", params._pathParam(0))
                    .apply { params._body()?.let { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response.use { deleteHandler.handle(it) }
            }
        }

        private val createWithListHandler: Handler<User> =
            jsonHandler<User>(clientOptions.jsonMapper)

        override fun createWithList(
            params: UserCreateWithListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<User> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("user", "createWithList")
                    .apply { params._body()?.let { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { createWithListHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val loginHandler: Handler<String> = stringHandler()

        override fun login(
            params: UserLoginParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<String> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("user", "login")
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response.use { loginHandler.handle(it) }
            }
        }

        private val logoutHandler: Handler<Void?> = emptyHandler()

        override fun logout(
            params: UserLogoutParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("user", "logout")
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response.use { logoutHandler.handle(it) }
            }
        }
    }
}
