// File generated from our OpenAPI spec by Stainless.

package com.strezless_musick_nexus_metadata.api.models.store.orders

import com.strezless_musick_nexus_metadata.api.models.Order
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrderCreateParamsTest {

    @Test
    fun create() {
        OrderCreateParams.builder()
            .order(
                Order.builder()
                    .id(10L)
                    .complete(true)
                    .petId(198772L)
                    .quantity(7)
                    .shipDate(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .status(Order.Status.APPROVED)
                    .build()
            )
            .build()
    }

    @Test
    fun body() {
        val params =
            OrderCreateParams.builder()
                .order(
                    Order.builder()
                        .id(10L)
                        .complete(true)
                        .petId(198772L)
                        .quantity(7)
                        .shipDate(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .status(Order.Status.APPROVED)
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body)
            .isEqualTo(
                Order.builder()
                    .id(10L)
                    .complete(true)
                    .petId(198772L)
                    .quantity(7)
                    .shipDate(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .status(Order.Status.APPROVED)
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = OrderCreateParams.builder().build()

        val body = params._body()
    }
}
