@file:JvmName("StringHandler")

package com.strezless_musick_nexus_metadata.api.core.handlers

import com.strezless_musick_nexus_metadata.api.core.http.HttpResponse
import com.strezless_musick_nexus_metadata.api.core.http.HttpResponse.Handler

internal fun stringHandler(): Handler<String> = StringHandlerInternal

private object StringHandlerInternal : Handler<String> {
    override fun handle(response: HttpResponse): String =
        response.body().readBytes().toString(Charsets.UTF_8)
}
