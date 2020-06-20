package org.sdelaysam.carprice.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.joda.time.DateTime
import org.sdelaysam.carprice.util.data.DateTimeSerializer

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

@Serializable
class Make(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("active")
    val active: Boolean,
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: DateTime? = null,
    @SerialName("updated_at")
    @Serializable(with = DateTimeSerializer::class)
    val updatedAt: DateTime? = null
)